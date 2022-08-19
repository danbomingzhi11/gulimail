package com.wyf.gulimall.product.dao;

import com.wyf.gulimall.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyf.gulimall.product.entity.vo.BrandVo;
import com.wyf.gulimall.product.entity.vo.CategoryBrandVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Ʒ
 * 
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {
    /**
     * 根据品牌ID 找到所有的 对应商品关系
     * @param brandId
     * @return
     */
    List<CategoryBrandVo> selectCategoryByBrandId(String brandId);

    List<BrandVo> selectBrandyByCateId(String cateId);
}
