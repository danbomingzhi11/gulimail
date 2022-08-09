package com.wyf.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * 
 * 
 * @author wyf
 * @email 
 * @date 2022-08-02 21:12:22
 */
@ApiModel(value = "商品三级分类表", description = "用于存储所有的分类")
@Data
@TableName("pms_category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
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
	@TableLogic
	@TableField(select = false)
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

}
