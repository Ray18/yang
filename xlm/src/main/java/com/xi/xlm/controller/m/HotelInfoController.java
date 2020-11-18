package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.util.Result;
import com.xi.xlm.entity.HotelInfo;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.service.IHotelInfoService;
import com.xi.xlm.service.IWebMemberService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-21
 */
@Controller
@RequestMapping("/m/hotelInfo")
@AllArgsConstructor
public class HotelInfoController {
    private IHotelInfoService iHotelInfoService;
    private IWebMemberService iWebMemberService;

    @GetMapping(value = "showEmployee")
    @RequiresPermissions("hotel:showEmployee")
    public String showEmployee() {
        return "/hotel/hotelInfo/employeeList";
    }

    @GetMapping(value = "showEmployeeList")
    @ResponseBody
    public Result showEmployeeList(String name, String jobNumber, Long page, Long limit) {
        Page<HotelInfo> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<HotelInfo> list = iHotelInfoService.page(pages, Wrappers.<HotelInfo>lambdaQuery().eq(HotelInfo::getStatus, 1).eq(StringUtils.hasText(name), HotelInfo::getName, name).eq(StringUtils.hasText(jobNumber), HotelInfo::getJobNumber, jobNumber).orderByDesc(HotelInfo::getCreateBy));
        return Result.ok(list);
    }


    @GetMapping(value = "show")
    @RequiresPermissions("hotel:hotelInfo")
    public String show() {
        return "/hotel/hotelInfo/list";
    }


    @GetMapping(value = "showList")
    @ResponseBody
    public Result showList(Long page, Long limit) {
        Page<HotelInfo> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<HotelInfo> list = iHotelInfoService.page(pages, Wrappers.<HotelInfo>lambdaQuery().eq(HotelInfo::getStatus, 0).orderByAsc(HotelInfo::getStatus));
        return Result.ok(list);
    }


    @PostMapping("audit")
    @ResponseBody
    public Result audit(String id, Boolean type) {
        HotelInfo hotelInfo = iHotelInfoService.getById(id);
        if (type) {
            hotelInfo.setStatus(1);
            WebMember webMember = iWebMemberService.getById(hotelInfo.getMemberId());
            webMember.setHotelFlag(type);
            iWebMemberService.updateById(webMember);
        } else {
            hotelInfo.setStatus(2);
        }

        return Result.ok(iHotelInfoService.updateById(hotelInfo));
    }


    @PostMapping("upOrDown")
    @ResponseBody
    public Result upOrDown(String id, Boolean type) {
        HotelInfo hotelInfo = iHotelInfoService.getById(id);
        WebMember webMember = iWebMemberService.getById(hotelInfo.getMemberId());
        hotelInfo.setUp(type);
        webMember.setHotelFlag(type);
        iHotelInfoService.updateById(hotelInfo);
        iWebMemberService.updateById(webMember);

        return Result.ok();
    }


}
