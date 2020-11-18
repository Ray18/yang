package com.xi.xlm.service;

import com.xi.xlm.entity.AttractCommissionInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.xlm.response.w.CommissionInfoIndexResp;

import java.util.List;

/**
 * <p>
 * 招商板块-商机管理-佣金信息表 服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-01
 */
public interface IAttractCommissionInfoService extends IService<AttractCommissionInfo> {
    
    /**
     * @Author YangTianFeng
     * @Description  首页获得佣金信息列表接口
     * @Date 17:31 2020/9/4
     * @Param []
     * @return java.util.List<com.xi.xlm.response.w.CommissionInfoIndexResp>
     **/
    List<CommissionInfoIndexResp> listCommissionResp();

}
