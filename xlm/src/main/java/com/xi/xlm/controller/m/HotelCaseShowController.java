package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.HotelCaseShow;
import com.xi.xlm.service.IHotelCaseShowService;
import com.xi.xlm.service.IHotelCaseShowTypeService;
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
@RequestMapping("/m/hotelCaseShow")
@AllArgsConstructor
public class HotelCaseShowController extends BaseController {

    private IHotelCaseShowService iHotelCaseShowService;
    private IHotelCaseShowTypeService iHotelCaseShowTypeService;

    @GetMapping(value = "showCaseShow")
    @RequiresPermissions("hotel:caseShow")
    public String show() {
        return "/hotel/caseShow/list";
    }


    @GetMapping(value = "showCaseShowList")
    @ResponseBody
    public Result showCaseShowList(Long page, Long limit) {
        Page<HotelCaseShow> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<HotelCaseShow> list = iHotelCaseShowService.page(pages);
        return Result.ok(list);
    }


    @GetMapping(value = "toUpdateCaseShow")
    @RequiresPermissions("hotel:updateCaseShow")
    public String toUpdateParameter(Model model, String id) {
        model.addAttribute("model", iHotelCaseShowService.getById(id));
        model.addAttribute("type",iHotelCaseShowTypeService.list());
        return "/hotel/caseShow/update";
    }

    @GetMapping(value = "toAddCaseShow")
    @RequiresPermissions("hotel:toAddCaseShow")
    public String toAddCase(Model model) {
        model.addAttribute("model",iHotelCaseShowTypeService.list());
        return "/hotel/caseShow/add";
    }


    @PostMapping("add")
    @ResponseBody
    public Result add(HotelCaseShow parameter) {
        parameter.setTypeName(iHotelCaseShowTypeService.getById(parameter.getType()).getName());
        iHotelCaseShowService.save(parameter);
        return Result.ok();
    }

    @PostMapping("del")
    @RequiresPermissions("hotel:caseShowDel")
    @ResponseBody
    public Result del(String id) {
        iHotelCaseShowService.removeById(id);
        return Result.ok();
    }


    @PostMapping("update")
    @ResponseBody
    public Result update(HotelCaseShow parameter) {
        parameter.setTypeName(iHotelCaseShowTypeService.getById(parameter.getType()).getName());
        iHotelCaseShowService.updateById(parameter);
        return Result.ok();
    }


    @PostMapping("upOrDown")
    @ResponseBody
    public Result upOrDown(String id, Boolean type) {
        HotelCaseShow parameter = iHotelCaseShowService.getById(id);
        parameter.setUp(type);
        iHotelCaseShowService.updateById(parameter);
        return Result.ok();
    }

}
