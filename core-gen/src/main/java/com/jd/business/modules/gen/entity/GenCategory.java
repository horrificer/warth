package com.jd.business.modules.gen.entity;


import com.jd.business.modules.gen.base.DictCopy;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 生成方案Entity
 * @author horrific
 * @version 2017-10-15
 */
@XmlRootElement(name="category")
public class GenCategory extends DictCopy {

	private static final long serialVersionUID = 1L;

	/**
	 * 主表模板
	 */
	private List<String> template;

	/**
	 * 子表模板
	 */
	private List<String> childTableTemplate;
	
	public static String CATEGORY_REF = "category-ref:";

	public GenCategory() {
		super();
	}

	@XmlElement(name = "template")
	public List<String> getTemplate() {
		return template;
	}

	public void setTemplate(List<String> template) {
		this.template = template;
	}
	
	@XmlElementWrapper(name = "childTable")
	@XmlElement(name = "template")
	public List<String> getChildTableTemplate() {
		return childTableTemplate;
	}

	public void setChildTableTemplate(List<String> childTableTemplate) {
		this.childTableTemplate = childTableTemplate;
	}
	
}


