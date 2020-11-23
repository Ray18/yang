package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.util.Result;
import com.xi.xlm.entity.Banner;
import com.xi.xlm.entity.Fwzx;
import com.xi.xlm.service.FwzxService;
import com.xi.xlm.request.ListBannerRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/m/fwzx")
@Controller
public class FwzxController {
    @Autowired
    private FwzxService fwzxService;

    @RequiresPermissions("fwzx:show")
    @GetMapping(value = "update")
    public String showBanner(Model model) {
        Fwzx fwzx = fwzxService.getById(1);
        model.addAttribute("fwzx", fwzx);
        return "/attrac/fwzx/update";
    }

    @RequiresPermissions("fwzx:show")
    @PostMapping(value = "updateFwzx")
    @ResponseBody
    public Result updateFwzx(Fwzx fwzx) {
        fwzxService.updateFwzx(fwzx);
        return Result.ok();
    }

}
