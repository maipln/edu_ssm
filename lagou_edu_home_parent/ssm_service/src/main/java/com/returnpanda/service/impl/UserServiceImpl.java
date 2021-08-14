package com.returnpanda.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.returnpanda.dao.UserMapper;
import com.returnpanda.domain.*;
import com.returnpanda.service.UserService;

import com.returnpanda.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    //分页多条件查询所有用户
    @Override
    public PageInfo<User> findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> list = userMapper.findAllUserByPage(userVo);
        PageInfo<User> userPageInfo = new PageInfo<>(list);
        return userPageInfo;
    }


    //修改用户状态
    @Override
    public void updateUserStatus(Integer id,String status){
        User user = new User();
        //补全封装信息
        user.setId(id);
        user.setStatus(status);
        Date date = new Date();
        user.setUpdate_time(date);
        userMapper.updateUserStatus(user);
    }


    //用户登录(根据用户名查询具体用户信息)
    @Override
    public User login(User user) throws Exception {
        //调用mapper方法
        User user2 = userMapper.login(user);
        if (user2 != null && Md5.verify(user.getPassword(),"malpin",user2.getPassword())){
            return user2;
        }else {
            return null;
        }
    }


    //根据id查询该用户具备的角色信息
    @Override
    public List<Role> findUserRoleById(Integer id) {
        List<Role> list = userMapper.findUserRoleById(id);
        return list;
    }


    //分配角色
    @Override
    public void userContextRole(UserVo userVo) {
        //根据用户id清空中间表关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());
        //添加关联关系
        for (Integer integer : userVo.getRoleIdList()) {
            User_Role_relation userRoleRelation = new User_Role_relation();
            userRoleRelation.setUserId(userVo.getUserId());
            userRoleRelation.setRoleId(integer);

            Date date = new Date();
            userRoleRelation.setCreatedTime(date);
            userRoleRelation.setUpdatedTime(date);
            userRoleRelation.setCreatedBy("system");
            userRoleRelation.setUpdatedby("system");

            userMapper.userContextRole(userRoleRelation);
        }
    }



    //获取用户权限,进行菜单的动态展示
    @Override
    public ResponseResult getUserPermissions(Integer userid) {
        //获取当前用户拥有的角色
        List<Role> roleId = userMapper.findUserRoleById(userid);

        //将id单独保存
        ArrayList<Integer>  roleIdList = new ArrayList<>();
        for (Role role:roleId) {
            roleIdList.add(role.getId());
        }

        //根据角色id查询角色关联的顶级菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIdList);
        //查询父菜单关联的子菜单信息
        for (Menu menu : parentMenu) {
            List<Menu> subMent= userMapper.findSubMentByPid(menu.getId());
            menu.setSubMenuList(subMent);
        }

        //获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIdList);

        //封装数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);
        ResponseResult responseResult = new ResponseResult(true, 200, "获取用户权限,进行菜单的动态展示",map);
        return responseResult;
    }
}
