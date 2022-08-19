package com.wyf.gulimall.product.service.impl;

import com.wyf.gulimall.product.dao.BrandDao;
import com.wyf.gulimall.product.dao.CategoryDao;
import com.wyf.gulimall.product.entity.BrandEntity;
import com.wyf.gulimall.product.entity.CategoryEntity;
import com.wyf.gulimall.product.entity.vo.BrandVo;
import com.wyf.gulimall.product.entity.vo.CategoryBrandVo;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wyf.gulimall.product.dao.CategoryBrandRelationDao;
import com.wyf.gulimall.product.entity.CategoryBrandRelationEntity;
import com.wyf.gulimall.product.service.CategoryBrandRelationService;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    // 根据品牌ID 找到所有的 对应商品关系
    @Override
    public List<CategoryBrandVo> selectCategoryByBrandId(String brandId) {
        List<CategoryBrandVo> categoryBrandVos = categoryBrandRelationDao.selectCategoryByBrandId(brandId);

        return categoryBrandVos;
    }

    // 新增关联关系
    @Override
    public void selectByIdAndSave(CategoryBrandRelationEntity entity) {
        Long brand = entity.getBrandId();
        Long cate =  entity.getCatelogId();
        BrandEntity brandEntity = brandDao.selectById(brand);
        CategoryEntity categoryEntity = categoryDao.selectById(cate);

        entity.setBrandName(brandEntity.getName());
        entity.setCatelogName(categoryEntity.getName());
        categoryBrandRelationDao.insert(entity);
    }

    //根据分类id查询 对应的品牌信息
    @Override
    public List<BrandVo> selectBrandByCategoryId(String cateId) {
        List<BrandVo> brandVos = categoryBrandRelationDao.selectBrandyByCateId(cateId);

        return brandVos;
    }
}