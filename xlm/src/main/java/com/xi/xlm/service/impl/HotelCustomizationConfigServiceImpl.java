package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.xlm.entity.HotelCustomizationConfig;
import com.xi.xlm.mapper.HotelCustomizationConfigMapper;
import com.xi.xlm.service.IHotelCustomizationConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@Service
public class HotelCustomizationConfigServiceImpl extends ServiceImpl<HotelCustomizationConfigMapper, HotelCustomizationConfig> implements IHotelCustomizationConfigService {

    @Override
    public List<HotelCustomizationConfig> getConfigByType(int type) {
        QueryWrapper<HotelCustomizationConfig> wrapper = new QueryWrapper<>();
        wrapper.eq(HotelCustomizationConfig.TYPE, type);

        return this.baseMapper.selectList(wrapper);
    }
}
