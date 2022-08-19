package com.wyf.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.product.entity.AttrEntity;
import com.wyf.gulimall.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 新增属性，并且新增属性与属性组关联
     * @param attrEntity
     */
    void saveAttrAndGroup(AttrEntity attrEntity);

    /**
     * 获取分类销售属性
     * @param catelogId
     * @param params
     * @return
     */
    PageUtils selectAllAttrByCatelogId(Long catelogId, Map<String, Object> params);
}

