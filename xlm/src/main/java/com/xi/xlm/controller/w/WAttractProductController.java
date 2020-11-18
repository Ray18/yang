package com.xi.xlm.controller.w;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.kit.HttpKit;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApiConfig;
import com.ijpay.wxpay.WxPayApiConfigKit;
import com.len.util.Result;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.AttractProduct;
import com.xi.xlm.entity.AttractProductOrder;
import com.xi.xlm.properties.WxPayProperties;
import com.xi.xlm.request.w.AttractProductOrderReq;
import com.xi.xlm.response.w.AttOrderListResp;
import com.xi.xlm.service.IAttractProductOrderService;
import com.xi.xlm.service.IAttractProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: AttractProductController
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/5 16:25
 * @Version: 1.0
 **/
@RequestMapping("/w/att/product")
@RestController
@Api(tags = "A-加盟产品相关操作")
@AllArgsConstructor
@Slf4j
public class WAttractProductController {
    private IAttractProductService iAttractProductService;
    private IAttractProductOrderService iAttractProductOrderService;
    private WxPayProperties wxPayProperties;

    @PostMapping("turnProductList")
    public Result turnProductList() {
        QueryWrapper<AttractProduct> wrapper = new QueryWrapper<>();
        wrapper.eq(AttractProduct.UP_STATE, true);
        return Result.ok(iAttractProductService.list(wrapper));
    }

    @ApiOperation("购买记录")
    @PostMapping("turnOrderList")
    public Result<List<AttOrderListResp>> turnOrderList() {

        QueryWrapper<AttractProductOrder> wrapper = new QueryWrapper<>();
        wrapper.eq(AttractProductOrder.WEB_MEMBER_ID, UPrincipal.getMember().getId());
        wrapper.orderByDesc(AttractProductOrder.CREATE_DATE);
        List<AttractProductOrder> list = iAttractProductOrderService.list(wrapper);
        List<AttOrderListResp> resps = new ArrayList<>();
        list.forEach(a -> {
            AttOrderListResp r = new AttOrderListResp();
            r.setId(a.getId());
            r.setPrice(a.getPrice());
            r.setProductName(a.getProductName());
            r.setOrderNo(a.getOrderNo());
            r.setBanner(iAttractProductService.getById(a.getAttractProductId()).getCoverImg());
            resps.add(r);
        });

        return Result.ok(resps);
    }


    @ApiOperation("删除订单")
    @GetMapping("delOrder/{orderId}")
    public Result delOrder(@PathVariable String orderId
    ) {
        return Result.ok(iAttractProductOrderService.removeById(orderId));
    }


    @ApiOperation("下单操作")
    @PostMapping("buyProduct")
    public Result buyProduct(@RequestBody AttractProductOrderReq req, HttpServletRequest request) {
        if (req == null) {
            return Result.error("正确订单");
        }

        Map<String, Object> map = iAttractProductOrderService.buyProduct(req, request);
        if ((Integer) map.get("key") == 200) {
            return Result.ok(map.get("msg"));
        } else {
            return Result.error(map.get("msg").toString());
        }

    }

    @RequestMapping(value = "notifyOrder", method = {RequestMethod.POST, RequestMethod.GET})
    public String notifyOrder(HttpServletRequest request, HttpServletResponse response) {
        String xmlMsg = HttpKit.readData(request);
        Map<String, String> params = WxPayKit.xmlToMap(xmlMsg);
        String returnCode = params.get("return_code");
        WxPayApiConfig wxPayApiConfig = new WxPayApiConfig();
        wxPayApiConfig.setAppId(wxPayProperties.getAppId());
        wxPayApiConfig.setDomain(wxPayProperties.getDomain());
        wxPayApiConfig.setMchId(wxPayProperties.getMchId());
        wxPayApiConfig.setPartnerKey(wxPayProperties.getPartnerKey());
        WxPayApiConfigKit.putApiConfig(wxPayApiConfig);
        if (WxPayKit.verifyNotify(params, WxPayApiConfigKit.getWxPayApiConfig().getPartnerKey(), SignType.HMACSHA256)) {
            if (WxPayKit.codeIsOk(returnCode)) {
                String orderNo = params.get("out_trade_no");
                QueryWrapper<AttractProductOrder> wrapper = new QueryWrapper<>();
                wrapper.eq(AttractProductOrder.ORDER_NO, orderNo);
                AttractProductOrder order = iAttractProductOrderService.getOne(wrapper);
                if (order != null) {
                    order.setOrderState(1);
                    iAttractProductOrderService.updateById(order);
                }
                Map<String, String> xml = new HashMap<String, String>(2);
                xml.put("return_code", "SUCCESS");
                xml.put("return_msg", "OK");
                return WxPayKit.toXml(xml);
            }
        }
        return null;
    }


}
