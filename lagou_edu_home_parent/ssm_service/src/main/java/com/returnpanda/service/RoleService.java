package com.returnpanda.service;

import com.returnpanda.domain.Role;
import com.returnpanda.domain.RoleMenuVO;


import java.util.List;

public interface RoleService {
    //添加角色信息
    public void saveRole(Role role);

    //修改角色信息
    public void updateRole(Role role);

    //查询所有角色
    public List<Role> findAllRole(Role role);

    //根据角色id查询关联的菜单信息的ID
    public List<Integer> findMenuByRoleId(Integer roleid);

    //角色菜单分配
    public void roleContextMenu(RoleMenuVO roleMenuVO);

    //删除角色(先删除关联关系)
    public void deleteRole(Integer id);
}
