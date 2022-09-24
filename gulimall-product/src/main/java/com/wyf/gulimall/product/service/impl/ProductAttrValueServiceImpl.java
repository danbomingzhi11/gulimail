package com.wyf.gulimall.product.service.impl;

import com.wyf.gulimall.product.entity.vo.SpuItemAttrGroupVo;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.wyf.gulimall.product.dao.ProductAttrValueDao;
import com.wyf.gulimall.product.entity.ProductAttrValueEntity;
import com.wyf.gulimall.product.service.ProductAttrValueService;


@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductAttrValueEntity> page = this.page(
                new Query<ProductAttrValueEntity>().getPage(params),
                new QueryWrapper<ProductAttrValueEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SpuItemAttrGroupVo> getProductGroupAttrsBySpuId(Long spuId, Long catalogId) {

        return baseMapper.getProductGroupAttrsBySpuId(spuId, catalogId);
    }
}