package com.arpgalaxy.ink.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * 
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@Data
@TableName("ink_sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long userId;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String salt;
	/**
	 * 
	 */
	private String createUserId;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Integer status;
	/**
	 *
	 */
	@TableField(exist = false)
	private List<SysRoleEntity> roleEntities;
}
