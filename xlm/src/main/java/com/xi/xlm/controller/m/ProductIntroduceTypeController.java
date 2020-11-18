package com.xi.xlm.controller.m;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.core.annotation.Log;
import com.len.util.Result;
import com.xi.xlm.entity.AttracProductIntroduce;
import com.xi.xlm.entity.AttracProductIntroduceType;
import com.xi.xlm.request.ListAPITRequest;
import com.xi.xlm.service.ProductIntroduceTypeService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/qj/apit")
@Controller
public class ProductIntroduceTypeController extends BaseController {
    @Autowired
    ProductIntroduceTypeService productIntroduceTypeService;

    @GetMapping(value = "selectAll")
    @ResponseBody
    public Result selectall() {
        List<AttracProductIntroduceType> oList = productIntroduceTypeService.selectAll();
        return Result.ok(oList);
    }

    @GetMapping(value = "showType")
    @RequiresPermissions("attrac:showType")
    public String showType(Model model) {
        return "/attrac/type/typeList";
    }

    @GetMapping(value = "showTypeList")
    @ResponseBody
    public Result showTypeList(Long page, Long limit) {
        ListAPITRequest request = new ListAPITRequest();
        Page<AttracProductIntroduceType> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        request.setPage(pages);
        IPage<AttracProductIntroduceType> list = productIntroduceTypeService.list(request);
        return Result.ok(list);
    }

    @GetMapping(value = "showAddType")
    @RequiresPermissions("attrac:showAddType")
    public String showAddType(Model model) {
        List<AttracProductIntroduceType> oList = productIntroduceTypeService.selectAll();
        model.addAttribute("type", oList);
        return "/attrac/type/add-type";
    }

    @RequiresPermissions("attrac:showAddType")
    @PostMapping(value = "addType")
    @ResponseBody
    public Result addType(AttracProductIntroduceType attracProductIntroduceType) {
        productIntroduceTypeService.addType(attracProductIntroduceType);
        return Result.ok();
    }

    @RequiresPermissions("attrac:delType")
    @PostMapping(value = "delType")
    @ResponseBody
    public Result delType(String id) {
        AttracProductIntroduce att = productIntroduceTypeService.getByType(id);
        System.out.println(att);
        if (att != null) {
            Result result = new Result();
            result.setFlag(false);
            result.setMsg("分类下有产品无法删除");
            return result;
        } else {
            productIntroduceTypeService.delById(id);
            return Result.ok();
        }
    }

    @RequiresPermissions("attrac:updateType")
    @GetMapping(value = "updateType")
    public String updateType(String id, Model model, boolean detail) {
        AttracProductIntroduceType attracProductIntroduceType = productIntroduceTypeService.getById(id);
        model.addAttribute("type", attracProductIntroduceType);
        return "/attrac/type/update-type";
    }

    @RequiresPermissions("attrac:updateType")
    @PostMapping(value = "updateType")
    @ResponseBody
    public Result updateType(AttracProductIntroduceType attracProductIntroduceType) {
        productIntroduceTypeService.updateType(attracProductIntroduceType);
        return Result.ok();
    }
}
