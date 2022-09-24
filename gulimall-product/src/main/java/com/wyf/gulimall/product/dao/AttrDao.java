package com.wyf.gulimall.product.dao;

import com.wyf.gulimall.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {
	List<AttrEntity> selectAllAttrByCatelogId(Long catelogId, @Param("params") Map<String, Object> params);

    List<Long> selectSearchAttrIds(@Param("attrIds") List<Long> attrIds);
}
