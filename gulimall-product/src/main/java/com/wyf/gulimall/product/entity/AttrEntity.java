package com.wyf.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

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
	@TableId
	private Long attrId;
	/**
	 * 
	 */
	private String attrName;
	/**
	 * 
	 */
	private Integer searchType;
	/**
	 * 
	 */
	private String icon;
	/**
	 * 
	 */
	private String valueSelect;
	/**
	 * 
	 */
	private Integer attrType;
	/**
	 * 
	 */
	private Long enable;
	/**
	 * 
	 */
	private Long catelogId;
	/**
	 * 
	 */
	private Integer showDesc;

}
