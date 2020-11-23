package com.xi.xlm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.xlm.entity.Fwzx;


public interface FwzxService extends IService<Fwzx>{

    void updateFwzx(Fwzx fwzx);

    Fwzx getById(int id);

}
