package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.AttractEmployeeInfo;
import com.xi.xlm.mapper.AttractEmployeeInfoMapper;
import com.xi.xlm.service.IAttractEmployeeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 招商员工信息 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-31
 */
@Service
public class AttractEmployeeInfoServiceImpl extends ServiceImpl<AttractEmployeeInfoMapper, AttractEmployeeInfo> implements IAttractEmployeeInfoService {


    @Override
    public IPage<AttractEmployeeInfo> list(Page page) {

        return baseMapper.selectPage(page, null);
    }
}
