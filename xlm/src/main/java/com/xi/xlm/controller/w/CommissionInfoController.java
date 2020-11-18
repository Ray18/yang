package com.xi.xlm.controller.w;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.util.Result;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.AttractCommissionInfo;
import com.xi.xlm.entity.AttractWithdrawEposit;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.request.w.ListCommissionReq;
import com.xi.xlm.service.IAttractCommissionInfoService;
import com.xi.xlm.service.IAttractWithdrawEpositService;
import com.xi.xlm.service.IWebMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: CommissionInfoController
 * @author:by yangtianfeng
 * @classDate: 2020/9/9 9:13
 * @Version: 1.0
 **/
@RequestMapping("/w/att/commission")
@RestController
@AllArgsConstructor
@Slf4j
public class CommissionInfoController {

    private IAttractCommissionInfoService iAttractCommissionInfoService;
    private IWebMemberService iWebMemberService;
    private IAttractWithdrawEpositService iAttractWithdrawEpositService;

    @PostMapping("listCommission")
    public Result listCommission(@RequestBody ListCommissionReq req) {
        QueryWrapper<AttractCommissionInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(AttractCommissionInfo.MEMBER_ID, UPrincipal.getMember().getId());
        wrapper.eq(StringUtils.hasText(req.getType()), AttractCommissionInfo.COMMISSION_TYPE, req.getType());

        return Result.ok(iAttractCommissionInfoService.list(wrapper));
    }


    @PostMapping("submitCommission")
    @Transactional
    public Result submitCommission(@RequestBody ListCommissionReq req) {
        try {
            if (req.getCommission() == null) {

                return Result.error("请传入提现金额");
            }

            WebMember webMember = UPrincipal.getMember();
            if (req.getCommission().compareTo(webMember.getSumMoney()) == 1) {

                return Result.error("提现金额,超过账户余额！");
            }
            AttractWithdrawEposit eposit = new AttractWithdrawEposit();
            eposit.setMemberId(webMember.getId());
            eposit.setMoney(req.getCommission().toString());
            eposit.setPhone(webMember.getPhone());
            eposit.setNickName(webMember.getNickName());
            iAttractWithdrawEpositService.save(eposit);
            webMember.setSumMoney(webMember.getSumMoney().subtract(req.getCommission()));
            iWebMemberService.updateById(webMember);

            AttractCommissionInfo commissionInfo = new AttractCommissionInfo();
            commissionInfo.setMemberId(webMember.getId());
            commissionInfo.setCommissionType(1);
            commissionInfo.setThisCommission(req.getCommission());
            commissionInfo.setCommissionCount(webMember.getSumMoney());
            commissionInfo.setEpositId(eposit.getId());
            iAttractCommissionInfoService.save(commissionInfo);

            return Result.ok("提现成功，请等待审核");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("提现失败");
        }

    }


}
