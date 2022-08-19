package com.wyf.gulimall.product.service.impl;

import com.wyf.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.wyf.gulimall.product.dao.AttrDao;
import com.wyf.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.wyf.gulimall.product.entity.AttrEntity;
import com.wyf.gulimall.product.entity.bo.ArrGroupBo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.Query;

import com.wyf.gulimall.product.dao.AttrGroupDao;
import com.wyf.gulimall.product.entity.AttrGroupEntity;
import com.wyf.gulimall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    private AttrDao attrDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    // 根据catelogId 查询对应group表的数据 and 通过模糊查询or分组id的方式 查询对应的数据
    @Override
    public PageUtils queryGroupBySomething(Integer catelogId, Map<String, Object> params) {
        params.put("catelogId", Integer.toString(catelogId));

        if (params.get("key") != null && params.get("key") != "") {
            int su = Integer.parseInt((String) params.get("key"));
            params.put("keyInt", su);
        }

        int page = Integer.parseInt((String) params.get("page"));
        int pageSize = Integer.parseInt((String) params.get("limit"));

        List<AttrGroupEntity> attrGroupEntities = attrGroupDao.queryGroupBySomething(params);
        PageUtils pageUtils = new PageUtils();
        pageUtils.setList(attrGroupEntities);
        pageUtils.setCurrPage(page);
        pageUtils.setPageSize(pageSize);

        return pageUtils;
    }

    // 根据cateId查询对应的分组属性以及属性。
    @Override
    public List<ArrGroupBo> selectAllGroupAndArrByCateId(String cateId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("catelog_id", cateId);

        List<ArrGroupBo> arrGroupBos = new ArrayList<>();
        List<AttrGroupEntity> attrGroupEntities = attrGroupDao.selectList(queryWrapper);
        for (AttrGroupEntity arr: attrGroupEntities) {
            ArrGroupBo arrGroupBo = new ArrGroupBo();
            BeanUtils.copyProperties(arr, arrGroupBo);
            arrGroupBos.add(arrGroupBo);
        }
        for (ArrGroupBo arrGroupBo: arrGroupBos) {
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("attr_group_id", arrGroupBo.getAttrGroupId());
            List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntities = attrAttrgroupRelationDao.selectList(queryWrapper1);
            List<AttrEntity> attrEntities = new ArrayList<>();
            for (AttrAttrgroupRelationEntity attr: attrAttrgroupRelationEntities) {
                QueryWrapper queryWrapper2 = new QueryWrapper();
                queryWrapper2.eq("attr_id", attr.getAttrId());

                AttrEntity attrEntitie = attrDao.selectOne(queryWrapper2);

                attrEntities.add(attrEntitie);
            }
            arrGroupBo.setAttrs(attrEntities);
        }
        return arrGroupBos;
    }
}