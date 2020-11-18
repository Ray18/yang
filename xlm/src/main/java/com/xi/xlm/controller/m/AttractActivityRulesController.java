package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.util.Result;
import com.xi.xlm.entity.AttractActivityRules;
import com.xi.xlm.service.IAttractActivityRulesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-08
 */
@Controller
@RequestMapping("/m/attractActivityRules")
public class AttractActivityRulesController {
    @Autowired
    private IAttractActivityRulesService iAttractActivityRulesService;

    @GetMapping(value = "showActivity")
    @RequiresPermissions("attract:showActivity")
    public String showBanner(Model model) {
        return "/attrac/business/act/actList";
    }

    @GetMapping(value = "showActivityList")
    @ResponseBody
    public Result showGoods(Long page, Long limit) {
        Page<AttractActivityRules> pg = new Page<>();
        pg.setCurrent(page);
        pg.setSize(limit);
        return Result.ok(iAttractActivityRulesService.page(pg));
    }

    @GetMapping(value = "updateActivity")
    public String updateBanner(String id, Model model) {
        model.addAttribute("model", iAttractActivityRulesService.getById(id));
        return "/attrac/business/act/update-act";
    }

    @PostMapping(value = "updateActivity")
    @ResponseBody
    public Result updateActivity(AttractActivityRules rules) {
        iAttractActivityRulesService.updateById(rules);
        return Result.ok();
    }

}
