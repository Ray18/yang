package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.AttracProductIntroduce;
import com.xi.xlm.request.ListAPIRequest;
import com.xi.xlm.service.ProductIntroduceService;
import com.xi.xlm.service.ProductIntroduceTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/qj/introduce")
@Controller
public class ProducIntroduceController extends BaseController {
    @Autowired
    ProductIntroduceService productIntroduceService;

    @Autowired
    ProductIntroduceTypeService productIntroduceTypeService;

    @RequiresPermissions("attract:showIntroduce")
    @GetMapping(value = "showIntroduce")
    public String showIntroduce(Model model) {
        return "/attrac/introduce/introduceList";
    }

    @RequiresPermissions("attract:showIntroduce")
    @GetMapping(value = "showIntroduceList")
    @ResponseBody
    public Result showIntroduce(Long page, Long limit) {
        ListAPIRequest request = new ListAPIRequest();
        Page<AttracProductIntroduce> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        request.setPage(pages);
        IPage<AttracProductIntroduce> list = productIntroduceService.list(request);
        return Result.ok(list);
    }

    @RequiresPermissions("attract:showAddIntroduce")
    @GetMapping(value = "showAddIntroduce")
    public String goAddIntroduce(Model model) {
        return "/attrac/introduce/add-introduce";
    }

    @RequiresPermissions("attract:showIntroduceDetail")
    @GetMapping(value = "showIntroduceDetail")
    public String showIntroduceDetail(String id, Model model, boolean detail) {
        AttracProductIntroduce introduce = productIntroduceService.getById(id);
        model.addAttribute("introduce", introduce);
        return "/attrac/introduce/show-introduce";
    }

    @RequiresPermissions("attract:showAddIntroduce")
    @PostMapping(value = "addIntroduce")
    @ResponseBody
    public Result addIntroduce(AttracProductIntroduce attracProductIntroduce) {
        productIntroduceService.addIntroduce(attracProductIntroduce);
        return Result.ok();
    }

    @RequiresPermissions("attract:delIntroduce")
    @PostMapping(value = "delIntroduce")
    @ResponseBody
    public Result delIntroduce(String id) {
        productIntroduceService.delById(id);
        return Result.ok();
    }

    @RequiresPermissions("attract:updateIntroduce")
    @GetMapping(value = "updateIntroduce")
    public String updateIntroduce(String id, Model model, boolean detail) {
        AttracProductIntroduce introduce = productIntroduceService.getById(id);
        model.addAttribute("introduce", introduce);
        //查询所有种类
        model.addAttribute("type", productIntroduceTypeService.list());
        return "/attrac/introduce/update-introduce";
    }

    @RequiresPermissions("attract:updateIntroduce")
    @PostMapping(value = "updateIntroduces")
    @ResponseBody
    public Result updateIntroduces(AttracProductIntroduce attracProductIntroduce) {
        attracProductIntroduce.setTypeName(productIntroduceTypeService.getById(attracProductIntroduce.getType()).getName());
        productIntroduceService.updateById(attracProductIntroduce);
        return Result.ok();
    }

}
