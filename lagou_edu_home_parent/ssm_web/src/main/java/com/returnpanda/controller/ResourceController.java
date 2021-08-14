package com.returnpanda.controller;

import com.github.pagehelper.PageInfo;
import com.returnpanda.domain.Resource;
import com.returnpanda.domain.ResourceVO;
import com.returnpanda.domain.ResponseResult;
import com.returnpanda.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    //资源分页和对条件查询
    @RequestMapping("/findAllResource")
    private ResponseResult findAllResource(@RequestBody ResourceVO resourceVO){
        PageInfo<Resource> allResourceByPage = resourceService.findAllResourceByPage(resourceVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "资源分页和对条件查询",allResourceByPage);
        return responseResult;
    }

    //按id查询资源信息回显
    @RequestMapping("/findResourceById")
    private ResponseResult findResourceById(Integer id){
        Resource resourceById = resourceService.findResourceById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "按id查询资源信息回显",resourceById);
        return responseResult;
    }

    //添加/修改资源信息
    @RequestMapping("/saveOrUpdateResource")
    private ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        ResponseResult responseResult;
        if (resource.getId()==null){
            resourceService.saveResource(resource);
            responseResult = new ResponseResult(true, 200, "添加资源信息",null);
        }else {
            resourceService.updateResource(resource);
            responseResult = new ResponseResult(true, 200, "修改资源信息",null);
        }
        return responseResult;
    }


    //删除资源信息
    @RequestMapping("/deleteResource")
    private ResponseResult deleteResource(Integer id){
        resourceService.deleteResource(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除资源信息",null);
        return responseResult;
    }

}
