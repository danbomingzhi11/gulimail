package com.wyf.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 属性&属性分组关联
 * 
 * @author wyf
 * @email 
 * @date 2022-08-09 14:47:46
 */
@Api(value = "商品属性与属性组的关联", tags = {"商品属性与属性组的关联"})
@Data
@TableName("pms_attr_attrgroup_relation")
public class AttrAttrgroupRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(value = "ID", dataType = "Long", example = "1")
	@TableId
	private Long id;
	/**
	 * 属性id
	 */
	@ApiModelProperty(value = "属性id", dataType = "Long", example = "1")
	private Long attrId;
	/**
	 * 属性分组id
	 */
	@ApiModelProperty(value = "属性分组id", dataType = "Long", example = "1")
	private Long attrGroupId;
	/**
	 * 属性组内排序
	 */
	@ApiModelProperty(value = "属性组内排序", dataType = "Integer", example = "1")
	private Integer attrSort;

}
