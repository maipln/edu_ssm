package com.returnpanda.dao;

import com.returnpanda.domain.Menu;

import java.util.List;

public interface MenuMapper {

    //查询所有父子菜单信息(父子级关系查询)
    public List<Menu> findSubMenuListByPid(Integer pid);


    //查询所有菜单列表进行回显(菜单添加和编辑时需要回显)
    public List<Menu> findAllMenu();


    //按id查询菜单信息
    public Menu findMenuById(Integer id);


    //菜单信息添加
    public void saveMenu(Menu menu);

    //菜单信息修改
    public void updateMenu(Menu menu);
}
