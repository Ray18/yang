package com.xi.xlm.controller.w;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.len.util.Result;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.HotelInfo;
import com.xi.xlm.entity.HotelQuotation;
import com.xi.xlm.entity.HotelQuotationDetails;
import com.xi.xlm.exception.ServiceException;
import com.xi.xlm.request.w.SaveCard;
import com.xi.xlm.request.w.SaveQuotationReq;
import com.xi.xlm.response.w.QuotationDetailsResp;
import com.xi.xlm.response.w.QuotationResp;
import com.xi.xlm.service.IHotelInfoService;
import com.xi.xlm.service.IHotelQuotationDetailsService;
import com.xi.xlm.service.IHotelQuotationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 报价单主体 前端控制器
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/w/hotel/hotelQuotation")
@AllArgsConstructor
@Api(tags = "B-酒店-报价管理")
public class WHotelQuotationController {

    private IHotelInfoService iHotelInfoService;
    private IHotelQuotationService iHotelQuotationService;
    private IHotelQuotationDetailsService iHotelQuotationDetailsService;


    @PostMapping("getCard")
    @ApiOperation("获取用户名片")
    public Result getCard() {
        QueryWrapper<HotelInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(HotelInfo.MEMBER_ID, UPrincipal.getMember().getId());
        wrapper.eq(HotelInfo.STATUS, 1);
        HotelInfo hotelInfo = iHotelInfoService.getOne(wrapper);
        return Result.ok(hotelInfo);
    }


    @PostMapping("saveCard")
    @ApiOperation("保存用户名片")
    public Result saveCard(@Valid @RequestBody SaveCard saveCard) {

        HotelInfo hotelInfo = iHotelInfoService.getById(saveCard.getId());

        if (hotelInfo == null) {
            return Result.error("保存错误，没有查到员工信息");

        } else {
            hotelInfo.setEmployeeName(saveCard.getEmployeeName());
            hotelInfo.setWechat(saveCard.getWechat());
            hotelInfo.setWechatImg(saveCard.getWechatImg());

            return Result.ok(iHotelInfoService.updateById(hotelInfo));
        }

    }


    @GetMapping("getQuotationById/{id}")
    @ApiOperation("根据ID查询报价单")
    public Result<QuotationResp> getQuotationById(@PathVariable String id) {

        HotelQuotation hotelQuotations = iHotelQuotationService.getById(id);
        QuotationResp quotationResp = BeanUtil.toBean(hotelQuotations, QuotationResp.class);

        List<HotelQuotationDetails> details = iHotelQuotationDetailsService.list(Wrappers.<HotelQuotationDetails>lambdaQuery().eq(HotelQuotationDetails::getQuotationId, quotationResp.getId()));
        List<QuotationDetailsResp> detailsRespList = new ArrayList<>();
        details.forEach(de -> {
            QuotationDetailsResp detailsResp = BeanUtil.toBean(de, QuotationDetailsResp.class);
            detailsRespList.add(detailsResp);
        });

        quotationResp.setReqs(detailsRespList);

        return Result.ok(quotationResp);
    }

    @GetMapping("getQuotationList")
    @ApiOperation("报价单列表")
    public Result<List<QuotationResp>> getQuotationList( String title) {
        QueryWrapper<HotelQuotation> hotelQuotationQueryWrapper = new QueryWrapper<>();
        hotelQuotationQueryWrapper.eq(HotelQuotation.MEMBER_ID, UPrincipal.getMember().getId());
        hotelQuotationQueryWrapper.eq(StringUtils.hasText(title), HotelQuotation.PRODUCT_NAME, title);
        List<HotelQuotation> hotelQuotations = iHotelQuotationService.list(hotelQuotationQueryWrapper);
        List<QuotationResp> quotationRespList = new ArrayList<>();
        hotelQuotations.forEach(q -> {
            QuotationResp quotationResp = BeanUtil.toBean(q, QuotationResp.class);

            List<HotelQuotationDetails> details = iHotelQuotationDetailsService.list(Wrappers.<HotelQuotationDetails>lambdaQuery().eq(HotelQuotationDetails::getQuotationId, q.getId()));
            List<QuotationDetailsResp> detailsRespList = new ArrayList<>();
            details.forEach(de -> {
                QuotationDetailsResp detailsResp = BeanUtil.toBean(de, QuotationDetailsResp.class);
                detailsRespList.add(detailsResp);
            });

            quotationResp.setReqs(detailsRespList);

            quotationRespList.add(quotationResp);
        });


        return Result.ok(quotationRespList);
    }


    @PostMapping("saveQuotation")
    @ApiOperation("保存报价单")
    @Transactional
    public Result saveQuotation(@Valid @RequestBody SaveQuotationReq req) {
        try {
            if (StringUtils.hasText(req.getId())) {
                HotelQuotation quotation = iHotelQuotationService.getById(req.getId());

                quotation.setCountPrice(req.getCountPrice());
                quotation.setDiscountPrice(req.getDiscountPrice());
                quotation.setDiscount(req.getDiscount());
                quotation.setProductName(req.getProductName());
                iHotelQuotationService.updateById(quotation);

                iHotelQuotationDetailsService.remove(Wrappers.<HotelQuotationDetails>lambdaQuery().eq(HotelQuotationDetails::getQuotationId, req.getId()));
                details(req, quotation);

            } else {
                HotelQuotation quotation = new HotelQuotation();
                quotation.setMemberId(UPrincipal.getMember().getId());

                QueryWrapper<HotelInfo> wrapper = new QueryWrapper<>();
                wrapper.eq(HotelInfo.MEMBER_ID, UPrincipal.getMember().getId());
                wrapper.eq(HotelInfo.STATUS, 1);
                HotelInfo hotelInfo = iHotelInfoService.getOne(wrapper);

                quotation.setMemberName(hotelInfo.getEmployeeName());
                quotation.setMemberWechat(hotelInfo.getWechat());
                quotation.setMemberWechatImg(hotelInfo.getWechatImg());

                quotation.setCountPrice(req.getCountPrice());
                quotation.setDiscountPrice(req.getDiscountPrice());
                quotation.setDiscount(req.getDiscount());
                quotation.setProductName(req.getProductName());
                iHotelQuotationService.save(quotation);

                details(req, quotation);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("保存失败，请稍后重试");
        }


        return Result.ok();
    }


    private void details(SaveQuotationReq req, HotelQuotation quotation) {
        if (req.getReqs().size() > 0) {
            List<HotelQuotationDetails> details = new ArrayList<>();
            req.getReqs().forEach(a -> {
                HotelQuotationDetails de = new HotelQuotationDetails();
                de.setProductType(false);
                de.setProductId(a.getProductId());
                de.setProductName(a.getProductName());
                de.setProductCount(a.getProductCount());
                de.setProductPrice(a.getProductPrice());
                de.setProductCountPrice(a.getProductCountPrice());
                de.setQuotationId(quotation.getId());
                de.setOriginalPrice(a.getOriginalPrice());
                de.setProductImg(a.getProductImg());
                details.add(de);
            });

            req.getReqList().forEach(r -> {
                HotelQuotationDetails de = new HotelQuotationDetails();
                de.setProductType(true);
                de.setProductId(r.getProductId());
                de.setProductName(r.getProductName());
                de.setProductCount(r.getProductCount());
                de.setProductPrice(r.getProductPrice());
                de.setProductCountPrice(r.getProductCountPrice());
                de.setQuotationId(quotation.getId());
                de.setOriginalPrice(r.getOriginalPrice());
                de.setProductImg(r.getProductImg());
                details.add(de);
            });
            iHotelQuotationDetailsService.saveBatch(details);

        } else {
            throw new ServiceException("请选择产品");
        }

    }


}
