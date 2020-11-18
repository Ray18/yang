package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.TongxihuiInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 同喜汇信息 服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-01
 */
public interface ITongxihuiInfoService extends IService<TongxihuiInfo> {
    IPage<TongxihuiInfo> listPage(Page<TongxihuiInfo> page);

}
