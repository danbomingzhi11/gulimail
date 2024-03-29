package com.wyf.gulimall.ware.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.Query;

import com.wyf.gulimall.ware.dao.WareInfoDao;
import com.wyf.gulimall.ware.entity.WareInfoEntity;
import com.wyf.gulimall.ware.service.WareInfoService;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Autowired
    private WareInfoDao wareInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareInfoEntity> page = this.page(
                new Query<WareInfoEntity>().getPage(params),
                new QueryWrapper<WareInfoEntity>()
        );

        return new PageUtils(page);
    }

    // 获取所有的仓库列表数据


    @Override
    public PageUtils selectWareByMessage(Map<String, Object> params) {
        List<WareInfoEntity> wareInfoEntities = wareInfoDao.selectWareByMessage(params);
        int page = Integer.parseInt((String) params.get("page"));
        int pageSize = Integer.parseInt((String) params.get("limit"));

        PageUtils pageUtils = new PageUtils();
        pageUtils.setCurrPage(page);
        pageUtils.setPageSize(pageSize);
        pageUtils.setList(wareInfoEntities);
        return pageUtils;
    }
}