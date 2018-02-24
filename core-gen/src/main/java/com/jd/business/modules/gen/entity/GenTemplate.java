package com.jd.business.modules.gen.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.jd.business.modules.gen.base.DataEntityCopy;
import lombok.*;

import com.google.common.collect.Lists;
import common.utils.StringUtils;

/**
 * 生成方案Entity
 * @author horrific
 * @version 2017-10-15
 */

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
@XmlRootElement(name="template")
public class GenTemplate extends DataEntityCopy<GenTemplate> {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 分类
	 */
	private String category;

	/**
	 * 生成文件路径
	 */
	private String filePath;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 内容
	 */
	private String content;

	public GenTemplate() {
		super();
	}
	
	@XmlTransient
	public List<String> getCategoryList() {
		if (category == null){
			return Lists.newArrayList();
		}else{
			return Lists.newArrayList(StringUtils.split(category, ","));
		}
	}

	public void setCategoryList(List<String> categoryList) {
		if (categoryList == null){
			this.category = "";
		}else{
			this.category = ","+StringUtils.join(categoryList, ",") + ",";
		}
	}
	
}


