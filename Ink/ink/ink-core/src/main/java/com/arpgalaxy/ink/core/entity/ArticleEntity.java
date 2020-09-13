package com.arpgalaxy.ink.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文章
 * 
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@Data
@TableName("ink_article")
public class ArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 文章标题
	 */
	private String title;
	/**
	 * 文章描述
	 */
	private String description;
	/**
	 * 文章作者
	 */
	private String author;
	/**
	 * 文章内容
	 */
	private String content;
	/**
	 * html的content
	 */
	private String contentFormat;
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
	 * 文章展示类别,1:普通，2：大图片，3：无图片
	 */
	private Integer coverType;
	/**
	 * 封面
	 */
	private String cover;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否推荐文章
	 */
	private Integer recommend;
	/**
	 * 分类类别存在多级分类，用逗号隔开
	 */
	private String categoryId;
	/**
	 * 发布状态
	 */
	private Integer publish;
	/**
	 * 是否置顶
	 */
	private Integer top;

}
