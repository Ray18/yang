package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.len.util.Result;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.*;
import com.xi.xlm.mapper.IntegralOrderMapper;
import com.xi.xlm.request.w.HotelGoodsOrderReq;
import com.xi.xlm.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-15
 */
@Service
@AllArgsConstructor
public class IntegralOrderServiceImpl extends ServiceImpl<IntegralOrderMapper, IntegralOrder> implements IIntegralOrderService {
    private IIntegralGoodsSpecService iIntegralGoodsSpecService;
    private ITakeAddressService iTakeAddressService;
    private IWebMemberService iWebMemberService;
    private IIntegralGoodsService iIntegralGoodsService;
    private IHotelIntegralInfoService iHotelIntegralInfoService;


    @Override
    @Transactional
    public Result order(HotelGoodsOrderReq req) {
        try {
            IntegralOrder order = new IntegralOrder();
            order.setCount(req.getCount());
            IntegralGoods goods = iIntegralGoodsService.getById(req.getProductId());
            if (goods == null) {
                return Result.error("下单失败-不存在该产品");
            }
            order.setIntegraProductId(goods.getId());
            order.setProductName(goods.getGoodsName());
            IntegralGoodsSpec spec = iIntegralGoodsSpecService.getById(req.getSpecId());
            if (spec == null) {
                return Result.error("下单失败-不存在该规格");
            }

            order.setSpecId(spec.getId());
            order.setSpecDetails(spec.getName());
            order.setPrice(spec.getPrice());


            WebMember webMember = UPrincipal.getMember();
            if (webMember == null) {
                return Result.error("下单失败-该会员不存在");
            }
            order.setBuyerHaedImg(webMember.getAvatarUrl());
            order.setBuyerNickName(webMember.getNickName());
            order.setBuyerTel(webMember.getPhone());
            order.setWebMemberId(webMember.getId());

            TakeAddress address = iTakeAddressService.getById(req.getAddressId());
            if (address == null) {

                return Result.error("下单失败-该地址不存在");
            }

            order.setTakePersonName(address.getTakePersonName());
            order.setProvince(address.getProvince());
            order.setCity(address.getCity());
            order.setDistrict(address.getDistrict());
            order.setAddressDetails(address.getAddressDetails());
            order.setPhone(address.getPhone());


            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            order.setOrderNo(format.format(new Date()));

            BigDecimal price = new BigDecimal(req.getCount());
            order.setCountPrice(order.getPrice().multiply(price));
            if (webMember.getIntegral().compareTo(order.getCountPrice()) == -1) {

                return Result.error("下单失败-账户积分不足");
            }


            order.setOrderState(1);
            this.baseMapper.insert(order);

            webMember.setIntegral(webMember.getIntegral().subtract(order.getCountPrice()));
            iWebMemberService.updateById(webMember);

            HotelIntegralInfo hotelIntegralInfo = new HotelIntegralInfo();
            hotelIntegralInfo.setMemberId(webMember.getId());
            hotelIntegralInfo.setNickName(webMember.getNickName());
            hotelIntegralInfo.setThisIntegral(order.getCountPrice().intValue());
            hotelIntegralInfo.setIntegralCount(webMember.getIntegral().intValue());
            hotelIntegralInfo.setIntegralType(1);
            hotelIntegralInfo.setOrderId(order.getId());
            hotelIntegralInfo.setGoodsName(order.getProductName());
            hotelIntegralInfo.setGoodsSpecification(order.getSpecDetails());
            hotelIntegralInfo.setGoodsAmount(req.getCount());
            iHotelIntegralInfoService.save(hotelIntegralInfo);

            goods.setSalesCount((goods.getSalesCount()==null?0:goods.getSalesCount())+req.getCount());


        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("系统异常-下单失败");

        }


        return Result.ok();
    }
}
