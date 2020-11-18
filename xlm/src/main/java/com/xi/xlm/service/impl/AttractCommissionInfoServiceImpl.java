package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.AttractCommissionInfo;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.mapper.AttractCommissionInfoMapper;
import com.xi.xlm.response.w.CommissionInfoIndexResp;
import com.xi.xlm.service.IAttractCommissionInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.xlm.service.IWebMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 招商板块-商机管理-佣金信息表 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-01
 */
@Service
public class AttractCommissionInfoServiceImpl extends ServiceImpl<AttractCommissionInfoMapper, AttractCommissionInfo> implements IAttractCommissionInfoService {

    @Autowired
    private IWebMemberService iWebMemberService;

    @Override
    public List<CommissionInfoIndexResp> listCommissionResp() {
        QueryWrapper<AttractCommissionInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(AttractCommissionInfo.COMMISSION_TYPE, 2);
        wrapper.orderByDesc(AttractCommissionInfo.CREATE_DATE);
        List<AttractCommissionInfo> list = this.baseMapper.selectList(wrapper);
      List<CommissionInfoIndexResp>respList = new ArrayList<>();
        list.forEach(a->{
            CommissionInfoIndexResp resp = new CommissionInfoIndexResp();
            WebMember webMember = iWebMemberService.getById(a.getMemberId());
            resp.setNickName(webMember.getNickName());
            resp.setPriceCount(a.getThisCommission().toString());
            resp.setCreateDate(a.getCreateDate());
            respList.add(resp);

        });


        return respList;
    }
}
