package com.returnpanda.controller;

import com.returnpanda.domain.ResourceCategory;
import com.returnpanda.domain.ResponseResult;
import com.returnpanda.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {
    @Autowired
    private ResourceCategoryService resourceCategoryService;

    //查询所有资源分类
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> list = resourceCategoryService.findAllResourceCategory();
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有资源分类",list);
        return responseResult;
    }
}
