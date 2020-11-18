package com.xi.xlm.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xi.xlm.common.UPrincipal;
import com.xi.xlm.entity.AttracArea;
import com.xi.xlm.entity.AttractEmployeeInfo;
import com.xi.xlm.entity.AttractThread;
import com.xi.xlm.exception.ServiceException;
import com.xi.xlm.mapper.AttractThreadMapper;
import com.xi.xlm.request.w.BusinessListReq;
import com.xi.xlm.request.w.SubmitBusinessReq;
import com.xi.xlm.response.w.AttractBusinessListResp;
import com.xi.xlm.service.IAttracAreaService;
import com.xi.xlm.service.IAttractEmployeeInfoService;
import com.xi.xlm.service.IAttractThreadService;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 招商板块-商机管理-线索管理 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-31
 */
@Service
@AllArgsConstructor
public class AttractThreadServiceImpl extends ServiceImpl<AttractThreadMapper, AttractThread> implements IAttractThreadService {


    private IAttracAreaService iAttracAreaService;

    private IAttractEmployeeInfoService iAttractEmployeeInfoService;


    @Override
    public IPage<AttractThread> list(Page<AttractThread> page) {
        return this.baseMapper.selectPage(page, null);
    }

    @Override
    public boolean submitThread(SubmitBusinessReq req) throws ServiceException {
        AttractThread thread = BeanUtil.toBean(req, AttractThread.class);
        if (!StringUtils.hasText(thread.getMemberId())) {
            thread.setMemberId(UPrincipal.getMember().getId());
        }

        if (req.getIsCountry()) {
            QueryWrapper<AttracArea> areaWrapper = new QueryWrapper<>();
            areaWrapper.eq(AttracArea.NAME, thread.getCity());
            AttracArea attracArea = iAttracAreaService.getOne(areaWrapper);
            attracArea = iAttracAreaService.getById(attracArea.getParent());

            QueryWrapper<AttractEmployeeInfo> wrapper = new QueryWrapper<>();
            wrapper.eq(AttractEmployeeInfo.AREA_CODE, attracArea.getId());
            AttractEmployeeInfo attractEmployeeInfo = iAttractEmployeeInfoService.getOne(wrapper);
            if (attractEmployeeInfo == null) {
                throw new ServiceException("未搜索到相应区域经理");
            }
            thread.setRegionalUserId(attractEmployeeInfo.getId());
            thread.setRegionalUserName(attractEmployeeInfo.getName());

            AttracArea areaInfo = iAttracAreaService.getById(attracArea.getParent());
            if(areaInfo!=null){
                QueryWrapper<AttractEmployeeInfo> wrapperP = new QueryWrapper<>();
                wrapperP.eq(AttractEmployeeInfo.AREA_CODE, areaInfo.getId());
                AttractEmployeeInfo employeeInfoP = iAttractEmployeeInfoService.getOne(wrapperP);
                if(employeeInfoP!=null){
                    thread.setRegionUserId(employeeInfoP.getId());
                    thread.setRegionUserName(employeeInfoP.getName());
                }

            }

            if (this.baseMapper.insert(thread) > 0) {
                return true;
            }


        } else {
            QueryWrapper<AttractEmployeeInfo> wrapper = new QueryWrapper<>();
            wrapper.eq(AttractEmployeeInfo.AREA_CODE, 9);
            AttractEmployeeInfo attractEmployeeInfo = iAttractEmployeeInfoService.getOne(wrapper);
            if (attractEmployeeInfo == null) {
                return false;
            }
            thread.setRegionalUserId(attractEmployeeInfo.getId());
            thread.setRegionalUserName(attractEmployeeInfo.getName());
/*            thread.setRegionUserId(attractEmployeeInfo.getId());
            thread.setRegionUserName(attractEmployeeInfo.getName());*/
            if (this.baseMapper.insert(thread) > 0) {
                return true;
            }


        }


        return false;
    }

    @Override
    public List<AttractBusinessListResp> getListThread(BusinessListReq req) {
        QueryWrapper<AttractEmployeeInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(AttractEmployeeInfo.MEMBER_ID, UPrincipal.getMember().getId());
        AttractEmployeeInfo info = iAttractEmployeeInfoService.getOne(wrapper);
        List<AttractBusinessListResp> resps = new ArrayList<>();

        QueryWrapper<AttractThread> threadQueryWrapper = new QueryWrapper<>();
     if ("大区经理".equals(info.getJobInfo())) {
            threadQueryWrapper.eq(AttractThread.REGION_USER_ID,info.getId());

        }
        if ("区域经理".equals(info.getJobInfo())) {
            threadQueryWrapper.eq(AttractThread.REGIONAL_USER_ID,info.getId());
        }
        threadQueryWrapper.eq(req.getAuditState()!=null,AttractThread.AUDIT_STATE, req.getAuditState());

        List<AttractThread> threads = this.baseMapper.selectList(threadQueryWrapper);
        threads.forEach(t->{
            AttractBusinessListResp r = new AttractBusinessListResp();
            r.setDQInfo(iAttractEmployeeInfoService.getById(t.getRegionUserId()));
            r.setQYInfo(iAttractEmployeeInfoService.getById(t.getRegionalUserId()));
            r.setId(t.getId());
            r.setIsCountry(t.getIsCountry());
            r.setCity(t.getCity());
            r.setName(t.getName());
            r.setPhone(t.getPhone());
            r.setAuditState(t.getAuditState());
            r.setThisCommission(t.getThisCommission());
            r.setCreateDate(t.getCreateDate());
            r.setJobInfo(info.getJobInfo());
            r.setJobName(info.getName());
            resps.add(r);
        });

        return resps;
    }
}
