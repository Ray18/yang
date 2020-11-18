package com.xi.xlm.controller.m;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.util.ExcelUtil;
import com.len.util.Result;
import com.xi.xlm.entity.HotelQuotation;
import com.xi.xlm.entity.HotelQuotationDetails;
import com.xi.xlm.response.w.QuotationDetailsResp;
import com.xi.xlm.response.w.QuotationExportResp;
import com.xi.xlm.response.w.QuotationResp;
import com.xi.xlm.service.IHotelInfoService;
import com.xi.xlm.service.IHotelQuotationDetailsService;
import com.xi.xlm.service.IHotelQuotationService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-21
 */
@Controller
@RequestMapping("/m/hotelQuotation")
@AllArgsConstructor
public class HotelQuotationController {

    private IHotelInfoService iHotelInfoService;
    private IHotelQuotationService iHotelQuotationService;
    private IHotelQuotationDetailsService iHotelQuotationDetailsService;


    @GetMapping("getQuotationById")
    public String getQuotationById(Model model, String id) {

        HotelQuotation hotelQuotations = iHotelQuotationService.getById(id);
        QuotationResp quotationResp = BeanUtil.toBean(hotelQuotations, QuotationResp.class);
        List<HotelQuotationDetails> details = iHotelQuotationDetailsService.list(Wrappers.<HotelQuotationDetails>lambdaQuery().eq(HotelQuotationDetails::getQuotationId, quotationResp.getId()));
        List<QuotationDetailsResp> detailsRespList = new ArrayList<>();
        details.forEach(de -> {
            QuotationDetailsResp detailsResp = BeanUtil.toBean(de, QuotationDetailsResp.class);
            detailsRespList.add(detailsResp);
        });

        quotationResp.setReqs(detailsRespList);
        model.addAttribute("model", quotationResp);

        return "hotel/quotation/details";
    }


    @GetMapping("show")
    @RequiresPermissions("hotel:quoShow")
    public String show() {

        return "hotel/quotation/list";
    }

    @GetMapping("getQuotationList")
    @ResponseBody
    public Result getQuotationList(String productName, String memberName, Long page, Long limit) {
        Page<HotelQuotation> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<HotelQuotation> hotelQuotations = iHotelQuotationService.page(pages, Wrappers.<HotelQuotation>lambdaQuery().like(StringUtils.hasText(memberName), HotelQuotation::getMemberName, memberName).like(StringUtils.hasText(productName), HotelQuotation::getProductName, productName));
        return Result.ok(hotelQuotations);
    }


    @GetMapping("export")
    public void export(HttpServletResponse response) throws Exception {
        List<HotelQuotation> hotelQ = iHotelQuotationService.list();
        List<QuotationExportResp> quotationExportResps = new ArrayList<>();
        hotelQ.forEach(q -> {
            QuotationExportResp quotationResp = BeanUtil.toBean(q, QuotationExportResp.class);
            List<HotelQuotationDetails> details = iHotelQuotationDetailsService.list(Wrappers.<HotelQuotationDetails>lambdaQuery().eq(HotelQuotationDetails::getQuotationId, quotationResp.getId()));
            StringBuffer des = new StringBuffer();
            details.forEach(de -> {
                des.append(de.getProductName() + "-" + de.getProductPrice() + "X" + de.getProductCount() + ";\n");
            });
            quotationResp.setDetails(des.toString());
            quotationExportResps.add(quotationResp);
        });
        ExcelUtil.writeExcel(response, quotationExportResps, "报价单记录", "所有报价单", new QuotationExportResp());

    }


}
