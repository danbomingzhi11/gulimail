package com.wyf.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * 
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
@Data
@TableName("pms_attr")
public class AttrEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@ApiModelProperty(value = "属性id", dataType = "Long", example = "1")
	@TableId
	private Long attrId;
	/**
	 * 
	 */
	@ApiModelProperty(value = "属性名", dataType = "String", example = "小米 8GB")
	private String attrName;
	/**
	 * 
	 */
	@ApiModelProperty(value = "是否需要检索[0-不需要，1-需要]", dataType = "Integer", example = "1")
	private Integer searchType;
	/**
	 * 
	 */
	@ApiModelProperty(value = "属性图标", dataType = "String", example = "图片地址")
	private String icon;
	/**
	 * 
	 */
	@ApiModelProperty(value = "可选值列表[用逗号分隔]", dataType = "String", example = "1")
	private String valueSelect;
	/**
	 * 
	 */
	@ApiModelProperty(value = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]", dataType = "Integer", example = "1")
	private Integer attrType;
	/**
	 * 
	 */
	@ApiModelProperty(value = "启用状态[0 - 禁用，1 - 启用]", dataType = "Long", example = "1")
	private Long enable;
	/**
	 * 
	 */
	@ApiModelProperty(value = "所属分类", dataType = "Long", example = "1")
	private Long catelogId;
	/**
	 * 
	 */
	@ApiModelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整", dataType = "Integer", example = "1")
	private Integer showDesc;

	@TableField(exist = false)
	private Long attrGroupId;
}
