package com.returnpanda.service;

import com.github.pagehelper.PageInfo;
import com.returnpanda.domain.Menu;
import com.returnpanda.domain.MenuVO;
import com.returnpanda.domain.UserVo;


import java.util.List;


public interface MenuService {

    //查询所有父子菜单信息
    public List<Menu> findSubMenuListByPid(Integer pid);


    //查询所有菜单列表
    public PageInfo<Menu> findAllMenu(MenuVO menuVO);

    //按id查询菜单信息
    public Menu findMenuById(Integer id);

    //菜单信息添加
    public void saveMenu(Menu menu);

    //菜单信息修改
    public void updateMenu(Menu menu);
}
