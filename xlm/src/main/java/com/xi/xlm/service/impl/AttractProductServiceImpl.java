package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.xlm.entity.AttractProduct;
import com.xi.xlm.mapper.AttractProductMapper;
import com.xi.xlm.service.IAttractProductService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 招商版块-加盟产品 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-24
 */
@Service
public class AttractProductServiceImpl extends ServiceImpl<AttractProductMapper, AttractProduct> implements IAttractProductService {

    @Override
    public IPage<AttractProduct> list(Page<AttractProduct> page) {
        return baseMapper.selectPage(page, null);
    }
}
