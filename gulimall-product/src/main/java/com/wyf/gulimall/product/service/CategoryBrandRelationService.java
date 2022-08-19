package com.wyf.gulimall.product.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.product.entity.CategoryBrandRelationEntity;
import com.wyf.gulimall.product.entity.vo.BrandVo;
import com.wyf.gulimall.product.entity.vo.CategoryBrandVo;
import com.wyf.gulimall.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * Ʒ
 *
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据品牌ID 找到所有的 对应商品关系
     * @param brandId
     * @return
     */
    List<CategoryBrandVo> selectCategoryByBrandId(String brandId);

    /**
     * 新增关联关系
     * @param entity
     * @return
     */
     void selectByIdAndSave(CategoryBrandRelationEntity entity);

    /**
     * 根据分类id查询 对应的品牌信息
     * @param cateId
     * @return
     */
     List<BrandVo> selectBrandByCategoryId(String cateId);
}

