package com.wyf.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.R;
import com.wyf.gulimall.valid.AddGroup;
import com.wyf.gulimall.valid.UpdateGroup;
import com.wyf.gulimall.valid.UpdateStatusGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyf.gulimall.product.entity.BrandEntity;
import com.wyf.gulimall.product.service.BrandService;




/**
 * Ʒ
 *
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
@Api(value = "品牌API", tags = {"品牌API"})
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 新增品牌
     */
    @ApiOperation(value = "新增品牌", notes = "新增品牌", httpMethod = "POST")
    @RequestMapping("/save")
    public R save(@Validated({AddGroup.class}) @RequestBody BrandEntity brand){
		brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改品牌
     */
    @ApiOperation(value = "修改品牌", notes = "修改品牌", httpMethod = "POST")
    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand){
		brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 修改状态
     */
    @ApiOperation(value = "修改状态", notes = "修改状态", httpMethod = "POST")
    @RequestMapping("/update/status")
    //@RequiresPermissions("product:brand:update")
    public R updateStatus(@Validated(UpdateStatusGroup.class) @RequestBody BrandEntity brand){
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
