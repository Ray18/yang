package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xi.xlm.entity.Parameter;
import com.xi.xlm.enums.ParameterEnum;
import com.xi.xlm.mapper.ParameterMapper;
import com.xi.xlm.service.IParameterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 全局系统参数 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-14
 */
@Service
public class ParameterServiceImpl extends ServiceImpl<ParameterMapper, Parameter> implements IParameterService {

    @Override
    public Parameter getParameterByCode(String code) {
        QueryWrapper<Parameter> wrapper = new QueryWrapper<>();
        wrapper.eq(Parameter.CODE, code);
        return this.baseMapper.selectOne(wrapper);
    }
}
