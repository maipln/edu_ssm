package com.returnpanda.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.returnpanda.dao.PromotionAdMapper;
import com.returnpanda.domain.PromotionAd;
import com.returnpanda.domain.PromotionAdVO;
import com.returnpanda.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {
    @Autowired
    private PromotionAdMapper promotionAdMapper;

    //分页查询广告信息
    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {

        PageHelper.startPage(promotionAdVO.getCurrentPage(),promotionAdVO.getPageSize());
        List<PromotionAd> allPromotionAdByPage = promotionAdMapper.findAllPromotionAdByPage();
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(allPromotionAdByPage);
        return pageInfo;
    }


    //新建广告信息
    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        //补全信息
        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    //修改广告信息
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        //补全信息
        Date date = new Date();
        promotionAd.setUpdateTime(date);
        promotionAdMapper.updatePromotionAd(promotionAd);

    }


    //根据id查询广告信息
    @Override
    public PromotionAd findPromotionAdById(Integer id) {
        PromotionAd promotionAdById = promotionAdMapper.findPromotionAdById(id);
        return promotionAdById;
    }


    //广告状态上下线
    @Override
    public void updatePromotionAdStatus(Integer id , Integer status) {
        PromotionAd promotionAd = new PromotionAd();
        //封装补全信息
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        Date date = new Date();
        promotionAd.setUpdateTime(date);
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }


}
