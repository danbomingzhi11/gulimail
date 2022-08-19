package com.wyf.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 属性分组
 * 
 * @author wyf
 * @email 
 * @date 2022-08-09 14:47:46
 */
@ApiModel(value = "商品分类与属性分组的关联表", description = "商品分类与属性分组的关联表")
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分组id
	 */
	@ApiModelProperty(value = "属性分组Id", dataType = "Long", example = "1")
	@TableId
	private Long attrGroupId;
	/**
	 * 组名
	 */
	@ApiModelProperty(value = "属性分组名字", dataType = "String", example = "规格")
	private String attrGroupName;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", dataType = "Integer", example = "1")
	private Integer sort;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述", dataType = "String", example = "一个分组")
	private String descript;
	/**
	 * 组图标
	 */
	@ApiModelProperty(value = "图表地址url", dataType = "String", example = "图标地址")
	private String icon;
	/**
	 * 所属分类id
	 */
	@ApiModelProperty(value = "与分类表关联外键", dataType = "Long", example = "https://ossis.oss-cn-qingdao.aliyuncs")
	private Long catelogId;

	@ApiModelProperty(value = "返回分类父子级树级关系", dataType = "List<Long> ", example = "")
	@TableField(exist = false)
	private List<Long> catelogPath;
}
