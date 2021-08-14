package com.returnpanda.service;

import com.github.pagehelper.PageInfo;
import com.returnpanda.domain.*;

import java.util.List;


public interface UserService {

    //分页多条件查询所有用户
    public PageInfo<User> findAllUserByPage(UserVo userVo);
    //修改用户状态
    public void updateUserStatus(Integer id,String status);
    //用户登录(根据用户名查询具体用户信息)
    public User login(User user) throws Exception;

    //分配角色
    public void userContextRole(UserVo userVo);

    //根据id查询该用户具备的角色信息
    public List<Role> findUserRoleById(Integer id);

    //获取用户权限,进行菜单的动态展示
    public ResponseResult getUserPermissions(Integer userid);
}
