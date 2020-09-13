package com.arpgalaxy.ink.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 标签多对多维护表
 * 
 * @author arpgalaxy
 * @email 13605560342@163.com
 * @date 2020-09-05 14:47:37
 */
@Data
@TableName("ink_tag_link")
public class TagLinkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 标签Id
	 */
	private Integer tagId;
	/**
	 * 关联Id
	 */
	private Integer linkId;
	/**
	 * 所属类别：0文章，1阅读
	 */
	private Integer type;

}
