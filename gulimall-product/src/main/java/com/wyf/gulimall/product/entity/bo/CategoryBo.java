package com.wyf.gulimall.product.entity.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.wyf.gulimall.product.entity.CategoryEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "三级分类BO")
@Data
public class CategoryBo {
    @ApiModelProperty(value = "主键分类ID 自增", dataType = "Long", example = "1")
    @TableId
    private Long catId;
    /**
     *
     */
    @ApiModelProperty(value = "分类名", dataType = "String", example = "王逸飞")
    private String name;
    /**
     *
     */
    @ApiModelProperty(value = "分类父级ID", dataType = "Long", example = "21")
    private Long parentCid;
    /**
     *
     */
    @ApiModelProperty(value = "分类层级1 or 2 or 3", dataType = "Integer", example = "1")
    private Integer catLevel;
    /**
     *
     */
    @ApiModelProperty(value = "分类是否显示:1 显示 2 不显示", dataType = "Integer", example = "1")
    private Integer showStatus;
    /**
     *
     */
    @ApiModelProperty(value = "分类排序", dataType = "Integer", example = "0")
    private Integer sort;
    /**
     * ͼ
     */
    @ApiModelProperty(value = "对应分类的图片地址", dataType = "String", example = "http://static.runoob.com/images/demo/demo2.jpg")
    private String icon;
    /**
     *
     */
    @ApiModelProperty(value = "计量单位", dataType = "String", example = "厘米")
    private String productUnit;
    /**
     *
     */
    @ApiModelProperty(value = "商品数量", dataType = "Integer", example = "1000")
    private Integer productCount;
    /**
     *
     */
    @ApiModelProperty(value = "子类数组 树形结构")
    private List<CategoryBo> children = new ArrayList<>();
}
