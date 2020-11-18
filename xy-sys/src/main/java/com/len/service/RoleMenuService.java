package com.len.service;

import com.len.base.BaseService;
import com.len.entity.SysRoleMenu;
import java.util.List;

/**
 * @author 
 * @date 28.
 * @email 
 */
public interface RoleMenuService extends BaseService<SysRoleMenu,String>{

    List<SysRoleMenu> selectByCondition(SysRoleMenu sysRoleMenu);

    int  selectCountByCondition(SysRoleMenu sysRoleMenu);

    int deleteByPrimaryKey(SysRoleMenu sysRoleMenu);
}
