package com.returnpanda.controller;

import com.returnpanda.domain.Menu;
import com.returnpanda.domain.ResponseResult;
import com.returnpanda.domain.Role;
import com.returnpanda.domain.RoleMenuVO;
import com.returnpanda.service.MenuService;
import com.returnpanda.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;


    //添加/修改角色信息
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        ResponseResult responseResult;
        if (role.getId()==null){
            roleService.saveRole(role);
            responseResult = new ResponseResult(true, 200, "添加角色信息",null);
            return responseResult;
        }else {
            roleService.updateRole(role);
            responseResult = new ResponseResult(true, 200, "修改角色信息",null);
            return responseResult;
        }


    }



    //查询所有角色
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> list = roleService.findAllRole(role);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有角色",list);
        return responseResult;
    }


    //查询所有父子菜单信息
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){
        //-1表示查询所有父子级菜单
        List<Menu> list = menuService.findSubMenuListByPid(-1);
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList",list);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有子菜单信息",map);
        return responseResult;
    }


    //根据角色id查询关联的菜单信息的ID
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleid){
        List<Integer> list = roleService.findMenuByRoleId(roleid);
        ResponseResult responseResult = new ResponseResult(true, 200, "根据角色id查询关联的菜单信息的ID",list);
        return responseResult;
    }


    //角色菜单分配
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVO roleMenuVO){
        roleService.roleContextMenu(roleMenuVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "角色菜单分配",null);
        return responseResult;
    }


    //删除角色(先删除关联关系)
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色(先删除关联关系)",null);
        return responseResult;
    }
}
