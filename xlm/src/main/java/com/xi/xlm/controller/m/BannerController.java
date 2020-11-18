package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.util.Result;
import com.xi.xlm.entity.Banner;
import com.xi.xlm.request.ListBannerRequest;
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
import java.util.Date;


@RequestMapping("/qj/banner")
@Controller
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequiresPermissions("attract:showBanner")
    @GetMapping(value = "showBanner")
    public String showBanner(Model model) {
        return "/attrac/banner/bannerList";
    }

    @GetMapping(value = "showBannerList")
    @ResponseBody
    @RequiresPermissions("attract:showBanner")
    public Result showBannerList(Long page, Long limit) {
        ListBannerRequest request = new ListBannerRequest();
        Page<Banner> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        request.setPage(pages);
        IPage<Banner> list = bannerService.list(request);

        return Result.ok(list);
    }

    @RequiresPermissions("attract:showAddBanner")
    @GetMapping(value = "showAddBanner")
    public String goAddUser(Model model) {
        return "/attrac/banner/add-banner";
    }

    @RequiresPermissions("attract:showAddBanner")
    @PostMapping(value = "addBanner")
    @ResponseBody
    public Result addBanner(Banner banner) {

        banner.setUpState("0");
        bannerService.addBanner(banner);

        return Result.ok();
    }

    @RequiresPermissions("attract:delBanner")
    @PostMapping(value = "delBanner")
    @ResponseBody
    public Result delGood(String id) {
        bannerService.delById(id);
        return Result.ok();
    }

    @RequiresPermissions("attract:updateBanner")
    @GetMapping(value = "updateBanner")
    public String updateBanner(String id, Model model, boolean detail) {

        Banner banner = bannerService.getById(id);
        model.addAttribute("banner", banner);
        return "/attrac/banner/update-banner";
    }

    @RequiresPermissions("attract:updateBanner")
    @PostMapping(value = "updateBanner")
    @ResponseBody
    public Result updateBanner(Banner banner) {
        bannerService.updateBanner(banner);
        return Result.ok();
    }

    @RequiresPermissions("attract:upstateBanner")
    @PostMapping(value = "upstateBanner")
    @ResponseBody
    public Result upstateBanner(Banner banner) {
        Banner banner1 = bannerService.getById(banner.getId());
        if (banner1.getUpState().equals("0")) {
            banner1.setUpState("1");
        } else {
            banner1.setUpState("0");
        }
        bannerService.upstate(banner1);
        return Result.ok();
    }
}
