package com.wyf.gulimall.product.entity.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.wyf.gulimall.product.entity.AttrEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArrGroupBo {
    /**
     * 分组id
     */
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catelogId;

    @TableField(exist = false)
    private List<AttrEntity> attrs = new ArrayList<>();

}
