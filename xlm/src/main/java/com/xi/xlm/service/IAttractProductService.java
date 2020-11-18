package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.xlm.entity.AttractProduct;

/**
 * <p>
 * 招商版块-加盟产品 服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-24
 */
public interface IAttractProductService extends IService<AttractProduct> {

    /**
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.xi.xlm.entity.AttractProduct>
     * @Author YangTianFeng
     * @Description 无条件分页查询列表
     * @Date 14:46 2020/8/24
     * @Param [page]
     **/
    IPage<AttractProduct> list(Page<AttractProduct> page);

}
