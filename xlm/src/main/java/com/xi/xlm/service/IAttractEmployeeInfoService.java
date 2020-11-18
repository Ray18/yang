package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.AttractEmployeeInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 招商员工信息 服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-31
 */
public interface IAttractEmployeeInfoService extends IService<AttractEmployeeInfo> {

    IPage<AttractEmployeeInfo> list (Page page);

}
