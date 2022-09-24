package com.wyf.gulimall.product.dao;

import com.wyf.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyf.gulimall.product.entity.vo.SkuItemSaleAttrVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sku
 * 
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
@Mapper
public interface SkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValueEntity> {
    List<SkuItemSaleAttrVo> listSaleAttrs(@Param("spuId") Long spuId);
}
