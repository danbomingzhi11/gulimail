package com.wyf.gulimall.product.service.impl;

import com.wyf.gulimall.product.config.MyThreadPoolConfig;
import com.wyf.gulimall.product.config.ThreadPoolConfigProperties;
import com.wyf.gulimall.product.entity.SkuImagesEntity;
import com.wyf.gulimall.product.entity.SpuInfoDescEntity;
import com.wyf.gulimall.product.entity.vo.SkuItemSaleAttrVo;
import com.wyf.gulimall.product.entity.vo.SkuItemVo;
import com.wyf.gulimall.product.entity.vo.SpuItemAttrGroupVo;
import com.wyf.gulimall.product.service.*;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.wyf.gulimall.product.dao.SkuInfoDao;
import com.wyf.gulimall.product.entity.SkuInfoEntity;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Autowired
    private SkuInfoDao skuInfoDao;

    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private SpuInfoDescService spuInfoDescService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    private ThreadPoolExecutor executor;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuInfoEntity> page = this.page(
                new Query<SkuInfoEntity>().getPage(params),
                new QueryWrapper<SkuInfoEntity>()
        );

        return new PageUtils(page);
    }

    // 根据条件查询对应的sku信息

    @Override
    public PageUtils selectAllSkuByMessage(Map<String, Object> params) {
        List<SkuInfoEntity> skuInfoEntities = skuInfoDao.selectAllSkuByMessage(params);
        int page = Integer.parseInt((String) params.get("page"));
        int pageSize = Integer.parseInt((String) params.get("limit"));

        PageUtils pageUtils = new PageUtils();
        pageUtils.setCurrPage(page);
        pageUtils.setPageSize(pageSize);
        pageUtils.setList(skuInfoEntities);
        return pageUtils;
    }

    @Override
    public SkuItemVo item(Long skuId) {
        SkuItemVo skuItemVo = new SkuItemVo();

        CompletableFuture<SkuInfoEntity> infoFuture = CompletableFuture.supplyAsync(() -> {
            //1、sku基本信息的获取  pms_sku_info
            SkuInfoEntity skuInfoEntity = this.getById(skuId);
            skuItemVo.setInfo(skuInfoEntity);
            return skuInfoEntity;
        }, executor);

        //2、sku的图片信息    pms_sku_images
        CompletableFuture<Void> imageFuture = CompletableFuture.runAsync(() -> {
            List<SkuImagesEntity> skuImagesEntities = skuImagesService.list(new QueryWrapper<SkuImagesEntity>().eq("sku_id", skuId));
            skuItemVo.setImages(skuImagesEntities);
        }, executor);


        //3、获取spu的销售属性组合-> 依赖1 获取spuId
        CompletableFuture<Void> saleFuture = infoFuture.thenAcceptAsync((info) -> {
            List<SkuItemSaleAttrVo> saleAttrVos = skuSaleAttrValueService.listSaleAttrs(info.getSpuId());
            skuItemVo.setSaleAttr(saleAttrVos);
        }, executor);


        //4、获取spu的介绍-> 依赖1 获取spuId
        CompletableFuture<Void> descFuture = infoFuture.thenAcceptAsync((info) -> {
            SpuInfoDescEntity byId = spuInfoDescService.getById(info.getSpuId());
            skuItemVo.setDesc(byId);
        }, executor);


        //5、获取spu的规格参数信息-> 依赖1 获取spuId catalogId
        CompletableFuture<Void> attrFuture = infoFuture.thenAcceptAsync((info) -> {
            List<SpuItemAttrGroupVo> spuItemAttrGroupVos=productAttrValueService.getProductGroupAttrsBySpuId(info.getSpuId(), info.getCatalogId());
            skuItemVo.setGroupAttrs(spuItemAttrGroupVos);
        }, executor);

        return skuItemVo;
    }


    @Override
    public List<SkuInfoEntity> getSkusBySpuId(Long spuId) {
        List<SkuInfoEntity> skus = this.list(new QueryWrapper<SkuInfoEntity>().eq("spu_id", spuId));
        return skus;
    }
}