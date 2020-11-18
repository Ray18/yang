package com.xi.xlm.service;

import com.len.util.Result;
import com.xi.xlm.entity.IntegralOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.xlm.request.w.HotelGoodsOrderReq;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-15
 */
public interface IIntegralOrderService extends IService<IntegralOrder> {
    /**
     * @Author YangTianFeng
     * @Description  积分商品下单操作
     * @Date 11:36 2020/9/17
     * @Param [req]
     * @return com.len.util.Result
     **/
    Result order(HotelGoodsOrderReq req);

}
