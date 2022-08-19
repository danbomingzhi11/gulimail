package com.wyf.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyf.gulimall.product.entity.AttrEntity;
import com.wyf.gulimall.product.service.AttrService;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.R;


/**
 * 
 *
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */

@Api(value = "商品属性", tags = {"商品属性"})
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    /**
     * 获取分类销售属性
     */
    @ApiOperation(value = "获取分类销售属性", notes = "获取分类销售属性", httpMethod = "GET")
    @RequestMapping("/sale/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable Long catelogId){
        PageUtils page = attrService.selectAllAttrByCatelogId(catelogId, params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    //@RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
		AttrEntity attr = attrService.getById(attrId);

        return R.ok().put("attr", attr);
    }

    /**
     * 新增属性，并且新增属性与属性组关联
     */
    @ApiOperation(value = "新增属性，并且新增属性与属性组关联", notes = "新增属性，并且新增属性与属性组关联", httpMethod = "POST")
    @RequestMapping("/save")
    //@RequiresPermissions("product:attr:save")
    public R save(@RequestBody AttrEntity attr){
		attrService.saveAttrAndGroup(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrEntity attr){
		attrService.updateById(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
