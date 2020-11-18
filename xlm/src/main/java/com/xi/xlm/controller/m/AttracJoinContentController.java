package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.util.Result;
import com.xi.xlm.entity.AttracJoinContent;
import com.xi.xlm.entity.Banner;
import com.xi.xlm.request.ListAJCRequest;
import com.xi.xlm.request.ListBannerRequest;
import com.xi.xlm.service.AttracJoinContentService;
import com.xi.xlm.service.BannerService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;


@RequestMapping("/qj/joincontent")
@Controller
public class AttracJoinContentController {

    @Autowired
    private AttracJoinContentService attracJoinContentService;

    @GetMapping(value = "showJoinContent")
    @RequiresPermissions("attract:showJoinContent")
    public String showBanner(Model model) {
        return "/attrac/joincontent/joincontentList";
    }

    @GetMapping(value = "showJoinContentList")
    @ResponseBody
    public Result showGoods(Long page, Long limit) {
        ListAJCRequest request = new ListAJCRequest();
        Page<AttracJoinContent> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        request.setPage(pages);
        IPage<AttracJoinContent> list = attracJoinContentService.list(request);
        return Result.ok(list);
    }

    @RequiresPermissions("attract:updateJoinContent")
    @GetMapping(value = "updateJoinContent")
    public String updateBanner(String id, Model model, boolean detail) {

        AttracJoinContent attracJoinContent = attracJoinContentService.getById(id);
        model.addAttribute("attracJoinContent", attracJoinContent);
        return "/attrac/joincontent/update-joincontent";
    }

    @RequiresPermissions("attract:updateJoinContent")
    @PostMapping(value = "updateJoinContent")
    @ResponseBody
    public Result updateBanner(AttracJoinContent attracJoinContent) {
        attracJoinContentService.updateJoinContent(attracJoinContent);
        return Result.ok();
    }

}
