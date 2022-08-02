package com.wyf.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.product.entity.CategoryEntity;
import com.wyf.gulimall.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

