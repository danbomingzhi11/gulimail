package com.wyf.gulimall.product.service.impl;

import com.mysql.cj.util.StringUtils;
import com.wyf.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.wyf.gulimall.product.dao.AttrGroupDao;
import com.wyf.gulimall.product.dao.CategoryDao;
import com.wyf.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.wyf.gulimall.product.entity.AttrGroupEntity;
import com.wyf.gulimall.product.entity.CategoryEntity;
import com.wyf.gulimall.product.entity.vo.AttrRespVo;
import com.wyf.gulimall.utils.PageUtils;
import com.wyf.gulimall.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wyf.gulimall.product.dao.AttrDao;
import com.wyf.gulimall.product.entity.AttrEntity;
import com.wyf.gulimall.product.service.AttrService;

import javax.xml.ws.Action;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrDao attrDao;

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    // 新增属性，并且新增属性与属性组关联
    @Override
    public void saveAttrAndGroup(AttrEntity attrEntity) {
        attrDao.insert(attrEntity);

        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
        attrAttrgroupRelationEntity.setAttrGroupId(attrEntity.getAttrGroupId());
        attrAttrgroupRelationEntity.setAttrSort(0);

        attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
    }

    // 获取分类销售属性
    @Override
    public PageUtils selectAllAttrByCatelogId(Long catelogId, Map<String, Object> params) {
        // 存入key的 int形式 为了方便通过 id 查找
        if (params.get("key") != null && params.get("key") != "") {
            int su = Integer.parseInt((String) params.get("key"));
            params.put("keyInt", su);
        }
        List<AttrRespVo> attrRespVos = new ArrayList<>();
        List<AttrEntity> attrEntities = attrDao.selectAllAttrByCatelogId(catelogId, params);

        for (AttrEntity attrEntity: attrEntities) {

            AttrRespVo core = new AttrRespVo();
            // 属性互换
            BeanUtils.copyProperties(attrEntity, core);

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("attr_id", attrEntity.getAttrId());
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = attrAttrgroupRelationDao.selectOne(queryWrapper);
            if (attrAttrgroupRelationEntity == null ) {
                break;
            }
            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelationEntity.getAttrGroupId());

            // 注入group_name
            core.setGroupName(attrGroupEntity.getAttrGroupName());

            // 拼接字符串
            StringBuffer sb = new StringBuffer();

            CategoryEntity categoryEntity = categoryDao.selectById(catelogId);

            sb = findAllFather(categoryEntity.getParentCid(), sb);

            // 将三级分类加入 并转为字符串
            String keywordStr = sb.append(categoryEntity.getName()).toString();

            // catelog_name 注入
            core.setCatelogName(keywordStr);

            // 最后一步 将封装好的数据 放入外围的 VO数组
            attrRespVos.add(core);
        }

        // 封装入 PageUtils
        PageUtils pageUtils = new PageUtils();
        int page = Integer.parseInt((String) params.get("page"));
        int pageSize = Integer.parseInt((String) params.get("limit"));
        pageUtils.setCurrPage(page);
        pageUtils.setPageSize(pageSize);
        pageUtils.setList(attrRespVos);

        return  pageUtils;
    }
    // 递归查询
    private StringBuffer findAllFather(Long cateId, StringBuffer sb) {
        CategoryEntity categoryEntity = categoryDao.selectById(cateId);
        if (categoryEntity.getParentCid() != 0) {
            findAllFather(categoryEntity.getParentCid(), sb);
        }
        return sb.append(categoryEntity.getName()).append("/");
    }

    @Override
    public List<Long> selectSearchAttrIds(List<Long> attrIds) {
        return baseMapper.selectSearchAttrIds(attrIds);
    }
}
