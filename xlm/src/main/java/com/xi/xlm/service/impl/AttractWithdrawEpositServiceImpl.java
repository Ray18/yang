package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.AttractWithdrawEposit;
import com.xi.xlm.mapper.AttractWithdrawEpositMapper;
import com.xi.xlm.service.IAttractWithdrawEpositService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 招商板块-商机管理-提现申请 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-01
 */
@Service
public class AttractWithdrawEpositServiceImpl extends ServiceImpl<AttractWithdrawEpositMapper, AttractWithdrawEposit> implements IAttractWithdrawEpositService {

    @Override
    public IPage<AttractWithdrawEposit> listPage(Page<AttractWithdrawEposit> page) {
        return baseMapper.selectPage(page, null);
    }
}
