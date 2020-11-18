package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.AttractWithdrawEposit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 招商板块-商机管理-提现申请 服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-01
 */
public interface IAttractWithdrawEpositService extends IService<AttractWithdrawEposit> {


    IPage<AttractWithdrawEposit> listPage(Page<AttractWithdrawEposit> page);
}
