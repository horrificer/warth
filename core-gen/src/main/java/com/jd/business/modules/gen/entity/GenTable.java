package com.jd.business.modules.gen.entity;

import java.util.List;

import com.jd.business.modules.gen.base.DataEntityCopy;
import lombok.*;

import com.google.common.collect.Lists;
import common.utils.StringUtils;

/**
 * 业务表Entity
 * @author horrific
 * @version 2017-10-15
 */
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class GenTable extends DataEntityCopy<GenTable> {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 描述
	 */
	private String comments;

	/**
	 * 实体类名称
	 */
	private String className;

	/**
	 * 关联父表
	 */
	private String parentTable;

	/**
	 * 关联父表外键
	 */
	private String parentTableFk;

	/**
	 * 表列
	 */
	private List<GenTableColumn> columnList = Lists.newArrayList();

	/**
	 * 按名称模糊查询
	 */
	private String nameLike;

	/**
	 * 当前表主键列表
	 */
	private List<String> pkList;

	/**
	 * 父表对象
	 */
	private GenTable parent;

	/**
	 * 子表列表
	 */
	private List<GenTable> childList = Lists.newArrayList();

	public GenTable() {
		super();
	}

	public GenTable(String id){
		super(id);
	}
	
	/**
	 * 获取列名和说明
	 * @return
	 */
	public String getNameAndComments() {
		return getName() + (comments == null ? "" : "  :  " + comments);
	}

	/**
	 * 获取导入依赖包字符串
	 * @return
	 */
	public List<String> getImportList(){
		// 引用列表
		List<String> importList = Lists.newArrayList();
		for (GenTableColumn column : getColumnList()){
			if (column.getIsNotBaseField() || ("1".equals(column.getIsQuery()) && "between".equals(column.getQueryType())
							&& ("createDate".equals(column.getSimpleJavaField()) || "updateDate".equals(column.getSimpleJavaField())))){
				// 导入类型依赖包， 如果类型中包含“.”，则需要导入引用。
				if (StringUtils.indexOf(column.getJavaType(), ".") != -1 && !importList.contains(column.getJavaType())){
					importList.add(column.getJavaType());
				}
			}
			if (column.getIsNotBaseField()){
				// 导入JSR303、Json等依赖包
				for (String ann : column.getAnnotationList()){
					if (!importList.contains(StringUtils.substringBeforeLast(ann, "("))){
						importList.add(StringUtils.substringBeforeLast(ann, "("));
					}
				}
			}
		}
		// 如果有子表，则需要导入List相关引用
		if (getChildList() != null && getChildList().size() > 0){
			if (!importList.contains("java.util.List")){
				importList.add("java.util.List");
			}
			if (!importList.contains("com.google.common.collect.Lists")){
				importList.add("com.google.common.collect.Lists");
			}
		}
		return importList;
	}
	
	/**
	 * 是否存在父类
	 * @return
	 */
	public Boolean getParentExists(){
		return parent != null && StringUtils.isNotBlank(parentTable) && StringUtils.isNotBlank(parentTableFk);
	}

	/**
	 * 是否存在create_date列
	 * @return
	 */
	public Boolean getCreateDateExists(){
		for (GenTableColumn c : columnList){
			if ("create_date".equals(c.getName())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 是否存在update_date列
	 * @return
	 */
	public Boolean getUpdateDateExists(){
		for (GenTableColumn c : columnList){
			if ("update_date".equals(c.getName())){
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否存在del_flag列
	 * @return
	 */
	public Boolean getDelFlagExists(){
		for (GenTableColumn c : columnList){
			if ("del_flag".equals(c.getName())){
				return true;
			}
		}
		return false;
	}
}


