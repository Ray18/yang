package com.xi.xlm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.WebMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 前台用户 服务类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-02
 */
public interface IWebMemberService extends IService<WebMember> {

    IPage<WebMember> listPage(Page<WebMember> page);

    /**
     * @Author YangTianFeng
     * @Description  通过openid 查询会员
     * @Date 11:43 2020/9/3
     * @Param [openId]
     * @return com.xi.xlm.entity.WebMember
     **/
    WebMember getMemberByOpenId(String openId);
}
