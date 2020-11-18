package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.AttracArea;
import com.xi.xlm.entity.AttractDealerInfo;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.service.IAttracAreaService;
import com.xi.xlm.service.IAttractDealerInfoService;
import com.xi.xlm.service.IWebMemberService;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.geom.Area;

/**
 * <p>
 *
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-27
 */
@Controller
@RequestMapping("/m/attractDealerInfo")
@AllArgsConstructor
public class AttractDealerInfoController extends BaseController {
    IAttractDealerInfoService iAttractDealerInfoService;
    IWebMemberService iWebMemberService;

    @GetMapping(value = "showAttractDealerInfo")
    @RequiresPermissions("attract:attractDealerInfoShow")
    public String show() {
        return "/attrac/allianceBusiness/list";
    }


    @GetMapping(value = "showAttractDealerInfoList")
    @ResponseBody
    @RequiresPermissions("attract:attractDealerInfoShow")
    public Result productShow(Long page, Long limit) {
        Page<AttractDealerInfo> pages = new Page<>();
        pages.setSize(limit);
        pages.setCurrent(page);
        IPage<AttractDealerInfo> list = iAttractDealerInfoService.list(pages);
        return Result.ok(list);
    }

    @PostMapping("audit")
    @ResponseBody
    @RequiresPermissions("attract:attractDealerInfoAudit")
    public Result audit(String id, boolean type) {
        AttractDealerInfo info = iAttractDealerInfoService.getById(id);
        if (type) {
            info.setStatus(1);

            WebMember webMember = iWebMemberService.getById(info.getMemberId());
            if (webMember != null) {
                webMember.setDealerFlag(true);
                iWebMemberService.updateById(webMember);

            }
        }
        if (!type) {
            info.setStatus(2);
        }

        iAttractDealerInfoService.updateById(info);
        return Result.ok();

    }

}
