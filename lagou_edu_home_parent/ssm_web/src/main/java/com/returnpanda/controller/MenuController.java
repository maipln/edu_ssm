package com.returnpanda.controller;

import com.github.pagehelper.PageInfo;
import com.returnpanda.domain.Menu;
import com.returnpanda.domain.MenuVO;
import com.returnpanda.domain.ResponseResult;
import com.returnpanda.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    public MenuService menuService;

    //分页查询所有菜单列表
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(MenuVO menuVO){
        PageInfo<Menu> pageInfo = menuService.findAllMenu(menuVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "分页查询所有菜单列表",pageInfo);
        return responseResult;
    }


    //回显菜单信息
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        ResponseResult responseResult;
        //如果是新增菜单,则id值为 -1, 修改菜单 则为当前选择的id值
        if (id == -1){
            //添加菜单时
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            //封装数据
            HashMap<Object, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);
            responseResult = new ResponseResult(true, 200, "添加时回显菜单信息",map);
            return responseResult;
        }else {
            //修改菜单操作时(回显所有menu信息)
            Menu menu = menuService.findMenuById(id);
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            //封装数据
            HashMap<Object, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuListByPid);
            responseResult = new ResponseResult(true, 200, "修改时回显菜单信息",map);
            return responseResult;
        }


    }

    //新建/修改菜单信息
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        ResponseResult responseResult;
        if (menu.getId() == null){
            menuService.saveMenu(menu);
            responseResult = new ResponseResult(true, 200, "新建菜单信息",null);
            return responseResult;
        }else {
            menuService.updateMenu(menu);
            responseResult = new ResponseResult(true, 200, "修改菜单信息",null);
            return responseResult;
        }
    }
}
