package com.xi.xlm.controller.w;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.len.util.Result;
import com.xi.xlm.entity.IntegralGoods;
import com.xi.xlm.entity.IntegralGoodsSpec;
import com.xi.xlm.request.w.GetGoodsListReq;
import com.xi.xlm.request.w.HotelGoodsOrderReq;
import com.xi.xlm.response.w.GoodsDetailsResp;
import com.xi.xlm.service.IIntegralGoodsService;
import com.xi.xlm.service.IIntegralGoodsSpecService;
import com.xi.xlm.service.IIntegralOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @className: WIntegralGoodsController
 * @Description:
 * @author:by yangtianfeng
 * @classDate: 2020/9/17 10:42
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/w/hotel/integralGoods")
@AllArgsConstructor
@Api(tags = "B-酒店-积分商城")
public class WIntegralGoodsController {


    private IIntegralGoodsService iIntegralGoodsService;

    private IIntegralGoodsSpecService iIntegralGoodsSpecService;

    private IIntegralOrderService iIntegralOrderService;


    @PostMapping("getGoodsList")
    @ApiOperation("积分商品列表")
    public Result getGoodsList(@RequestBody GetGoodsListReq req) {
        QueryWrapper<IntegralGoods> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.hasText(req.getGoodsName()), IntegralGoods.GOODS_NAME, req.getGoodsName());
        wrapper.eq(req.getNewGoods() != null && req.getNewGoods() == true, IntegralGoods.NEW_PRODUCT, true);
        wrapper.orderByDesc(req.getPopularity() != null && req.getPopularity() == true, IntegralGoods.SALES_COUNT);
       wrapper.eq(IntegralGoods.UP_STATE, true);
        if (req.getIntegral() != null) {
            if (req.getIntegral()) {
                wrapper.orderByAsc(IntegralGoods.INTEGRAL);
            }
            if (!req.getIntegral()) {
                wrapper.orderByDesc(IntegralGoods.INTEGRAL);
            }
        }

        return Result.ok(iIntegralGoodsService.list(wrapper));
    }

    @GetMapping("getGoodsById/{id}")
    @ApiOperation("按照ID查询商品详情")
    public Result getGoodsById(@PathVariable String id) {
        GoodsDetailsResp resp = new GoodsDetailsResp();
        IntegralGoods goods = iIntegralGoodsService.getById(id);
        List<IntegralGoodsSpec> goodsSpecList = iIntegralGoodsSpecService.list(Wrappers.<IntegralGoodsSpec>lambdaQuery().eq(IntegralGoodsSpec::getGoodsId, goods.getId()));
        resp = BeanUtil.toBean(goods, GoodsDetailsResp.class);
        resp.setSpecList(goodsSpecList);
        return Result.ok(resp);
    }


    @PostMapping("order")
    public Result order(@RequestBody @Valid HotelGoodsOrderReq req) {

        return iIntegralOrderService.order(req);
    }


}
