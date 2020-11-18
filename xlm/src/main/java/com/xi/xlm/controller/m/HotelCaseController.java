package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.HotelCase;
import com.xi.xlm.service.IHotelCaseService;
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
@RequestMapping("/m/hotelCase")
@AllArgsConstructor
public class HotelCaseController  extends BaseController {
    private IHotelCaseService iHotelCaseService;


    @GetMapping(value = "show")
    @RequiresPermissions("hotel:case")
    public String show() {
        return "/hotel/case/list";
    }


    @GetMapping(value = "showList")
    @ResponseBody
    public Result showCaseList(Long page, Long limit) {
        Page<HotelCase> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<HotelCase> list = iHotelCaseService.page(pages);
        return Result.ok(list);
    }


    @GetMapping(value = "toUpdate")
    @RequiresPermissions("hotel:updateCase")
    public String toUpdateParameter(Model model, String id) {
        model.addAttribute("model", iHotelCaseService.getById(id));
        return "/hotel/case/update";
    }

    @GetMapping(value = "toAdd")
    @RequiresPermissions("hotel:toAddCase")
    public String toAddCase() {
        return "/hotel/case/add";
    }


    @PostMapping("add")
    @ResponseBody
    public Result add(HotelCase parameter) {
        iHotelCaseService.save(parameter);
        return Result.ok();
    }

    @PostMapping("del")
    @RequiresPermissions("hotel:caseDel")
    @ResponseBody
    public Result del(String id) {
        iHotelCaseService.removeById(id);
        return Result.ok();
    }


    @PostMapping("update")
    @ResponseBody
    public Result update(HotelCase parameter) {
        iHotelCaseService.updateById(parameter);
        return Result.ok();
    }


    @PostMapping("upOrDown")
    @ResponseBody
    public Result upOrDown(String id, Boolean type) {
        HotelCase parameter = iHotelCaseService.getById(id);
        parameter.setUp(type);
        iHotelCaseService.updateById(parameter);
        return Result.ok();
    }

}
