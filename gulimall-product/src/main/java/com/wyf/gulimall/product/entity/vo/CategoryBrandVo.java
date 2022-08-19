package com.wyf.gulimall.product.entity.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "根据brandId查找商品分类关系VO")
@Data
public class CategoryBrandVo {
    @ApiModelProperty(value = "商品Id", dataType = "Long", example = "21")
    private Long catelogId;

    @ApiModelProperty(value = "商品名字", dataType = "String", example = "手机")
    private String catelogName;
}
