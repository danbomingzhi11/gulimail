package com.wyf.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.utils.PageUtils;

import com.wyf.gulimall.coupon.entity.SeckillSessionEntity;

import java.util.Map;

/**
 * 秒杀活动场次
 *
 * @author wyf
 * @email 
 * @date 2022-08-11 10:33:53
 */
public interface SeckillSessionService extends IService<SeckillSessionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

