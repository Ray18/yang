package com.xi.xlm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xi.xlm.entity.WebMember;
import com.xi.xlm.mapper.WebMemberMapper;
import com.xi.xlm.service.IWebMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 前台用户 服务实现类
 * </p>
 *
 * @author yangtianfeng
 * @since 2020-09-02
 */
@Service
public class WebMemberServiceImpl extends ServiceImpl<WebMemberMapper, WebMember> implements IWebMemberService {

    @Override
    public IPage<WebMember> listPage(Page<WebMember> page) {
        return this.baseMapper.selectPage(page, null);
    }

    @Override
    public WebMember getMemberByOpenId(String openId) {
        QueryWrapper<WebMember>wrapper = new QueryWrapper<>();
        wrapper.eq(WebMember.WX_OPEN_ID, openId);
        return baseMapper.selectOne(wrapper);
    }
}
