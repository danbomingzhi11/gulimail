package com.wyf.gulimall.product.service.impl;

import com.wyf.gulimall.product.entity.bo.CategoryBo;
import com.wyf.gulimall.product.entity.vo.Catalog2Vo;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.wyf.gulimall.product.dao.CategoryDao;
import com.wyf.gulimall.product.entity.CategoryEntity;
import com.wyf.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {


    @Autowired
    private CategoryDao categoryDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }


    // 查找分类表所有的内容
    @Override
    public List<CategoryBo> selectCategoryList() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        List<CategoryBo> categoryBoList = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntities) {
            CategoryBo categoryBo = new CategoryBo();
            BeanUtils.copyProperties(categoryEntity, categoryBo);
            categoryBoList.add(categoryBo);
        }
        // 2.组装 树形结构
        List<CategoryBo> result = createTreeCategory(categoryBoList);

        return result;
    }

    // 根据ID数组逻辑删除分类
    @Override
    public void removeMenuByIds(List<Long> idList) {
        categoryDao.deleteBatchIds(idList);
    }

    //新增子类
    @Override
    public void saveCategoryByBody(CategoryEntity category) {
        categoryDao.insert(category);
    }

    // 修改子类
    @Override
    public void updateCategoryByBody(CategoryEntity categoryEntity) {
        categoryDao.updateById(categoryEntity);
    }

    // 返回分类父子级树级关系
    @Override
    public List<Long> findCategoryList(Long cateId) {
        List<Long> list = new ArrayList<>();
        List<Long> result = findAll(cateId, list);
        return result;
    }

    // 递归查找父子级
    private List<Long> findAll(Long cateId, List<Long> list) {
        CategoryEntity categoryEntity = categoryDao.selectById(cateId);
        if (categoryEntity.getParentCid() != 0) {
            this.findAll(categoryEntity.getParentCid(), list);
        }
        list.add(categoryEntity.getCatId());
        return list;
    }

    // 根据一级分类 查询二级三级分类并且返回


    @Override
    public Map<String, List<Catalog2Vo>> getCatelogJson() {
        // 获取一级分类
        List<CategoryEntity> parent_cid = this.list(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
        Map<String, List<Catalog2Vo>> listMap = parent_cid.stream()
                .collect(Collectors.toMap(k -> {
                    // 将一级分类的id 作为 K 键
                    return k.getCatId().toString();
                }, V -> {
                    // 创建接受二级级分类的 类
                    List<Catalog2Vo> catalog2Vos = null;
                    // 获取所有二级分类
                    List<CategoryEntity> level2 = this.list(new QueryWrapper<CategoryEntity>().eq("parent_cid", V.getCatId()));
                    if (level2 != null) {
                        catalog2Vos = level2.stream()
                                .map(leve2 -> {
                                    // 查出三级分类
                                    List<CategoryEntity> level3 = this.list(new QueryWrapper<CategoryEntity>().eq("parent_cid", leve2.getCatId()));
                                    // 创建接受三级分类的 类
                                    List<Catalog2Vo.Catalog3Vo> catalog3Vos = null;
                                    if (level3 != null) {
                                        catalog3Vos = level3.stream()
                                                .map(leve3 -> {
                                                    // 给三级分类的对应Entity赋值
                                                    Catalog2Vo.Catalog3Vo catalog3Vo = new Catalog2Vo.Catalog3Vo(leve2.getCatId().toString(), leve3.getCatId().toString(), leve3.getName());
                                                    return catalog3Vo;
                                                }).collect(Collectors.toList());
                                    }
                                    // 将三级分类赋值到对应的 entity的类 加入到二级分类
                                    Catalog2Vo catalog2Vo = new Catalog2Vo(V.getParentCid().toString(), leve2.getCatId().toString(), leve2.getName(), catalog3Vos);
                                    return catalog2Vo;
                                }).collect(Collectors.toList());
                    }
                    return catalog2Vos;
                }));

        return listMap;
    }

    // 查找所有一级分类
    @Override
    public List<CategoryEntity> getLevel1Catagories() {
        List<CategoryEntity> parent_cid = this.list(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));

        return parent_cid;
    }

    public List<CategoryBo> createTreeCategory(List<CategoryBo> categoryBoList) {
        // 2.1 减少for循环次数，将数组分为三个
        List<CategoryBo> oneLevelCategoryList = new ArrayList<>();
        List<CategoryBo> twoLevelCategoryList = new ArrayList<>();
        List<CategoryBo> threeLevelCategoryList = new ArrayList<>();

        for (CategoryBo category : categoryBoList) {
            if (category.getCatLevel() == 1) {
                oneLevelCategoryList.add(category);
            }
        }

        for (CategoryBo category : categoryBoList) {
            if (category.getCatLevel() == 2) {
                twoLevelCategoryList.add(category);
            }
        }

        for (CategoryBo category : categoryBoList) {
            if (category.getCatLevel() == 3) {
                threeLevelCategoryList.add(category);
            }
        }


        // 递归包装树形类
        // 一层循环 遍历一级类目
        for (CategoryBo oneCategory : oneLevelCategoryList) {
            // 二层循环 ，寻找二级类目符合条件并 包装进children数组
            for (CategoryBo twoCategory : twoLevelCategoryList) {
                // 判断 一级类 是否等于 二级类数组里的父级id
                if (oneCategory.getCatId() == twoCategory.getParentCid()) {
                    // 添加进父级 children数组
                    oneCategory.getChildren().add(twoCategory);

                }
            }
            // 获取当前一级节点的二级分类数组
            List<CategoryBo> snapTwoLevelCategoryList = oneCategory.getChildren();
            for (CategoryBo snapTwoCategory : snapTwoLevelCategoryList) {
                // 三层层循环 寻找三级级类目符合条件并 包装进children数组
                for (CategoryBo threeCategory : threeLevelCategoryList) {
                    // 判断 二级类 是否等于 三级类数组里的父级id
                    if (snapTwoCategory.getCatId() == threeCategory.getParentCid()) {
                        // 添加进父级 children数组
                        snapTwoCategory.getChildren().add(threeCategory);
                        twoLevelCategoryList.remove(snapTwoCategory);
                    }
                }
            }
        }

        return oneLevelCategoryList;
    }
}