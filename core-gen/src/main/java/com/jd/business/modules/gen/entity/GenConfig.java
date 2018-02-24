package com.jd.business.modules.gen.entity;


import com.jd.business.modules.gen.base.DictCopy;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 生成方案Entity
 * @author horrific
 * @version 2013-10-15
 */
@XmlRootElement(name="config")
public class GenConfig implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 代码模板分类
	 */
	private List<GenCategory> categoryList;

	/**
	 * Java类型
	 */
	private List<DictCopy> javaTypeList;

	/**
	 * 查询类型
	 */
	private List<DictCopy> queryTypeList;

	/**
	 * 显示类型
	 */
	private List<DictCopy> showTypeList;

	public GenConfig() {
		super();
	}

	@XmlElementWrapper(name = "category")
	@XmlElement(name = "category")
	public List<GenCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<GenCategory> categoryList) {
		this.categoryList = categoryList;
	}

	@XmlElementWrapper(name = "javaType")
	@XmlElement(name = "dict")
	public List<DictCopy> getJavaTypeList() {
		return javaTypeList;
	}

	public void setJavaTypeList(List<DictCopy> javaTypeList) {
		this.javaTypeList = javaTypeList;
	}

	@XmlElementWrapper(name = "queryType")
	@XmlElement(name = "dict")
	public List<DictCopy> getQueryTypeList() {
		return queryTypeList;
	}

	public void setQueryTypeList(List<DictCopy> queryTypeList) {
		this.queryTypeList = queryTypeList;
	}

	@XmlElementWrapper(name = "showType")
	@XmlElement(name = "dict")
	public List<DictCopy> getShowTypeList() {
		return showTypeList;
	}

	public void setShowTypeList(List<DictCopy> showTypeList) {
		this.showTypeList = showTypeList;
	}
	
}