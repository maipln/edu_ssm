package com.returnpanda.service;

import com.github.pagehelper.PageInfo;
import com.returnpanda.domain.Resource;
import com.returnpanda.domain.ResourceVO;



public interface ResourceService {

    //资源分页和对条件查询
    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO);

    //按id查询资源信息回显
    public Resource findResourceById(Integer id);

    //添加资源信息
    public void saveResource(Resource resource);

    //修改资源信息
    public void updateResource(Resource resource);

    //删除资源信息
    public void deleteResource(Integer id);

}
