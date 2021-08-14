package com.returnpanda.service;

import com.github.pagehelper.PageInfo;
import com.returnpanda.domain.PromotionAd;
import com.returnpanda.domain.PromotionAdVO;



public interface PromotionAdService {


    //分页查询广告信息
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

    //新建广告信息
    public void savePromotionAd(PromotionAd promotionAd);

    //修改广告信息
    public void updatePromotionAd(PromotionAd promotionAd);

    //根据id查询广告信息
    public PromotionAd findPromotionAdById(Integer id);

    //广告状态上下线
    public void updatePromotionAdStatus(Integer id , Integer status);
}
