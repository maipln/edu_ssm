package com.returnpanda.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.returnpanda.dao.ResourceMapper;
import com.returnpanda.domain.PromotionAd;
import com.returnpanda.domain.Resource;
import com.returnpanda.domain.ResourceVO;
import com.returnpanda.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    //资源分页和对条件查询
    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO) {
        PageHelper.startPage(resourceVO.getCurrentPage(),resourceVO.getPageSize());
        List<Resource> list = resourceMapper.findAllResourceByPage(resourceVO);
        PageInfo<Resource> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    //按id查询资源信息回显
    @Override
    public Resource findResourceById(Integer id) {
        Resource resourceById = resourceMapper.findResourceById(id);
        return resourceById;
    }
    //添加资源信息
    @Override
    public void saveResource(Resource resource) {
        //补全信息
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");
        resourceMapper.saveResource(resource);
    }

    //修改资源信息
    @Override
    public void updateResource(Resource resource) {
        //补全信息
        Date date = new Date();
        resource.setUpdatedTime(date);
        resourceMapper.updateResource(resource);
    }


    //删除资源信息
    @Override
    public void deleteResource(Integer id) {
        resourceMapper.deleteResource(id);
    }


}
