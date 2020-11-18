package com.xi.xlm.service.impl;

import com.len.util.Result;
import com.xi.xlm.entity.IntegralGoods;
import com.xi.xlm.mapper.IntegralGoodsMapper;
import com.xi.xlm.request.w.HotelGoodsOrderReq;
import com.xi.xlm.service.IIntegralGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.xlm.service.IIntegralGoodsSpecService;
import com.xi.xlm.service.ITakeAddressService;
import com.xi.xlm.service.IWebMemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 积分商品主表 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-15
 */
@Service
@AllArgsConstructor
public class IntegralGoodsServiceImpl extends ServiceImpl<IntegralGoodsMapper, IntegralGoods> implements IIntegralGoodsService {

}
