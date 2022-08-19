package com.wyf.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.to.SkuReductionTo;
import com.wyf.gulimall.utils.PageUtils;

import com.wyf.gulimall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author wyf
 * @email 
 * @date 2022-08-02 23:28:13
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 远保存满减数据
     * @param skuReductionTo
     */
    void saveSkuReductionTo(SkuReductionTo skuReductionTo);
}

