package com.wyf.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.coupon.entity.SpuBoundsEntity;
import com.wyf.gulimall.utils.PageUtils;


import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author Ethan
 * @email hongshengmo@163.com
 * @date 2020-05-27 20:03:33
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

