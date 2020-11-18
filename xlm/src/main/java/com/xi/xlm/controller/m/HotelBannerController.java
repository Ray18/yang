package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.HotelBanner;
import com.xi.xlm.service.IHotelBannerService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/m/hotelBanner")
@AllArgsConstructor
public class HotelBannerController  extends BaseController {

    private IHotelBannerService iHotelBannerService;


    @GetMapping(value = "showBanner")
    @RequiresPermissions("hotel:banner")
    public String show() {
        return "/hotel/banner/list";
    }


    @GetMapping(value = "showBannerList")
    @ResponseBody
    public Result showBannerList(Long page, Long limit) {
        Page<HotelBanner> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<HotelBanner> list = iHotelBannerService.page(pages);
        return Result.ok(list);
    }


    @GetMapping(value = "toUpdateBanner")
    @RequiresPermissions("hotel:updateBanner")
    public String toUpdateParameter(Model model, String id) {
        model.addAttribute("banner", iHotelBannerService.getById(id));
        return "/hotel/banner/update";
    }

    @GetMapping(value = "toAddBanner")
    @RequiresPermissions("hotel:toAddBanner")
    public String toAddBanner() {
        return "/hotel/banner/add";
    }


    @PostMapping("add")
    @ResponseBody
    public Result add(HotelBanner parameter) {
        iHotelBannerService.save(parameter);
        return Result.ok();
    }

    @PostMapping("del")
    @RequiresPermissions("hotel:bannerDel")
    @ResponseBody
    public Result del(String id) {
        iHotelBannerService.removeById(id);
        return Result.ok();
    }


    @PostMapping("update")
    @ResponseBody
    public Result update(HotelBanner parameter) {
        iHotelBannerService.updateById(parameter);
        return Result.ok();
    }


    @PostMapping("upOrDown")
    @ResponseBody
    public Result upOrDown(String id, Boolean type) {
        HotelBanner parameter = iHotelBannerService.getById(id);
        parameter.setUpState(type);
        iHotelBannerService.updateById(parameter);
        return Result.ok();
    }

}
