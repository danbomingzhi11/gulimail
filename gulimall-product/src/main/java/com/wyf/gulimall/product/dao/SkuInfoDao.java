package com.wyf.gulimall.product.dao;

import com.wyf.gulimall.product.entity.SkuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * sku
 * 
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
@Mapper
public interface SkuInfoDao extends BaseMapper<SkuInfoEntity> {


    List<SkuInfoEntity> selectAllSkuByMessage(@Param("params") Map<String, Object> params);
}
