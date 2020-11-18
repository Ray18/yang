package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.HotelInformation;
import com.xi.xlm.service.IHotelInformationService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@Controller
@RequestMapping("/m/hotelInformation")
@AllArgsConstructor
public class HotelInformationController extends BaseController {

    private IHotelInformationService iHotelInformationService;

    @GetMapping(value = "show")
    @RequiresPermissions("hotel:information")
    public String show() {
        return "/hotel/information/list";
    }


    @GetMapping(value = "showList")
    @ResponseBody
    public Result showInformationList(Long page, Long limit) {
        Page<HotelInformation> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<HotelInformation> list = iHotelInformationService.page(pages);
        return Result.ok(list);
    }


    @GetMapping(value = "toUpdate")
    @RequiresPermissions("hotel:updateInformation")
    public String toUpdateParameter(Model model, String id) {
        model.addAttribute("model", iHotelInformationService.getById(id));
        return "/hotel/information/update";
    }

    @GetMapping(value = "toAdd")
    @RequiresPermissions("hotel:toAddInformation")
    public String toAddInformation() {
        return "/hotel/information/add";
    }


    @PostMapping("add")
    @ResponseBody
    public Result add(HotelInformation parameter) {
        iHotelInformationService.save(parameter);
        return Result.ok();
    }

    @PostMapping("del")
    @RequiresPermissions("hotel:informationDel")
    @ResponseBody
    public Result del(String id) {
        iHotelInformationService.removeById(id);
        return Result.ok();
    }


    @PostMapping("update")
    @ResponseBody
    public Result update(HotelInformation parameter) {
        iHotelInformationService.updateById(parameter);
        return Result.ok();
    }


    @PostMapping("upOrDown")
    @ResponseBody
    public Result upOrDown(String id, Boolean type) {
        HotelInformation parameter = iHotelInformationService.getById(id);
        iHotelInformationService.updateById(parameter);
        return Result.ok();
    }
    
    
}
