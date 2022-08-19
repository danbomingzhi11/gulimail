package com.wyf.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.wyf.gulimall.product.entity.vo.SpuSaveVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyf.gulimall.product.entity.SpuInfoEntity;
import com.wyf.gulimall.product.service.SpuInfoService;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.R;



/**
 * spu信息
 *
 * @author wyf
 * @email 
 * @date 2022-08-09 14:47:46
 */
@Api(value = "Spu信息Api", tags = {"Spu信息API"})
@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;

    /**
     * 根据条件查找spu信息
     */
    @ApiOperation(value = "根据条件查找spu信息", notes = "根据条件查找spu信息", httpMethod = "GET")
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = spuInfoService.selectAllSpuByMessage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("product:spuinfo:info")
    public R info(@PathVariable("id") Long id){
		SpuInfoEntity spuInfo = spuInfoService.getById(id);

        return R.ok().put("spuInfo", spuInfo);
    }

    /**
     * 保存增加商品的所有信息
     */
    @ApiOperation(value = "保存增加商品的所有信息", notes = "保存增加商品的所有信息", httpMethod = "POST")
    @RequestMapping("/save")
    public R save(@RequestBody SpuSaveVo spuSaveVo){
		spuInfoService.savaAll(spuSaveVo);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:spuinfo:update")
    public R update(@RequestBody SpuInfoEntity spuInfo){
		spuInfoService.updateById(spuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:spuinfo:delete")
    public R delete(@RequestBody Long[] ids){
		spuInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
