package com.wyf.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.utils.PageUtils;

import com.wyf.gulimall.coupon.entity.SeckillPromotionEntity;

import java.util.Map;

/**
 * 秒杀活动
 *
 * @author wyf
 * @email 
 * @date 2022-08-11 10:33:53
 */
public interface SeckillPromotionService extends IService<SeckillPromotionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

