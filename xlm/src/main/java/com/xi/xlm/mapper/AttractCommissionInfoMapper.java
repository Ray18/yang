package com.xi.xlm.mapper;

import com.xi.xlm.entity.AttractCommissionInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xi.xlm.response.w.CommissionInfoIndexResp;

import java.util.List;


/**
 * <p>
 * 招商板块-商机管理-佣金信息表 Mapper 接口
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-01
 */
public interface AttractCommissionInfoMapper extends BaseMapper<AttractCommissionInfo> {
    List<CommissionInfoIndexResp> listCommissionResp();
}
