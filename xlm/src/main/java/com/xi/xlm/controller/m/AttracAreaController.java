package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.util.Result;
import com.xi.xlm.entity.AttracArea;
import com.xi.xlm.service.IAttracAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-07
 */
@Controller
@RequestMapping("/m/attracArea")
public class AttracAreaController {
    @Autowired IAttracAreaService attracAreaService;

    @GetMapping(value = "showAttracAreaList")
    @ResponseBody
//    @RequiresPermissions("attract:showAttracAreaShow")
    public Result areaShow(@RequestParam(defaultValue = "0") Long page, @RequestParam(defaultValue = "20")Long limit,@RequestParam(defaultValue = "0")String type) {
        Page<AttracArea> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<AttracArea> list = attracAreaService.list(pages,type);
        return Result.ok(list);
    }


//    @RequiresPermissions("attract:showAreaList")
    @GetMapping(value = "showAreaList")
    public String showAreaList(Model model) {
        return "/attrac/area/showAreaList";
    }

//    @RequiresPermissions("attract:showAddArea")
    @GetMapping(value = "showAddArea")
    public String showAddArea(Model model) {
        List<AttracArea> list = attracAreaService.areaByParentId("0");
        model.addAttribute("parentAreas", list);
        return "/attrac/area/add-area";
    }


    @PostMapping(value = "addArea")
    @ResponseBody
    public Result addArea(AttracArea attracArea) {
        Integer integer = attracAreaService.addArea(attracArea);
        return integer>=1?Result.ok():Result.error("操作失败");
    }


//    @RequiresPermissions("attract:delArea")
    @PostMapping(value = "delArea")
    @ResponseBody
    public Result delArea(String id) {
        Integer integer = attracAreaService.delById(id);
        return integer>=1?Result.ok():Result.error("操作失败");
    }


//    @RequiresPermissions("attract:updateArea")
    @GetMapping(value = "showUpdateArea")
    public String updateArea(String id, Model model, boolean detail) {
        AttracArea attracArea = attracAreaService.getById(id);
        model.addAttribute("attracArea", attracArea);
        return "/attrac/area/update-area";
    }


    @PostMapping(value = "updateArea")
    @ResponseBody
    public Result updateArea(AttracArea attracArea) {
        Integer integer = attracAreaService.updateArea(attracArea);
        return integer>=1?Result.ok():Result.error("操作失败");
    }
}
