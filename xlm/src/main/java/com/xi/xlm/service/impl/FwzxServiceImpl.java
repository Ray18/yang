package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.xlm.entity.Fwzx;
import com.xi.xlm.mapper.FwzxMapper;
import com.xi.xlm.service.FwzxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FwzxServiceImpl extends ServiceImpl<FwzxMapper, Fwzx> implements FwzxService {
    @Autowired
    private FwzxMapper fwzxMapper;

    @Override
    public void updateFwzx(Fwzx fwzx) {
        fwzxMapper.updateById(fwzx);
    }

    @Override
    public Fwzx getById(int id) {
        LambdaQueryWrapper<Fwzx> fwzxLambdaQueryWrapper = new LambdaQueryWrapper<>();
        fwzxLambdaQueryWrapper.eq(Fwzx::getId,1);
        Fwzx fwzx = fwzxMapper.selectOne(fwzxLambdaQueryWrapper);
        return fwzx;
    }

}
