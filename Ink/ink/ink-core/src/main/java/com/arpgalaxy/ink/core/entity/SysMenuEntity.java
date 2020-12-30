package com.arpgalaxy.ink.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 菜单管理
 * 
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@Data
@TableName("ink_sys_menu")
@Alias("SysMenuEntity")
public class SysMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long menuId;
	/**
	 * 
	 */
	private Long parentId;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String url;
	/**
	 * 
	 */
	private String perms;
	/**
	 * 
	 */
	private Integer type;
	/**
	 * 
	 */
	private String icon;
	/**
	 * 
	 */
	private Integer orderNum;
	/**
	 * 子菜单
	 */
	@TableField(exist = false)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<SysMenuEntity> children;
}
