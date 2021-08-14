package com.returnpanda.dao;

import com.returnpanda.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    //分页查询广告信息
    public List<PromotionAd> findAllPromotionAdByPage();

    //新建广告信息
    public void savePromotionAd(PromotionAd promotionAd);

    //修改广告信息
    public void updatePromotionAd(PromotionAd promotionAd);

    //根据id查询广告信息
    public PromotionAd findPromotionAdById(Integer id);

    //广告状态上下线
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
