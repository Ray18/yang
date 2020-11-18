package com.xi.xlm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.enums.TradeType;
import com.ijpay.core.kit.IpKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.model.UnifiedOrderModel;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.AttractProduct;
import com.xi.xlm.entity.AttractProductOrder;
import com.xi.xlm.entity.TakeAddress;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.mapper.AttractProductOrderMapper;
import com.xi.xlm.properties.WxPayProperties;
import com.xi.xlm.request.m.ListAttractProductOrder;
import com.xi.xlm.request.w.AttractProductOrderReq;
import com.xi.xlm.service.IAttractProductOrderService;
import com.xi.xlm.service.IAttractProductService;
import com.xi.xlm.service.ITakeAddressService;
import com.xi.xlm.service.IWebMemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 加盟商品订单 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-24
 */
@Service
@AllArgsConstructor
@Slf4j
public class AttractProductOrderServiceImpl
        extends ServiceImpl<AttractProductOrderMapper, AttractProductOrder> implements IAttractProductOrderService {

    private IAttractProductService iAttractProductService;
    private IWebMemberService iWebMemberService;
    private WxPayProperties wxPayProperties;
    private ITakeAddressService iTakeAddressService;

    @Override
    public IPage<AttractProductOrder> list(ListAttractProductOrder order, Page<AttractProductOrder> page) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.apply(StringUtils.isNotEmpty(order.getCreateDate()), "DATE_FORMAT( create_date, '%Y-%m-%d' ) " +
                " = DATE_FORMAT( '" + order.getCreateDate() + "', '%Y-%m-%d' )");
        queryWrapper.like(StringUtils.isNotEmpty(order.getOrderNo()), AttractProductOrder.ORDER_NO, order.getOrderNo());
        queryWrapper.eq(order.getOrderState() != null, AttractProductOrder.ORDER_STATE, order.getOrderState());

        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public Map<String, Object> buyProduct(AttractProductOrderReq req, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            WebMember webMember = iWebMemberService.getById(req.getWebMemberId());
            AttractProductOrder order = BeanUtil.toBean(req, AttractProductOrder.class);
            if (order != null) {
                if (!org.springframework.util.StringUtils.hasText(order.getWebMemberId())) {
                    order.setWebMemberId(UPrincipal.getMember().getId());
                    webMember = UPrincipal.getMember();
                }
                AttractProduct product = iAttractProductService.getById(req.getAttractProductId());
                order.setProductName(product.getProductName());

                order.setBuyerHaedImg(
                        webMember.getAvatarUrl()
                );
                order.setBuyerNickName(webMember.getNickName());
                order.setBuyerTel(webMember.getPhone());

                SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                order.setOrderNo(format.format(new Date()));
                TakeAddress takeAddress = iTakeAddressService.getById(req.getAddressId());
                order.setProvince(takeAddress.getProvince());
                order.setCity(takeAddress.getCity());
                order.setDistrict(takeAddress.getDistrict());
                order.setAddressDetails(takeAddress.getAddressDetails());
                order.setTakePersonName(takeAddress.getTakePersonName());
                order.setPhone(takeAddress.getPhone());

                this.baseMapper.insert(order);


                String openId = webMember.getWxOpenId();
                String ip = IpKit.getRealIp(request);
                if (StringUtils.isBlank(ip)) {
                    ip = "127.0.0.1";
                }
                BigDecimal a = order.getPrice().multiply(new BigDecimal(100));
                Map<String, String> params = UnifiedOrderModel
                        .builder()
                        .appid(wxPayProperties.getAppId())
                        .mch_id(wxPayProperties.getMchId())
                        .nonce_str(WxPayKit.generateStr())
                        .body(order.getProductName())
                        .attach("")
                        .out_trade_no(order.getOrderNo())
                        .total_fee(String.valueOf(a.intValue()))
                        .spbill_create_ip(ip)
                        .notify_url(wxPayProperties.getDomain())
                        .trade_type(TradeType.JSAPI.getTradeType())
                        .openid(openId)
                        .build()
                        .createSign(wxPayProperties.getPartnerKey(), SignType.HMACSHA256);
                String xmlResult = WxPayApi.pushOrder(false, params);
                Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
                String returnCode = result.get("return_code");
                String returnMsg = result.get("return_msg");
                if (!WxPayKit.codeIsOk(returnCode)) {
                    map.put("code", 201);
                    map.put("msg", returnMsg);
                    return map;
                }
                String resultCode = result.get("result_code");
                if (!WxPayKit.codeIsOk(resultCode)) {
                    map.put("code", 201);
                    map.put("msg", returnMsg);
                    return map;
                }
                String prepayId = result.get("prepay_id");
                Map<String, String> packageParams = WxPayKit.miniAppPrepayIdCreateSign(wxPayProperties.getAppId(), prepayId,
                        wxPayProperties.getPartnerKey(), SignType.HMACSHA256);
                String jsonStr = JSON.toJSONString(packageParams);
                map.put("key", 200);
                map.put("msg", jsonStr);
                return map;
            } else {
                map.put("code", 201);
                map.put("msg", "无订单！");
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 201);
            map.put("msg", "订单系统出现异常！");
            return map;
        }

    }
}
