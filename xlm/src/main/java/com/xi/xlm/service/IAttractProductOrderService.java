package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.AttractProduct;
import com.xi.xlm.entity.AttractProductOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.xlm.request.m.ListAttractProductOrder;
import com.xi.xlm.entity.AttractProductOrder;
import com.xi.xlm.request.w.AttractProductOrderReq;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 加盟商品订单 服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-24
 */
public interface IAttractProductOrderService extends IService<AttractProductOrder> {
    IPage<AttractProductOrder> list(ListAttractProductOrder order, Page<AttractProductOrder> page);


    /**
     * @Author YangTianFeng
     * @Description  小程序购买加盟商品下单支付接口
     * @Date 16:45 2020/9/5
     * @Param [req]
     * @return  Map<String,Object>
     **/
    Map<String,Object> buyProduct (AttractProductOrderReq req, HttpServletRequest request);

}
