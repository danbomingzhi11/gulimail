package com.wyf.gulimall.order.dao;

import com.wyf.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author wyf
 * @email 
 * @date 2022-08-03 09:13:15
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
