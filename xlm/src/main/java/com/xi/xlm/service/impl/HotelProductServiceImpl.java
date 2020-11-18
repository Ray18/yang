package com.xi.xlm.service.impl;

import com.xi.xlm.entity.HotelProduct;
import com.xi.xlm.mapper.HotelProductMapper;
import com.xi.xlm.service.IHotelProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 酒店版块-产品 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@Service
public class HotelProductServiceImpl extends ServiceImpl<HotelProductMapper, HotelProduct> implements IHotelProductService {

    @Override
    public void savas(HotelProduct product) {
        this.baseMapper.insert(product);
    }
}
