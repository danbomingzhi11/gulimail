package com.wyf.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.product.entity.SkuInfoEntity;
import com.wyf.gulimall.product.entity.vo.SkuItemVo;
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
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据条件查询对应的sku信息
     * @param params
     * @return
     */
    PageUtils selectAllSkuByMessage(Map<String, Object> params);

    List<SkuInfoEntity> getSkusBySpuId(Long spuId);

    SkuItemVo item(Long skuId);
}

