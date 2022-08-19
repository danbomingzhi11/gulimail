package com.wyf.gulimall.product.dao;

import com.wyf.gulimall.product.entity.AttrGroupEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyf.gulimall.utils.PageUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 * 
 * @author wyf
 * @email 
 * @date 2022-08-09 14:47:46
 */
@Mapper
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {
    List<AttrGroupEntity> queryGroupBySomething(@Param("params") Map<String, Object> params);
}
