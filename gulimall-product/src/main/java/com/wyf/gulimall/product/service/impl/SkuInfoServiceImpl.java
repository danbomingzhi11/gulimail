package com.wyf.gulimall.product.service.impl;

import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.wyf.gulimall.product.dao.SkuInfoDao;
import com.wyf.gulimall.product.entity.SkuInfoEntity;
import com.wyf.gulimall.product.service.SkuInfoService;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Autowired
    private SkuInfoDao skuInfoDao;

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
}