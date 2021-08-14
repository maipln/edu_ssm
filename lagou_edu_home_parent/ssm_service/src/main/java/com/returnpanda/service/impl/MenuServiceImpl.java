package com.returnpanda.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.returnpanda.dao.MenuMapper;
import com.returnpanda.domain.Menu;
import com.returnpanda.domain.MenuVO;
import com.returnpanda.domain.UserVo;
import com.returnpanda.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    //查询所有父子菜单信息(角色分配时调用了)
    @Override
    public List<Menu> findSubMenuListByPid(Integer pid) {
        List<Menu> menuByRoleId = menuMapper.findSubMenuListByPid(pid);

        return menuByRoleId;
    }



    //分页查询所有菜单列表
    @Override
    public PageInfo<Menu> findAllMenu(MenuVO menuVO) {
        PageHelper.startPage(menuVO.getCurrentPage(),menuVO.getPageSize());
        List<Menu> list = menuMapper.findAllMenu();
        PageInfo<Menu> userPageInfo = new PageInfo<>(list);
        return userPageInfo;
    }

    //按id查询菜单信息
    @Override
    public Menu findMenuById(Integer id) {
        Menu menuById = menuMapper.findMenuById(id);
        return menuById;
    }

    //菜单信息添加
    @Override
    public void saveMenu(Menu menu) {

        //补全信息
        Date date = new Date();
        menu.setUpdatedTime(date);
        menu.setCreatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        menuMapper.saveMenu(menu);
    }


    //菜单信息修改
    @Override
    public void updateMenu(Menu menu) {

        //补全信息
        Date date = new Date();
        menu.setUpdatedTime(date);
        menuMapper.updateMenu(menu);
    }


}
