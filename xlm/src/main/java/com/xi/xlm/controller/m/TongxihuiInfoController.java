package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.base.BaseController;
import com.len.util.Result;
import com.xi.xlm.entity.TongxihuiInfo;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.service.ITongxihuiInfoService;
import com.xi.xlm.service.IWebMemberService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-01
 */
@Controller
@RequestMapping("/m/tongxihuiInfo")
@AllArgsConstructor
public class TongxihuiInfoController extends BaseController {

    private ITongxihuiInfoService iTongxihuiInfoService;
    private IWebMemberService iWebMemberService;

    @GetMapping("show")
    @RequiresPermissions("attract:tongxihuiInfoShow")
    public String show() {
        return "attrac/business/tongxihui/list";
    }

    @GetMapping("showList")
    @ResponseBody
    public Result showList(Long page, Long limit) {
        Page<TongxihuiInfo> infoPage = new Page<>();
        infoPage.setCurrent(page);
        infoPage.setSize(limit);

        return Result.ok(iTongxihuiInfoService.listPage(infoPage));
    }

    @PostMapping("audit")
    @RequiresPermissions("attract:tongxihuiInfoAudit")
    @ResponseBody
    public Result audit(String id, boolean type) {
        if (StringUtils.isBlank(id)) {

            return Result.error("请传入会员");
        }
        TongxihuiInfo tongxihuiInfo = iTongxihuiInfoService.getById(id);
        if (type) {
            tongxihuiInfo.setStatus(1);
            //默认会员等级待确认
            //TODO：此处基础会员信息标识
            //TODO:标识基础会员信息
            WebMember webMember = iWebMemberService.getById(tongxihuiInfo.getMemberId());
            if (webMember != null) {
                webMember.setTongxihuiFlag(true);
                iWebMemberService.updateById(webMember);

            }

        } else {
            tongxihuiInfo.setStatus(2);
        }

        iTongxihuiInfoService.updateById(tongxihuiInfo);
        return Result.ok();
    }


}
