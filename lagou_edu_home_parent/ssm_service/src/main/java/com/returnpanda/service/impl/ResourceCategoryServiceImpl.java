package com.returnpanda.service.impl;

import com.returnpanda.dao.ResourceCategoryMapper;
import com.returnpanda.domain.ResourceCategory;
import com.returnpanda.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {
    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;
    //查询所有资源分类
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> list = resourceCategoryMapper.findAllResourceCategory();
        return list;
    }
}
