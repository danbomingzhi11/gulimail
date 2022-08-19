package com.wyf.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.wyf.gulimall.product.entity.BrandEntity;
import com.wyf.gulimall.product.entity.vo.BrandVo;
import com.wyf.gulimall.product.entity.vo.CategoryBrandVo;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyf.gulimall.product.entity.CategoryBrandRelationEntity;
import com.wyf.gulimall.product.service.CategoryBrandRelationService;



/**
 * Ʒ
 *
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
@Api(value = "品牌与商品分类API", tags = {"品牌与商品分类API"})
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 根据品牌ID 找到所有的 对应商品关系
     */
    @ApiOperation(value = "根据品牌ID 找到所有的 对应商品关系", notes = "根据品牌ID 找到所有的 对应商品关系", httpMethod = "GET")
    @RequestMapping("/catelog/list")
    public R list(@RequestParam Map<String, Object> params){
        String brandId = (String) params.get("brandId");
        List<CategoryBrandVo> categoryBrandVos = categoryBrandRelationService.selectCategoryByBrandId(brandId);

        return R.ok().put("data", categoryBrandVos);
    }

    /**
     * 根据分类id查询 对应的品牌信息
     */
    @ApiOperation(value = "根据分类id查询 对应的品牌信息", notes = "根据分类id查询 对应的品牌信息", httpMethod = "GET")
    @RequestMapping("/brands/list")
    public R bradList(@RequestParam Map<String, Object> params){
        String cateId = (String) params.get("catId");
        List<BrandVo> brandVos = categoryBrandRelationService.selectBrandByCategoryId(cateId);

        return R.ok().put("data",brandVos);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:categorybrandrelation:info")
    public R info(@PathVariable("id") Long id){
		CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 新增关联关系
     */
    @ApiOperation(value = "新增关联关系", notes = "新增关联关系", httpMethod = "POST")
    @RequestMapping("/save")
    //@RequiresPermissions("product:categorybrandrelation:save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
		categoryBrandRelationService.selectByIdAndSave(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:categorybrandrelation:update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
		categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:categorybrandrelation:delete")
    public R delete(@RequestBody Long[] ids){
		categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
