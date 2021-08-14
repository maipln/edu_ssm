package com.returnpanda.dao;

import com.returnpanda.domain.Role;
import com.returnpanda.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    //添加角色信息
    public void saveRole(Role role);

    //修改角色信息
    public void updateRole(Role role);

    //查询所有角色
    public List<Role> findAllRole(Role role);

    //根据角色id查询关联的菜单信息的ID
    public List<Integer> findMenuByRoleId(Integer roleid);

    //根据roleid先清空中间表关联关系
    public void deleteRoleContextMenu(Integer roleid);
    //为角色分配菜单信息
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    //删除角色(先删除关联关系)
    public void deleteRole(Integer id);
}
