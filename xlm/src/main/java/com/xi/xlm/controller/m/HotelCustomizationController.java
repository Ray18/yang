package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.HotelCustomization;
import com.xi.xlm.service.IHotelCustomizationService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@Controller
@RequestMapping("/m/hotelCustomization")
@AllArgsConstructor
public class HotelCustomizationController extends BaseController {
    private IHotelCustomizationService iHotelCustomizationService;

    @GetMapping(value = "show")
    @RequiresPermissions("hotel:customization")
    public String show() {
        return "/hotel/customization/list";
    }


    @GetMapping(value = "showList")
    @ResponseBody
    public Result showList(Long page, Long limit) {
        Page<HotelCustomization> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<HotelCustomization> list = iHotelCustomizationService.page(pages);
        return Result.ok(list);
    }


}
