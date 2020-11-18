package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.util.Result;
import com.xi.xlm.entity.HotelCase;
import com.xi.xlm.entity.WalkContent;
import com.xi.xlm.service.IWalkContentService;
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
 * @since 2020-10-09
 */
@Controller
@RequestMapping("/m/walkContent")
@AllArgsConstructor
public class WalkContentController {

    private IWalkContentService iWalkContentService;


    @GetMapping(value = "showWalk")
    @RequiresPermissions("walk:walk")
    public String show() {
        return "/hotel/walk/list";
    }


    @GetMapping(value = "showWalkList")
    @ResponseBody
    public Result showWalkList(Long page, Long limit) {
        Page<WalkContent> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<WalkContent> list = iWalkContentService.page(pages);
        return Result.ok(list);
    }

    @PostMapping("add")
    @ResponseBody
    public Result add(WalkContent parameter) {
        iWalkContentService.save(parameter);
        return Result.ok();
    }

    @GetMapping(value = "toUpdateWalk")
    @RequiresPermissions("walk:updateWalk")
    public String toUpdateWalk(Model model, String id) {
        model.addAttribute("model", iWalkContentService.getById(id));
        return "/hotel/walk/update";
    }

    @GetMapping(value = "toAddWalk")
    @RequiresPermissions("walk:toWalk")
    public String toAddWalk() {
        return "/hotel/walk/add";
    }


    @PostMapping("del")
    @RequiresPermissions("walk:walkDel")
    @ResponseBody
    public Result del(String id) {
        iWalkContentService.removeById(id);
        return Result.ok();
    }


    @PostMapping("update")
    @ResponseBody
    public Result update(WalkContent walk) {
        iWalkContentService.updateById(walk);
        return Result.ok();
    }

}
