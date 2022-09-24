package com.wyf.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyf.gulimall.product.entity.vo.SpuSaveVo;
import com.wyf.gulimall.utils.PageUtils;

import com.wyf.gulimall.product.entity.SpuInfoEntity;

import java.util.Map;

/**
 * spu信息
 *
 * @author wyf
 * @email 
 * @date 2022-08-09 14:47:46
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存增加商品的所有信息
     * @param spuSaveVo
     */
    void savaAll(SpuSaveVo spuSaveVo);

    /**
     * 根据条件查找spu信息
     * @param params
     * @return
     */
    PageUtils selectAllSpuByMessage(Map<String, Object> params);

    void upSpuForSearch(Long spuId);
}

