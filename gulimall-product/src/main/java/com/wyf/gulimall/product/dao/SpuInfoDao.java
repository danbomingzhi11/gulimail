package com.wyf.gulimall.product.dao;

import com.wyf.gulimall.product.entity.SkuInfoEntity;
import com.wyf.gulimall.product.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * spu信息
 * 
 * @author wyf
 * @email 
 * @date 2022-08-09 14:47:46
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {
    List<SkuInfoEntity> selectAllSpuByMessage(@Param("params") Map<String, Object> params);
}
