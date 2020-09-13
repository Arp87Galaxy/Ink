package com.arpgalaxy.ink.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 图书表
 * 
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@Data
@TableName("ink_book")
public class BookEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 封面
	 */
	private String cover;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 分类类别
	 */
	private String categoryId;
	/**
	 * 是否推荐
	 */
	private Integer recommend;
	/**
	 * 出版社
	 */
	private String publisher;
	/**
	 * 出版日期
	 */
	private Date publishDate;
	/**
	 * 页数
	 */
	private Integer pageNum;
	/**
	 * 评分
	 */
	private Double grade;
	/**
	 * 简介
	 */
	private String description;
	/**
	 * 原书目录
	 */
	private String catalogue;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 阅读量
	 */
	private Integer readNum;
	/**
	 * 评论量
	 */
	private Integer commentNum;
	/**
	 * 点赞量
	 */
	private Integer likeNum;
	/**
	 * 是否发布
	 */
	private Integer publish;
	/**
	 * 读书状态
	 */
	private Integer progress;
	/**
	 * 是否阅读
	 */
	private Integer reading;

}
