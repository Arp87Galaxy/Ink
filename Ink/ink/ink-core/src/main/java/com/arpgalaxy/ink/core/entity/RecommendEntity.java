package com.arpgalaxy.ink.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 推荐
 * 
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@Data
@TableName("ink_recommend")
public class RecommendEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 推荐的文章Id
	 */
	private Integer linkId;
	/**
	 * 推荐类型
	 */
	private Integer type;
	/**
	 * 顺序
	 */
	private Integer orderNum;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 置顶
	 */
	private Integer top;

}
