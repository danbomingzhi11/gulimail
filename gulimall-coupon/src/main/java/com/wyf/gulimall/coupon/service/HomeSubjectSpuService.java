package com.wyf.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.utils.PageUtils;

import com.wyf.gulimall.coupon.entity.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * 专题商品
 *
 * @author wyf
 * @email 
 * @date 2022-08-11 10:33:53
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

