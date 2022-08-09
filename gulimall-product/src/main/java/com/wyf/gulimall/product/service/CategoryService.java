package com.wyf.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.product.entity.CategoryEntity;
import com.wyf.gulimall.product.entity.bo.CategoryBo;
import com.wyf.gulimall.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查找分类表所有的内容
     * @return
     */
    List<CategoryBo> selectCategoryList();

    /**
     * 根据ID数组逻辑删除分类
     * @param idList
     */
     void removeMenuByIds(List<Long> idList);

    /**
     * 新增子类
     * @param category
     */
    void saveCategoryByBody(CategoryEntity category);

    /**
     * 修改子类
     * @param categoryEntity
     */
    void updateCategoryByBody(CategoryEntity categoryEntity);
 }

