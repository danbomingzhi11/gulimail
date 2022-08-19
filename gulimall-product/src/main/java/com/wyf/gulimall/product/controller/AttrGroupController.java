package com.wyf.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.wyf.gulimall.product.entity.CategoryEntity;
import com.wyf.gulimall.product.entity.bo.ArrGroupBo;
import com.wyf.gulimall.product.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyf.gulimall.product.entity.AttrGroupEntity;
import com.wyf.gulimall.product.service.AttrGroupService;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.R;



/**
 * 属性分组
 *
 * @author wyf
 * @email 
 * @date 2022-08-09 14:47:46
 */
@Api(value = "属性分组API", tags = {"属性分组API"})
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据cateId查询对应的分组属性以及属性。
     */
    @ApiOperation(value = "根据catelogId 查询对应group表的数据 and 通过模糊查询or分组id的方式 查询对应的数据", notes = "根据catelogId 查询对应group表的数据 and 通过模糊查询or分组id的方式 查询对应的数据", httpMethod = "GET")
    @RequestMapping("{cateId}/withattr")
    public R selectAllGroupAndArrByCateId(@PathVariable String cateId) {
         List<ArrGroupBo> arrGroupBos = attrGroupService.selectAllGroupAndArrByCateId(cateId);

         return R.ok().put("data", arrGroupBos);
    }
    /**
     *  根据catelogId 查询对应group表的数据 and 通过模糊查询or分组id的方式 查询对应的数据
     */
    @ApiOperation(value = "根据catelogId 查询对应group表的数据 and 通过模糊查询or分组id的方式 查询对应的数据", notes = "根据catelogId 查询对应group表的数据 and 通过模糊查询or分组id的方式 查询对应的数据", httpMethod = "GET")
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable Integer catelogId){

        PageUtils page = attrGroupService.queryGroupBySomething(catelogId, params);

        return R.ok().put("page", page);
    }


    /**
     * 信息以及返回分组信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

		Long id = attrGroup.getCatelogId();
        List<Long> arr = categoryService.findCategoryList(id);
		attrGroup.setCatelogPath(arr);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
