package com.wyf.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.product.entity.SpuCommentEntity;
import com.wyf.gulimall.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

