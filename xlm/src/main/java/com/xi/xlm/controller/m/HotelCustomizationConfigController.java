package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.HotelCustomizationConfig;
import com.xi.xlm.service.IHotelCustomizationConfigService;
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
@RequestMapping("/m/hotelCustomizationConfig")
@AllArgsConstructor
public class HotelCustomizationConfigController extends BaseController {
    private IHotelCustomizationConfigService iHotelCustomizationConfigService;


    @GetMapping(value = "show")
    @RequiresPermissions("hotel:customizationConfig")
    public String show() {
        return "/hotel/customizationConfig/list";
    }


    @GetMapping(value = "showList")
    @ResponseBody
    public Result showCustomizationConfigList(Long page, Long limit) {
        Page<HotelCustomizationConfig> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<HotelCustomizationConfig> list = iHotelCustomizationConfigService.page(pages);
        return Result.ok(list);
    }


    @GetMapping(value = "toUpdate")
   // @RequiresPermissions("hotel:updateCustomizationConfig")
    public String toUpdateParameter(Model model, String id) {
        model.addAttribute("model", iHotelCustomizationConfigService.getById(id));
        return "/hotel/customizationConfig/update";
    }

    @GetMapping(value = "toAdd")
   // @RequiresPermissions("hotel:toAddCustomizationConfig")
    public String toAddCase() {
        return "/hotel/customizationConfig/add";
    }


    @PostMapping("add")
    @ResponseBody
    public Result add(HotelCustomizationConfig parameter) {
        iHotelCustomizationConfigService.save(parameter);
        return Result.ok();
    }

    @PostMapping("del")
  //  @RequiresPermissions("hotel:customizationConfigDel")
    @ResponseBody
    public Result del(String id) {
        iHotelCustomizationConfigService.removeById(id);
        return Result.ok();
    }


    @PostMapping("update")
    @ResponseBody
    public Result update(HotelCustomizationConfig parameter) {
        iHotelCustomizationConfigService.updateById(parameter);
        return Result.ok();
    }


}
