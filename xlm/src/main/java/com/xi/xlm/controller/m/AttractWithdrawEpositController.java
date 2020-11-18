package com.xi.xlm.controller.m;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.kit.IpKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.WxPayApiConfig;
import com.ijpay.wxpay.WxPayApiConfigKit;
import com.ijpay.wxpay.model.TransferModel;
import com.len.util.Result;
import com.xi.xlm.entity.AttractCommissionInfo;
import com.xi.xlm.entity.AttractWithdrawEposit;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.properties.WxPayProperties;
import com.xi.xlm.service.IAttractCommissionInfoService;
import com.xi.xlm.service.IAttractWithdrawEpositService;
import com.xi.xlm.service.IWebMemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-01
 */
@Controller
@RequestMapping("/m/attractWithdrawEposit")
@AllArgsConstructor
@Slf4j
public class AttractWithdrawEpositController {
    private IAttractWithdrawEpositService iAttractWithdrawEpositService;
    private IAttractCommissionInfoService iAttractCommissionInfoService;
    private IWebMemberService iWebMemberService;
    private WxPayProperties wxPayProperties;


    @GetMapping("show")
    @RequiresPermissions("attract:withdrawEpositShow")
    public String show() {
        return "attrac/business/eposit/list";
    }


    @GetMapping("showList")
    @ResponseBody
    public Result showList(Long page, Long limit) {
        Page<AttractWithdrawEposit> epositPage = new Page<>();
        epositPage.setSize(limit);
        epositPage.setCurrent(page);


        return Result.ok(iAttractWithdrawEpositService.listPage(epositPage));
    }


    @RequiresPermissions("attract:withdrawEpositAudit")
    @PostMapping("audit")
    @ResponseBody
    @Transactional
    public Result audit(HttpServletRequest request, String id, boolean type) {
        try {
            AttractWithdrawEposit eposit = iAttractWithdrawEpositService.getById(id);

            if (type) {
                QueryWrapper<AttractCommissionInfo> infoQueryWrapper = new QueryWrapper<>();
                infoQueryWrapper.eq(AttractCommissionInfo.EPOSIT_ID, eposit.getId());
                AttractCommissionInfo info = iAttractCommissionInfoService.getOne(infoQueryWrapper);
                WebMember webMember = iWebMemberService.getById(info.getMemberId());
                String ip = IpKit.getRealIp(request);
                if (StringUtils.isEmpty(ip)) {
                    ip = "127.0.0.1";
                }
                WxPayApiConfig wxPayApiConfig = new WxPayApiConfig();
                wxPayApiConfig.setAppId(wxPayProperties.getAppId());
                wxPayApiConfig.setDomain(wxPayProperties.getDomain());
                wxPayApiConfig.setMchId(wxPayProperties.getMchId());
                wxPayApiConfig.setPartnerKey(wxPayProperties.getPartnerKey());
                wxPayApiConfig.setCertPath(wxPayProperties.getCertPath());

                WxPayApiConfigKit.putApiConfig(wxPayApiConfig);
                BigDecimal a = info.getThisCommission().multiply(new BigDecimal(100));
                Map<String, String> params = TransferModel.builder()
                        .mch_appid(wxPayProperties.getAppId())
                        .mchid(wxPayProperties.getMchId())
                        .nonce_str(WxPayKit.generateStr())
                        .partner_trade_no(WxPayKit.generateStr())
                        .openid(webMember.getWxOpenId())
                        .check_name("NO_CHECK")
                        .amount(String.valueOf(a.intValue()))
                        .desc("提现操作")
                        .spbill_create_ip(ip)
                        .build()
                        .createSign(wxPayApiConfig.getPartnerKey(), SignType.MD5, false);

                String transfers = WxPayApi.transfers(params, wxPayApiConfig.getCertPath(), wxPayApiConfig.getMchId());
                Map<String, String> map = WxPayKit.xmlToMap(transfers);
                String returnCode = map.get("return_code");
                String resultCode = map.get("result_code");
                if (WxPayKit.codeIsOk(returnCode) && WxPayKit.codeIsOk(resultCode)) {
                    eposit.setRemark(map.get("return_msg"));
                    eposit.setIsState(1L);
                    eposit.setFlas(true);
                    iAttractWithdrawEpositService.updateById(eposit);

                } else {
                    eposit.setFlas(false);
                    iAttractWithdrawEpositService.updateById(eposit);
                }

            } else {
                QueryWrapper<AttractCommissionInfo> infoQueryWrapper = new QueryWrapper<>();
                infoQueryWrapper.eq(AttractCommissionInfo.EPOSIT_ID, eposit.getId());
                AttractCommissionInfo info = iAttractCommissionInfoService.getOne(infoQueryWrapper);
                WebMember webMember = iWebMemberService.getById(info.getId());
                webMember.setSumMoney(webMember.getSumMoney().add(info.getThisCommission()));
                iWebMemberService.updateById(webMember);
                iAttractCommissionInfoService.removeById(info.getId());


            }
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("审核失败,请稍后重试");
        }

    }

}
