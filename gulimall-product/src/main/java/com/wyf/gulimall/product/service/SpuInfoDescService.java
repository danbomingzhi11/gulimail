package com.wyf.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.utils.PageUtils;

import com.wyf.gulimall.product.entity.SpuInfoDescEntity;

import java.util.Map;

/**
 * spu信息介绍
 *
 * @author wyf
 * @email 
 * @date 2022-08-09 14:47:46
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

