package com.xi.xlm.controller.w;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.util.Result;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.*;
import com.xi.xlm.request.w.*;
import com.xi.xlm.response.w.AgencyInfoResp;
import com.xi.xlm.response.w.AttractBusinessListResp;
import com.xi.xlm.response.w.BusinessListResp;
import com.xi.xlm.service.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static cn.hutool.core.bean.BeanUtil.toBean;

/**
 * @className: BusinessController
 * @author:by yangtianfeng
 * @classDate: 2020/9/5 14:51
 * @Version: 1.0
 **/

@RequestMapping("/w/att/business")
@RestController
@AllArgsConstructor
public class BusinessController {
    private IAttractDealerInfoService iAttractDealerInfoService;
    private ITongxihuiInfoService iTongxihuiInfoService;
    private IAttractProductService iAttractProductService;
    private IAttractThreadService iAttractThreadService;
    private IAttractEmployeeInfoService iAttractEmployeeInfoService;
    private IAttractCommissionRuleService iAttractCommissionRuleService;


    @GetMapping("agencyInfo")
    public Result<AgencyInfoResp> agencyInfo() {
        WebMember webMember = UPrincipal.getMember();
        AgencyInfoResp resp = new AgencyInfoResp();
        if (webMember.getDealerFlag()) {
            resp.setAgency(1);
        } else {
            QueryWrapper<AttractDealerInfo> wrapper = new QueryWrapper<>();
            wrapper.eq(AttractDealerInfo.MEMBER_ID, webMember.getId());
            wrapper.ne(AttractDealerInfo.STATUS, 2);
            AttractDealerInfo info = iAttractDealerInfoService.getOne(wrapper);

            if (info == null) {
                resp.setAgency(2);
            } else {

                if (info.getStatus() == 1) {
                    resp.setAgency(1);
                }
                if (info.getStatus() == 0) {
                    resp.setAgency(3);
                }
                if (info.getStatus() == 2) {
                    resp.setAgency(4);
                }

            }


        }

        if (webMember.getTongxihuiFlag()) {
            resp.setTongXiHui(1);
        } else {
            QueryWrapper<TongxihuiInfo> wrapper = new QueryWrapper<>();
            wrapper.eq(TongxihuiInfo.MEMBER_ID, webMember.getId());
            wrapper.ne(TongxihuiInfo.STATUS, 2);
            TongxihuiInfo info = iTongxihuiInfoService.getOne(wrapper);

            if (info == null) {
                resp.setTongXiHui(2);
            } else {
                if (info.getStatus() == 1) {
                    resp.setTongXiHui(1);
                }
                if (info.getStatus() == 0) {
                    resp.setTongXiHui(3);
                }
                if (info.getStatus() == 2) {
                    resp.setTongXiHui(4);
                }
            }


        }


        return Result.ok(resp);
    }


    @PostMapping("turnAgency")
    public Result turnAgency(@RequestBody TurnAgencyReq req) {
        WebMember webMember = UPrincipal.getMember();
        if (webMember == null) {
            return Result.error("请登录");
        }
        AttractDealerInfo info = toBean(req, AttractDealerInfo.class);
        info.setMemberId(webMember.getId());
        iAttractDealerInfoService.save(info);
        return Result.ok();
    }

    @PostMapping("turnBusiness")
    public Result turnBusiness(@RequestBody TurnBusinessReq req) {
        WebMember webMember = UPrincipal.getMember();
        if (webMember == null) {
            return Result.error("请登录");
        }
        TongxihuiInfo info = toBean(req, TongxihuiInfo.class);
        info.setMemberId(webMember.getId());
        iTongxihuiInfoService.save(info);
        return Result.ok();
    }

    @PostMapping("submitBusiness")
    public Result submitBusiness(@RequestBody SubmitBusinessReq req) throws Exception {

        if (iAttractThreadService.submitThread(req)) {
            return Result.ok();
        } else {
            return Result.error("提交失败,请稍后重试");
        }


    }

    @PostMapping("getListBusinessListResp")
    public Result<List<BusinessListResp>> getListBusinessListResp(@RequestBody BusinessListReq req) {
        QueryWrapper<AttractThread> queryWrapper = new QueryWrapper<>();

        if (req.getAuditState() != null) {

            if (req.getAuditState() == 11) {
                queryWrapper.ne(AttractThread.AUDIT_STATE, 10);
                queryWrapper.ne(AttractThread.AUDIT_STATE, 0);
                queryWrapper.ne(AttractThread.AUDIT_STATE, 8);
                queryWrapper.ne(AttractThread.AUDIT_STATE, 9);
            } else {
                queryWrapper.eq(AttractThread.AUDIT_STATE, req.getAuditState());
            }

        }

        queryWrapper.eq(AttractThread.MEMBER_ID, UPrincipal.getMember().getId());

        List<AttractThread> attractThreadList = iAttractThreadService.list(queryWrapper);
        List<BusinessListResp> listResps = new ArrayList<>();
        attractThreadList.forEach(a -> {
            BusinessListResp resp = BeanUtil.toBean(a, BusinessListResp.class);
            listResps.add(resp);
        });


        return Result.ok(listResps);
    }

    @PostMapping("getListBusinessListRespById")
    public Result<BusinessListResp> getListBusinessListRespById(@RequestBody BusinessListReq req) {
        AttractThread attractThreadList = iAttractThreadService.getById(req.getId());
        BusinessListResp resp = BeanUtil.toBean(attractThreadList, BusinessListResp.class);

        return Result.ok(resp);
    }


    @PostMapping("getListThread")
    public Result<List<AttractBusinessListResp>> getListThread(@RequestBody BusinessListReq req) {
        return Result.ok(iAttractThreadService.getListThread(req));
    }


    @PostMapping("getListThreadById")
    public Result<AttractBusinessListResp> getListThreadById(@RequestBody BusinessListReq req) {
        QueryWrapper<AttractEmployeeInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(AttractEmployeeInfo.MEMBER_ID, UPrincipal.getMember().getId());
        AttractEmployeeInfo info = iAttractEmployeeInfoService.getOne(wrapper);
        AttractBusinessListResp r = new AttractBusinessListResp();

        AttractThread t = iAttractThreadService.getById(req.getId());

        r.setDQInfo(iAttractEmployeeInfoService.getById(t.getRegionUserId()));
        r.setQYInfo(iAttractEmployeeInfoService.getById(t.getRegionalUserId()));
        r.setId(t.getId());
        r.setIsCountry(t.getIsCountry());
        r.setCity(t.getCity());
        r.setName(t.getName());
        r.setPhone(t.getPhone());
        r.setAuditState(t.getAuditState());
        r.setThisCommission(t.getThisCommission());
        r.setCreateDate(t.getCreateDate());
        r.setJobInfo(info.getJobInfo());
        r.setJobName(info.getName());

        return Result.ok(r);
    }

    @GetMapping("getAttractCommissionRule/{threadId}")
    public Result getAttractCommissionRule(@PathVariable String threadId) {
        QueryWrapper<AttractCommissionRule> wrapper = new QueryWrapper<>();
        wrapper.eq(AttractCommissionRule.THREAD_ID, threadId);

        return Result.ok(iAttractCommissionRuleService.getOne(wrapper));
    }


    @PostMapping("nextThread")
    public Result nextThread(@RequestBody NextThreadReq req) {
        AttractThread thread = iAttractThreadService.getById(req.getId());
        if (req.getFl() == 0) {
            thread.setAuditState(10);
        }
        if (req.getFl() == 1) {
            if (thread.getAuditState() == 0) {
                thread.setAuditState(1);
            }
            thread.setAuditState(thread.getAuditState() + 1);
        }

        if (req.getFl() == 2) {
            thread.setIsSub(req.getIsSub());
            thread.setIsTalk(req.getIsTalk());
            thread.setIsPurpose(req.getIsPurpose());
            thread.setIsSeated(req.getIsSeated());
            thread.setThisCommission(req.getThisCommission());
            thread.setAuditState(8);
        }

        return Result.ok(iAttractThreadService.updateById(thread));
    }


}
