package com.len.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.SysRoleMenu;
import com.len.mapper.SysRoleMenuMapper;
import com.len.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @date 28.
 * @email
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu, String> implements
        RoleMenuService {

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Override
    public List<SysRoleMenu> selectByCondition(SysRoleMenu sysRoleMenu) {
        return roleMenuMapper.selectByCondition(sysRoleMenu);
    }

    @Override
    public int selectCountByCondition(SysRoleMenu sysRoleMenu) {
        return roleMenuMapper.selectCountByCondition(sysRoleMenu);
    }

    @Override
    public int deleteByPrimaryKey(SysRoleMenu sysRoleMenu) {
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>(sysRoleMenu);
        return roleMenuMapper.delete(queryWrapper);
    }
}
