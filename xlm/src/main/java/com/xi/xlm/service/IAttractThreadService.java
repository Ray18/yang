package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xi.xlm.entity.AttractThread;
import com.xi.xlm.request.w.BusinessListReq;
import com.xi.xlm.request.w.SubmitBusinessReq;
import com.xi.xlm.response.w.AttractBusinessListResp;

import java.util.List;

/**
 * <p>
 * 招商板块-商机管理-线索管理 服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-08-31
 */
public interface IAttractThreadService extends IService<AttractThread> {
    IPage<AttractThread> list(Page<AttractThread> page);
    /**
     * @Author YangTianFeng
     * @Description  提供商机接口
     * @Date 15:30 2020/9/7
     * @Param [req]
     * @return boolean
     **/
    boolean submitThread(SubmitBusinessReq req) throws Exception;

    /**
     * @Author YangTianFeng
     * @Description  招商信息审核列表
     * @Date 19:39 2020/9/7
     * @Param [req]
     * @return java.util.List<com.xi.xlm.response.w.AttractBusinessListResp>
     **/
    List<AttractBusinessListResp>getListThread(BusinessListReq req);
}
