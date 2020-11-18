package com.xi.xlm.service;

import com.xi.xlm.entity.HotelCustomizationConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
public interface IHotelCustomizationConfigService extends IService<HotelCustomizationConfig> {

    /**
     * @Author YangTianFeng
     * @Description  根据类别查询
     * @Date 14:30 2020/9/14
     * @Param [code]
     * @return java.util.List<com.xi.xlm.entity.HotelCustomizationConfig>
     **/
    List<HotelCustomizationConfig> getConfigByType(int type);

}
