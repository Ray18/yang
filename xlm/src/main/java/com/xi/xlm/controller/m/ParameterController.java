package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.Parameter;
import com.xi.xlm.service.IParameterService;
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
@RequestMapping("/m/parameter")
@AllArgsConstructor
public class ParameterController extends BaseController {

    private IParameterService iParameterService;

    @GetMapping(value = "showParameter")
    @RequiresPermissions("sys:parameter")
    public String show() {
        return "/hotel/sys/parameter-list";
    }


    @GetMapping(value = "showParameterList")
    @ResponseBody
    public Result showParameterList(Long page, Long limit) {
        Page<Parameter> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<Parameter> list = iParameterService.page(pages);
        return Result.ok(list);
    }


    @GetMapping(value = "toUpdateParameter")
    @RequiresPermissions("sys:updateParameter")
    public String toUpdateParameter(Model model, String id) {
        model.addAttribute("model", iParameterService.getById(id));
        return "/hotel/sys/parameter-update";
    }

    @GetMapping(value = "toAddParameter")
    @RequiresPermissions("sys:toParameter")
    public String toAddParameter() {
        return "/hotel/sys/parameter-add";
    }


    @PostMapping("add")
    @ResponseBody
    public Result add(Parameter parameter) {

        QueryWrapper<Parameter> wrapper = new QueryWrapper<>();
        wrapper.eq(Parameter.CODE, parameter.getCode());
        if (iParameterService.getOne(wrapper) != null) {

            return Result.error("code重复！");
        }
        iParameterService.save(parameter);
        return Result.ok();
    }

    @PostMapping("del")
    @RequiresPermissions("sys:parameterDel")
    @ResponseBody
    public Result del(String id) {
        iParameterService.removeById(id);
        return Result.ok();
    }


    @PostMapping("update")
    @ResponseBody
    public Result update(Parameter parameter) {
        iParameterService.updateById(parameter);
        return Result.ok();
    }


}
