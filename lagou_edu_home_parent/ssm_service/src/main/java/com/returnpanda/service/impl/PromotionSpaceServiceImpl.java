package com.returnpanda.service.impl;

import com.returnpanda.dao.PromotionSpaceMapper;
import com.returnpanda.domain.PromotionSpace;
import com.returnpanda.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    //获取所有广告位
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> list = promotionSpaceMapper.findAllPromotionSpace();
        return list;
    }

    //添加广告位
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        //封装补全数据
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        promotionSpace.setIsDel(0);
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    //修改广告位信息
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace){
        //封装补全数据
        Date date = new Date();
        promotionSpace.setUpdateTime(date);
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);

    }

    //回显广告位名称
    @Override
    public PromotionSpace findPromotionSpaceById(Integer id) {
        PromotionSpace promotionSpaceById = promotionSpaceMapper.findPromotionSpaceById(id);
        return promotionSpaceById;
    }
}
