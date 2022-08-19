package com.wyf.gulimall.product.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.alibaba.nacos.client.utils.JSONUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyf.gulimall.product.entity.bo.CategoryBo;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyf.gulimall.product.entity.CategoryEntity;
import com.wyf.gulimall.product.service.CategoryService;


/**
 * @author wyf
 * @email
 * @date 2022-08-02 21:12:22
 */
@Api(value = "三级分类API", tags = {"三级分类API"})
@RestController
@RequestMapping("/product/category")
public class CategoryController {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询所有三级分类以及子分类列表
     */
    @ApiOperation(value = "查询三级分类以及子分类API", notes = "查询三级分类以及子分类API", httpMethod = "GET")
    @RequestMapping("/list/tree")
    public R listTree() {

        // 1.调用Server层 查询所有父类 以及子分类
        List<CategoryBo> categoryBo = categoryService.selectCategoryList();

        return R.ok().put("data", categoryBo);
    }


    /**
     * 根据id查询对应的category数据
     */
    @ApiOperation(value = "根据id查询对应的category数据", notes = "根据id查询对应的category数据", httpMethod = "GET")
    @RequestMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId) {
        CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("category", category);
    }

    /**
     * 新增子类
     */
    @ApiOperation(value = "新增子类", notes = "新增子类", httpMethod = "POST")
    @RequestMapping("/save")
    public R save(@RequestBody CategoryEntity category) {
        categoryService.saveCategoryByBody(category);

        return R.ok();
    }

    /**
     * 修改类别
     */
    @ApiOperation(value = "修改类别", notes = "修改类别", httpMethod = "POST")
    @RequestMapping("/update")
    //@RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity category) {
        categoryService.updateCategoryByBody(category);

        return R.ok();
    }

    /**
     * 根据Id数组逻辑删除对应的分类
     */
    @ApiOperation(value = "根据Id数组逻辑删除对应的分类", notes = "根据Id数组逻辑删除对应的分类", httpMethod = "POST")
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds) {
        categoryService.removeMenuByIds(Arrays.asList(catIds));
        return R.ok();
    }


    /**
     * 前端拖拽改变分类
     * @param category
     * @return
     */
    @ApiOperation(value = "前端拖拽改变分类", notes = "前端拖拽改变分类", httpMethod = "POST")
    @RequestMapping("/update/sort")
    public R updateSort(@RequestBody CategoryEntity[] category){
        categoryService.updateBatchById(Arrays.asList(category));
        return R.ok();
    }

}
