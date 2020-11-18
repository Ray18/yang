package com.xi.xlm.controller.m;


import com.xi.xlm.service.IAttractCommissionRuleService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-31
 */
@Controller
@RequestMapping("/m/attractCommissionRule")
@AllArgsConstructor
public class AttractCommissionRuleController {

    private IAttractCommissionRuleService iAttractCommissionRuleService;

    @GetMapping("show")
    @RequiresPermissions("attract:commissionRuleShow")
    public String show(){

        return "attrac/business/commissionRule/list";
    }



}
