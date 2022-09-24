package com.wyf.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.wyf.gulimall.product.entity.vo.SkuItemSaleAttrVo;
import com.wyf.gulimall.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * sku
 *
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuItemSaleAttrVo> listSaleAttrs(Long spuId);
}

