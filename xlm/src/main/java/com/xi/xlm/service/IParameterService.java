package com.xi.xlm.service;

import com.xi.xlm.entity.Parameter;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 全局系统参数 服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
public interface IParameterService extends IService<Parameter> {

    /**
     * @Author YangTianFeng
     * @Description  通过code查询
     * @Date 16:42 2020/9/14
     * @Param [code]
     * @return com.xi.xlm.entity.Parameter
     **/
    Parameter getParameterByCode(String code);

}
