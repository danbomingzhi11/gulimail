package com.wyf.gulimall.ware.dao;

import com.wyf.gulimall.ware.entity.WareInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 仓库信息
 * 
 * @author wyf
 * @email 
 * @date 2022-08-03 09:27:19
 */
@Mapper
public interface WareInfoDao extends BaseMapper<WareInfoEntity> {

    List<WareInfoEntity> selectWareByMessage(@Param("params") Map<String, Object> params);
}
