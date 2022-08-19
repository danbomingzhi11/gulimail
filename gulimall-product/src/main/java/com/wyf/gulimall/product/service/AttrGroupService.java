package com.wyf.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.product.entity.bo.ArrGroupBo;
import com.wyf.gulimall.utils.PageUtils;

import com.wyf.gulimall.product.entity.AttrGroupEntity;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author wyf
 * @email 
 * @date 2022-08-09 14:47:46
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据catelogId 查询对应group表的数据 and 通过模糊查询or分组id的方式 查询对应的数据
     * @param catelogId
     * @param params
     * @return
     */
    PageUtils queryGroupBySomething(Integer catelogId, Map<String, Object> params);

    /**
     * 根据cateId查询对应的分组属性以及属性。
     * @param cateId
     * @return
     */
    List<ArrGroupBo> selectAllGroupAndArrByCateId(String cateId);
}

