package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.HotelBusiness;
import com.xi.xlm.entity.HotelIntegralInfo;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.service.IHotelBusinessService;
import com.xi.xlm.service.IHotelIntegralInfoService;
import com.xi.xlm.service.IWebMemberService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-15
 */
@Controller
@RequestMapping("/m/hotelBusiness")
@AllArgsConstructor
public class HotelBusinessController {
    private IHotelBusinessService iHotelBusinessService;
    private IWebMemberService iWebMemberService;
    private IHotelIntegralInfoService iHotelIntegralInfoService;

    @GetMapping(value = "show")
    @RequiresPermissions("hotel:hotelBusiness")
    public String show() {
        return "/hotel/hotelBusiness/list";
    }


    @GetMapping(value = "showList")
    @ResponseBody
    public Result showList(String memberPhone,Long page, Long limit) {
        Page<HotelBusiness> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<HotelBusiness> list = iHotelBusinessService.page(pages, Wrappers.<HotelBusiness>lambdaQuery().like(StringUtils.hasText(memberPhone),HotelBusiness::getMemberPhone,memberPhone));
        return Result.ok(list);
    }


    @GetMapping(value = "toUpdate")
   // @RequiresPermissions("hotel:updateCase")
    public String toUpdate(Model model, String id) {
        model.addAttribute("model", iHotelBusinessService.getById(id));
        return "/hotel/hotelBusiness/update";
    }

    @GetMapping(value = "toDetails")
    public String toDetails(Model model, String id) {
        model.addAttribute("model", iHotelBusinessService.getById(id));
        return "/hotel/hotelBusiness/details";
    }


    @PostMapping("no")
    @ResponseBody
    public Result no(String id) {
        HotelBusiness b = iHotelBusinessService.getById(id);
        b.setAudit(2);
        iHotelBusinessService.updateById(b);
        return Result.ok();
    }



    @PostMapping("update")
    @ResponseBody
    public Result update(HotelBusiness parameter) {
        HotelBusiness b = iHotelBusinessService.getById(parameter.getId());
        b.setAudit(3);
        b.setThisIntegral(parameter.getThisIntegral());
        iHotelBusinessService.updateById(b);
        WebMember webMember =iWebMemberService.getById(b.getMemberId());
        webMember.setIntegral(webMember.getIntegral().add(parameter.getThisIntegral()));
        iWebMemberService.updateById(webMember);

        HotelIntegralInfo hotelIntegralInfo = new HotelIntegralInfo();
        hotelIntegralInfo.setMemberId(webMember.getId());
        hotelIntegralInfo.setNickName(webMember.getNickName());
        hotelIntegralInfo.setThisIntegral(parameter.getThisIntegral().intValue());
        hotelIntegralInfo.setIntegralCount(webMember.getIntegral().intValue());
        hotelIntegralInfo.setIntegralType(2);
        hotelIntegralInfo.setHotelName(b.getHotelName());
        hotelIntegralInfo.setBusinessId(b.getId());
        iHotelIntegralInfoService.save(hotelIntegralInfo);

        return Result.ok();
    }
    

}
