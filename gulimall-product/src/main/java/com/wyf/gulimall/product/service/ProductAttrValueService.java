package com.wyf.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.product.entity.ProductAttrValueEntity;
import com.wyf.gulimall.product.entity.vo.SpuItemAttrGroupVo;
import com.wyf.gulimall.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * spu
 *
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SpuItemAttrGroupVo> getProductGroupAttrsBySpuId(Long spuId, Long catalogId);
}

