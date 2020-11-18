package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.AttractDealerInfo;
import com.xi.xlm.mapper.AttractDealerInfoMapper;
import com.xi.xlm.service.IAttractDealerInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 招商版块-经销商信息 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-27
 */
@Service
public class AttractDealerInfoServiceImpl extends ServiceImpl<AttractDealerInfoMapper, AttractDealerInfo> implements IAttractDealerInfoService {

    @Override
    public IPage<AttractDealerInfo> list(Page<AttractDealerInfo> page) {
        return baseMapper.selectPage(page, null);
    }
}
