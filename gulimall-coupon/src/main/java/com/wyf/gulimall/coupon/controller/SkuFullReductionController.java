package com.wyf.gulimall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import com.wyf.gulimall.to.SkuReductionTo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wyf.gulimall.coupon.entity.SkuFullReductionEntity;
import com.wyf.gulimall.coupon.service.SkuFullReductionService;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.R;



/**
 * 商品满减信息
 *
 * @author wyf
 * @email 
 * @date 2022-08-02 23:28:13
 */
@Api(value = "优惠满减服务API", tags = {"优惠满减服务API"})
@RestController
@RequestMapping("coupon/skufullreduction")
public class SkuFullReductionController {
    @Autowired
    private SkuFullReductionService skuFullReductionService;

    @ApiOperation(value = "远程调用API 保存满减数据", notes = "远程调用API 保存满减数据", httpMethod = "POST")
    @PostMapping("/saveInfo")
    public R saveSkuReductionTo(@RequestBody SkuReductionTo skuReductionTo) {
        skuFullReductionService.saveSkuReductionTo(skuReductionTo);
        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("coupon:skufullreduction:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = skuFullReductionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("coupon:skufullreduction:info")
    public R info(@PathVariable("id") Long id){
		SkuFullReductionEntity skuFullReduction = skuFullReductionService.getById(id);

        return R.ok().put("skuFullReduction", skuFullReduction);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("coupon:skufullreduction:save")
    public R save(@RequestBody SkuFullReductionEntity skuFullReduction){
		skuFullReductionService.save(skuFullReduction);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("coupon:skufullreduction:update")
    public R update(@RequestBody SkuFullReductionEntity skuFullReduction){
		skuFullReductionService.updateById(skuFullReduction);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("coupon:skufullreduction:delete")
    public R delete(@RequestBody Long[] ids){
		skuFullReductionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
