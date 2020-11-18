package com.xi.xlm.service;

import com.xi.xlm.entity.HotelProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 酒店版块-产品 服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
public interface IHotelProductService extends IService<HotelProduct> {

    void  savas (HotelProduct product);

}
