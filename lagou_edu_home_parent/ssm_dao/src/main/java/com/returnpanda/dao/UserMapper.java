package com.returnpanda.dao;

import com.returnpanda.domain.*;


import java.util.List;

public interface UserMapper {

    //分页多条件查询所有用户
    public List<User> findAllUserByPage(UserVo userVo);

    //修改用户状态
    public void updateUserStatus(User user);

    //用户登录(根据用户名查询具体用户信息)
    public User login(User user);




    //根据用户id清空中间表
    public void deleteUserContextRole(Integer id);

    //分配角色
    public void userContextRole(User_Role_relation userRoleRelation);


    //根据id查询该用户具备的角色信息
    public List<Role> findUserRoleById(Integer id);

    //根据角色id查询角色关联的顶级菜单
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
    //根据PID查询子菜单信息
    public List<Menu> findSubMentByPid(Integer pid);

    //获取用户拥有的资源权限信息
    public List<Resource> findResourceByRoleId(List<Integer> ids);
}
