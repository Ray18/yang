package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.TongxihuiInfo;
import com.xi.xlm.mapper.TongxihuiInfoMapper;
import com.xi.xlm.service.ITongxihuiInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 同喜汇信息 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-01
 */
@Service
public class TongxihuiInfoServiceImpl extends ServiceImpl<TongxihuiInfoMapper, TongxihuiInfo> implements ITongxihuiInfoService {


    @Override
    public IPage<TongxihuiInfo> listPage(Page<TongxihuiInfo> page) {
        return baseMapper.selectPage(page, null);
    }
}
