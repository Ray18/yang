package com.xi.xlm.controller.m;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.len.util.ExcelUtil;
import com.len.util.Result;
import com.xi.xlm.entity.AttractCommissionInfo;
import com.xi.xlm.entity.AttractCommissionRule;
import com.xi.xlm.entity.AttractThread;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.response.m.AttractThreadResp;
import com.xi.xlm.service.IAttractCommissionInfoService;
import com.xi.xlm.service.IAttractCommissionRuleService;
import com.xi.xlm.service.IAttractThreadService;
import com.xi.xlm.service.IWebMemberService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-31
 */
@Controller
@RequestMapping("/m/attractThread")
@AllArgsConstructor
public class AttractThreadController {

    private IAttractThreadService iAttractThreadService;
    private IAttractCommissionRuleService iAttractCommissionRuleService;
    private IAttractCommissionInfoService iAttractCommissionInfoService;
    private IWebMemberService iWebMemberService;

    @GetMapping("show")
    @RequiresPermissions("attract:attractThreadShow")
    public String show() {
        return "attrac/business/thread/list";
    }

    @GetMapping("showList")
    @ResponseBody
    public Result show(Long page, Long limit) {
        Page<AttractThread> pages = new Page<>();
        pages.setCurrent(page);
        pages.setSize(limit);
        return Result.ok(iAttractThreadService.list(pages));
    }


    @GetMapping("details")
    public String details(String id, Model model) {
        model.addAttribute("model", iAttractThreadService.getById(id));
        return "attrac/business/thread/details";
    }


    @PostMapping("del")
    public Result del(String id) {
        iAttractThreadService.removeById(id);
        return Result.ok();
    }

    //@RequiresPermissions("attract:ruleUpdate")
    @GetMapping("toUpdateRule")
    public String updateRule(Model model, String id) {
        QueryWrapper<AttractCommissionRule> wrapper = new QueryWrapper<>();
        wrapper.eq(AttractCommissionRule.THREAD_ID, id);
        model.addAttribute("rule", iAttractCommissionRuleService.getOne(wrapper));
        model.addAttribute("model", iAttractThreadService.getById(id));
        return "attrac/business/thread/rule";
    }


    @PostMapping("updateRule")
    @ResponseBody
    public Result updateRule(AttractCommissionRule rule) {
        if (rule == null) {
            return Result.error("提交错误");
        }
        if (StringUtils.isBlank(rule.getId())) {
            iAttractCommissionRuleService.save(rule);
        } else {
            iAttractCommissionRuleService.updateById(rule);
        }
        return Result.ok();
    }


    @PostMapping("updatePrice")
    @ResponseBody
    public Result update(String id, String price) {
        if (id == null) {
            return Result.error("id不能是空的");
        }
        AttractThread attractThread = iAttractThreadService.getById(id);
        if (attractThread != null) {
            if (price != null) {
                price = price.replaceAll(",", "");
                BigDecimal bd = new BigDecimal(price);
                attractThread.setThisCommission(bd);
                iAttractThreadService.updateById(attractThread);
            } else {
                return Result.error("修改金额不能为空");
            }
        }

        return Result.ok();
    }


    /**
     * @return com.len.util.Result
     * @Author YangTianFeng
     * @Description 发放佣金操作
     * @Date 11:01 2020/9/8
     * @Param [id, price]
     **/
    @PostMapping("senddAdd")
    @ResponseBody
    @Transactional
    public Result senddAdd(String id, String price) {
        try {
            if (id == null) {
                return Result.error("id不能是空的");
            }
            AttractThread attractThread = iAttractThreadService.getById(id);
            if (attractThread != null) {
                if (price != null) {
                    WebMember webMember = iWebMemberService.getById(attractThread.getMemberId());
                    webMember.setSumMoney(webMember.getSumMoney().add(attractThread.getThisCommission()));
                    AttractCommissionInfo commissionInfo = new AttractCommissionInfo();
                    commissionInfo.setCommissionType(2);
                    commissionInfo.setThisCommission(attractThread.getThisCommission());
                    commissionInfo.setCommissionCount(webMember.getSumMoney());
                    commissionInfo.setThreadId(attractThread.getId());
                    commissionInfo.setMemberId(webMember.getId());
                    iAttractCommissionInfoService.save(commissionInfo);
                    attractThread.setAuditState(9);
                    iAttractThreadService.updateById(attractThread);
                    QueryWrapper<AttractThread> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq(AttractThread.MEMBER_ID, webMember.getId());
                    queryWrapper.eq(AttractThread.AUDIT_STATE, 9);
                    List<AttractThread> attractThreadList = iAttractThreadService.list(queryWrapper);
                    if (attractThreadList.size() >= 1) {
                        webMember.setMemberClass(1);
                    }
                    if (attractThreadList.size() >= 2) {
                        webMember.setMemberClass(2);
                    }
                    if (attractThreadList.size() >= 3) {
                        webMember.setMemberClass(3);
                    }
                    if (attractThreadList.size() >= 4) {
                        webMember.setMemberClass(4);
                    }
                    if (attractThreadList.size() >= 5) {
                        webMember.setMemberClass(5);
                    }
                    iWebMemberService.updateById(webMember);



                } else {
                    return Result.error("发放金额不能为空");
                }
            }
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("发放失败");
        }


    }



    @GetMapping("export")
    public void export(HttpServletResponse response) throws Exception {
        List<AttractThread> threads = iAttractThreadService.list();
        if (threads != null) {
            List<AttractThreadResp> list = new ArrayList<>();
            threads.forEach(t -> {
                AttractThreadResp resp = new AttractThreadResp();
                resp = BeanUtil.toBean(t, AttractThreadResp.class);

                if (t.getAuditState() == 0) {
                    resp.setAuditState("待审核");
                }
                if (t.getAuditState() == 1) {
                    resp.setAuditState("已确认");
                }
                if (t.getAuditState() == 2) {
                    resp.setAuditState("洽谈阶段");
                }
                if (t.getAuditState() == 3) {
                    resp.setAuditState("意向金阶段");
                }
                if (t.getAuditState() == 4) {
                    resp.setAuditState("定金阶段");
                }
                if (t.getAuditState() == 5) {
                    resp.setAuditState("设计阶段");
                }
                if (t.getAuditState() == 6) {
                    resp.setAuditState("装修阶段");
                }
                if (t.getAuditState() == 7) {
                    resp.setAuditState("已落店");
                }
                if (t.getAuditState() == 8) {
                    resp.setAuditState("确认佣金");
                }
                if (t.getAuditState() == 9) {
                    resp.setAuditState("已发放");
                }
                if (t.getAuditState() == 10) {
                    resp.setAuditState("无效商机");
                }

                list.add(resp);
            });

            ExcelUtil.writeExcel(response, list, "线索管理导出记录", "所有线索", new AttractThreadResp());
        }


    }


}
