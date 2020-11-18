package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.util.Result;
import com.xi.xlm.entity.AttracCityCase;
import com.xi.xlm.request.ListACCRequest;
import com.xi.xlm.service.AttracCityCaseService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/qj/citycase")
@Controller
public class AttracCityCaseController {
    @Autowired
    AttracCityCaseService attracCityCaseService;


    @RequiresPermissions("attract:showCityCase")
    @GetMapping(value = "showCityCase")
    public String showCityCase(Model model) {
        return "/attrac/citycase/citycaseList";
    }


    @RequiresPermissions("attract:showCityCase")
    @GetMapping(value = "showCityCaseList")
    @ResponseBody
    public Result showCityCaseList(Long page, Long limit) {
        ListACCRequest request = new ListACCRequest();
        Page<AttracCityCase> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        request.setPage(pages);
        IPage<AttracCityCase> list = attracCityCaseService.list(request);
        return Result.ok(list);
    }


    @RequiresPermissions("attract:showAddCityCase")
    @GetMapping(value = "showAddCityCase")
    public String showAddCityCase(Model model) {
        return "/attrac/citycase/add-citycase";
    }


    @PostMapping(value = "addCityCase")
    @ResponseBody
    public Result addCityCase(AttracCityCase attracCityCase) {
        attracCityCaseService.addCityCase(attracCityCase);
        return Result.ok();
    }


    @RequiresPermissions("attract:delCityCase")
    @PostMapping(value = "delCityCase")
    @ResponseBody
    public Result delCityCase(String id) {
        attracCityCaseService.delById(id);
        return Result.ok();
    }


    @RequiresPermissions("attract:updateCityCase")
    @GetMapping(value = "updateCityCase")
    public String updateCityCase(String id, Model model, boolean detail) {

        AttracCityCase attraccitycase = attracCityCaseService.getById(id);
        model.addAttribute("attraccitycase", attraccitycase);
        return "/attrac/citycase/update-citycase";
    }


    @PostMapping(value = "updateCityCase")
    @ResponseBody
    public Result updateCityCase(AttracCityCase attracCityCase) {
        attracCityCaseService.updateCityCase(attracCityCase);
        return Result.ok();
    }

}
