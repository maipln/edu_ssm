package com.returnpanda.service.impl;

import com.returnpanda.dao.RoleMapper;
import com.returnpanda.domain.Role;
import com.returnpanda.domain.RoleMenuVO;
import com.returnpanda.domain.Role_menu_relation;
import com.returnpanda.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.tree.RowMapper;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    //添加角色信息
    @Override
    public void saveRole(Role role) {
        //封装补全信息
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        roleMapper.saveRole(role);
    }

    //修改角色信息
    @Override
    public void updateRole(Role role) {
        //封装补全信息
        Date date = new Date();
        role.setUpdatedTime(date);
        roleMapper.updateRole(role);
    }

    //查询所有角色
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    //根据角色id查询关联的菜单信息的ID
    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);
        return menuByRoleId;
    }

    //角色菜单分配
    @Override
    public void roleContextMenu(RoleMenuVO roleMenuVO) {
        //清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());
        //为角色分配菜单
        for (Integer mid : roleMenuVO.getMenuIdList()) {
            //补全封装信息
            Role_menu_relation roleMenuRelation = new Role_menu_relation();
            roleMenuRelation.setMenuId(mid);
            roleMenuRelation.setRoleId(roleMenuVO.getRoleId());
            Date date = new Date();
            roleMenuRelation.setUpdatedTime(date);
            roleMenuRelation.setCreatedTime(date);
            roleMenuRelation.setCreatedBy("system");
            roleMenuRelation.setUpdatedby("system");
            roleMapper.roleContextMenu(roleMenuRelation);

        }

    }



    //删除角色(先删除关联关系)
    @Override
    public void deleteRole(Integer id) {
        //清空中间表关联关系
        roleMapper.deleteRoleContextMenu(id);
        //删除角色
        roleMapper.deleteRole(id);
    }
}
