package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.HotelCaseShow;
import com.xi.xlm.entity.HotelCaseShowType;
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

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@Controller
@RequestMapping("/m/hotelCaseShowType")
@AllArgsConstructor
public class HotelCaseShowTypeController  extends BaseController {

    private IHotelCaseShowTypeService iHotelCaseShowTypeService;

    private IHotelCaseShowService iHotelCaseShowService;


    @GetMapping(value = "showCaseType")
    @RequiresPermissions("hotel:caseTypeList")
    public String show() {
        return "/hotel/caseType/list";
    }


    @GetMapping(value = "showCaseTypeList")
    @ResponseBody
    public Result showCaseTypeList(Long page, Long limit) {
        Page<HotelCaseShowType> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<HotelCaseShowType> list = iHotelCaseShowTypeService.page(pages);
        return Result.ok(list);
    }


    @GetMapping(value = "toUpdateCaseType")
    @RequiresPermissions("hotel:updateCaseType")
    public String toUpdateParameter(Model model, String id) {
        model.addAttribute("model", iHotelCaseShowTypeService.getById(id));
        return "/hotel/caseType/update";
    }

    @GetMapping(value = "toAddCaseType")
    @RequiresPermissions("hotel:toAddCaseType")
    public String toAddCaseType() {
        return "/hotel/caseType/add";
    }


    @PostMapping("add")
    @ResponseBody
    public Result add(HotelCaseShowType parameter) {
        iHotelCaseShowTypeService.save(parameter);
        return Result.ok();
    }

    @PostMapping("del")
    @RequiresPermissions("hotel:caseTypeDel")
    @ResponseBody
    public Result del(String id) {

        HotelCaseShowType type = iHotelCaseShowTypeService.getById(id);
        QueryWrapper<HotelCaseShow> showQueryWrapper = new QueryWrapper<>();
        showQueryWrapper.eq(HotelCaseShow.TYPE, type.getId());
        List<HotelCaseShow> list = iHotelCaseShowService.list(showQueryWrapper);
        if (list.size() > 0) {
            return Result.error("该分类下有产品,不允许删除");
        }

        iHotelCaseShowTypeService.removeById(id);
        return Result.ok();
    }


    @PostMapping("update")
    @ResponseBody
    public Result update(HotelCaseShowType parameter) {
        HotelCaseShowType type = iHotelCaseShowTypeService.getById(parameter.getId());
        QueryWrapper<HotelCaseShow> showQueryWrapper = new QueryWrapper<>();
        showQueryWrapper.eq(HotelCaseShow.TYPE, type.getId());
        List<HotelCaseShow> list = iHotelCaseShowService.list(showQueryWrapper);
        if (list.size() > 0) {
            return Result.error("该分类下有产品,不允许编辑");
        }

        iHotelCaseShowTypeService.updateById(parameter);
        return Result.ok();
    }


    @PostMapping("upOrDown")
    @ResponseBody
    public Result upOrDown(String id, Boolean type) {
        HotelCaseShowType parameter = iHotelCaseShowTypeService.getById(id);
        iHotelCaseShowTypeService.updateById(parameter);
        return Result.ok();
    }

}
