package com.returnpanda.controller;

import com.returnpanda.domain.PromotionSpace;
import com.returnpanda.domain.ResponseResult;
import com.returnpanda.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {
    @Autowired
    private PromotionSpaceService promotionSpaceService;

    //获取所有广告位
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();
        ResponseResult responseResult = new ResponseResult(true, 200, "获取所有广告位成功",allPromotionSpace);
        return responseResult;
    }

    //添加和修改广告位信息
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        System.out.println("c"+promotionSpace);
        ResponseResult responseResult=null;
        if (promotionSpace.getId()==null){
            promotionSpaceService.savePromotionSpace(promotionSpace);
            responseResult = new ResponseResult(true, 200, "添加广告位成功",null);
        }else {
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            responseResult = new ResponseResult(true, 200, "修改广告位信息成功",null);
        }

        return responseResult;
    }


    //回显广告位名称
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(@RequestParam Integer id){
        PromotionSpace promotionSpaceById = promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "回显广告位名称成功",promotionSpaceById);
        return responseResult;
    }
}
