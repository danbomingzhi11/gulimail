package com.wyf.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.wyf.gulimall.valid.AddGroup;
import com.wyf.gulimall.valid.ListValue;
import com.wyf.gulimall.valid.UpdateGroup;
import com.wyf.gulimall.valid.UpdateStatusGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * Ʒ
 *
 * @author wyf
 * @email
 * @date 2022-08-02 21:12:22
 */
@ApiModel(value = "商品品牌表", description = "用于存储所有的商品品牌表")
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @ApiModelProperty(value = "主键品牌ID 自增", dataType = "Long", example = "1")
    @NotNull(message = "修改必须指定品牌id", groups = {UpdateGroup.class})
    @Null(message = "新增不能指定id", groups = {AddGroup.class})
    @TableId
    private Long brandId;
    /**
     * 品牌名
     */
    @ApiModelProperty(value = "品牌名", dataType = "String", example = "小米10")
    @NotBlank(message = "品牌名必须提交", groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    /**
     * 品牌logo地址
     */
    @ApiModelProperty(value = "品牌logo 图片url", dataType = "String", example = "https://ossis.oss-cn-qingdao.aliyuncs.com/2022-08-08/0e6ef892-07cf-47cd-8335-5e0cb3aa0c40_1658886439043.jpg.jpg?Expires=1660010744&OSSAccessKeyId=TMP.3KhYGUpizVf6ht1wizveYjvkcVhLPfWeya2XEmX2L35KLFKxAHNcfhVgbKP8SLsnRZ3iGNg67d9K2wMSmFgMxTV11UWYom&Signature=1rCuzrV85yP%2BfkWryztAVRbLpFA%3D")
    @NotBlank(groups = {AddGroup.class})
    @URL(message = "logo必须是一个合法的url地址", groups = {AddGroup.class, UpdateGroup.class})
    private String logo;
    /**
     * 介绍
     */
    @ApiModelProperty(value = "品牌简介", dataType = "String", example = "好品牌，用小米")
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @ApiModelProperty(value = "显示状态（逻辑删除） 1 显示 0 不显示", dataType = "Integer", example = "1")
    @NotNull(groups = {AddGroup.class, UpdateStatusGroup.class})
    @ListValue(vals = {0, 1}, groups = {AddGroup.class, UpdateStatusGroup.class, UpdateGroup.class})
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @ApiModelProperty(value = "检索首字母", dataType = "String", example = "A")
    @NotEmpty(groups = {AddGroup.class})
    @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母", groups = {AddGroup.class, UpdateGroup.class})
    private String firstLetter;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序字段", dataType = "Integer", example = "0")
    @NotNull(groups = {AddGroup.class})
    @Min(value = 0, message = "排序必须大于等于0", groups = {AddGroup.class, UpdateGroup.class})
    private Integer sort;

}
