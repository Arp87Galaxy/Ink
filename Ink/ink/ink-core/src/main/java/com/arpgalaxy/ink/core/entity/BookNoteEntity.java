package com.arpgalaxy.ink.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 笔记
 * 
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@Data
@TableName("ink_book_note")
public class BookNoteEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 笔记标题
	 */
	private String title;
	/**
	 * 笔记描述
	 */
	private String description;
	/**
	 * 笔记作者
	 */
	private String author;
	/**
	 * 笔记内容
	 */
	private String content;
	/**
	 * html的context
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
	 * 封面
	 */
	private String cover;
	/**
	 * 所属书本
	 */
	private Integer bookId;
	/**
	 * 所属章节
	 */
	private String chapter;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 是否推荐笔记
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
	 * 封面类型
	 */
	private Integer coverType;
	/**
	 * 是否置顶
	 */
	private Integer top;

}
