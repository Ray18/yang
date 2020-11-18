package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.AttractDealerInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.xlm.entity.AttractProduct;

/**
 * <p>
 * 招商版块-经销商信息 服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-27
 */
public interface IAttractDealerInfoService extends IService<AttractDealerInfo> {
    IPage<AttractDealerInfo> list(Page<AttractDealerInfo> page);

}
