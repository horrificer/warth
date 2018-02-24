package com.jd.business.modules.gen.entity;

import com.google.common.collect.Lists;
import com.jd.business.modules.gen.base.DataEntityCopy;
import common.utils.StringUtils;
import lombok.*;

import java.util.List;

/**
 * 业务表字段Entity
 * @author horrific
 * @version 2017-10-15
 */

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class GenTableColumn extends DataEntityCopy<GenTableColumn> {

	private static final long serialVersionUID = 1L;

	/**
	 * 归属表
	 */
	private GenTable genTable;

	/**
	 * 列名
	 */
	private String name;

	/**
	 * 描述
	 */
	private String comments;

	/**
	 * JDBC类型
	 */
	private String jdbcType;

	/**
	 * JAVA类型
	 */
	private String javaType;

	/**
	 * JAVA字段名
	 */
	private String javaField;

	/**
	 * 是否主键（1：主键）
	 */
	private String isPk;

	/**
	 * 是否可为空（1：可为空；0：不为空）
	 */
	private String isNull;

	/**
	 * 是否为插入字段（1：插入字段）
	 */
	private String isInsert;

	/**
	 * 是否编辑字段（1：编辑字段）
	 */
	private String isEdit;

	/**
	 * 是否列表字段（1：列表字段）
	 */
	private String isList;

	/**
	 * 是否查询字段（1：查询字段）
	 */
	private String isQuery;

	/**
	 * 查询方式（等于、不等于、大于、小于、范围、左LIKE、右LIKE、左右LIKE）
	 */
	private String queryType;

	/**
	 * 字段生成方案（文本框、文本域、下拉框、复选框、单选框、字典选择、人员选择、部门选择、区域选择）
	 */
	private String showType;

    /**
     * 字典类型
     */
	private String dictType;

    /**
     * 排序（升序）
     */
	private Integer sort;

    public GenTableColumn() {
        super();
    }

    public GenTableColumn(String id){
        super(id);
    }

    public GenTableColumn(GenTable genTable){
        this.genTable = genTable;
    }

	/**
	 * 获取列名和说明
	 * @return
	 */
	public String getNameAndComments() {
		return getName() + (comments == null ? "" : "  :  " + comments);
	}

	/**
	 * 获取字符串长度
	 * @return
	 */
	public String getDataLength(){
		String[] ss = StringUtils.split(StringUtils.substringBetween(getJdbcType(), "(", ")"), ",");
		if (ss != null && ss.length == 1){// && "String".equals(getJavaType())){
			return ss[0];
		}
		return "0";
	}

	/**
	 * 获取简写Java类型
	 * @return
	 */
	public String getSimpleJavaType(){
		if ("This".equals(getJavaType())){
			return StringUtils.capitalize(genTable.getClassName());
		}
		return StringUtils.indexOf(getJavaType(), ".") != -1
				? StringUtils.substringAfterLast(getJavaType(), ".")
						: getJavaType();
	}

	/**
	 * 获取简写Java字段
	 * @return
	 */
	public String getSimpleJavaField(){
		return StringUtils.substringBefore(getJavaField(), ".");
	}

	/**
	 * 获取Java字段，如果是对象，则获取对象.附加属性1
	 * @return
	 */
	public String getJavaFieldId(){
		return StringUtils.substringBefore(getJavaField(), "|");
	}

	/**
	 * 获取Java字段，如果是对象，则获取对象.附加属性2
	 * @return
	 */
	public String getJavaFieldName(){
		String[][] ss = getJavaFieldAttrs();
		return ss.length>0 ? getSimpleJavaField()+"."+ss[0][0] : "";
	}

	/**
	 * 获取Java字段，所有属性名
	 * @return
	 */
	public String[][] getJavaFieldAttrs(){
		String[] ss = StringUtils.split(StringUtils.substringAfter(getJavaField(), "|"), "|");
		String[][] sss = new String[ss.length][2];
		if (ss!=null){
			for (int i=0; i<ss.length; i++){
				sss[i][0] = ss[i];
				sss[i][1] = StringUtils.toUnderScoreCase(ss[i]);
			}
		}
		return sss;
	}

	/**
	 * 获取列注解列表
	 * @return
	 */
	public List<String> getAnnotationList(){
		List<String> list = Lists.newArrayList();
		// 导入Jackson注解
		if ("This".equals(getJavaType())){
			list.add("com.fasterxml.jackson.annotation.JsonBackReference");
		}
		if ("java.util.Date".equals(getJavaType())){
			list.add("com.fasterxml.jackson.annotation.JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
		}
		// 导入JSR303验证依赖包
		if (!"1".equals(getIsNull()) && !"String".equals(getJavaType())){
			list.add("javax.validation.constraints.NotNull(message=\""+getComments()+"不能为空\")");
		}
		else if (!"1".equals(getIsNull()) && "String".equals(getJavaType()) && !"0".equals(getDataLength())){
			list.add("org.hibernate.validator.constraints.Length(min=1, max="+getDataLength()
					+", message=\""+getComments()+"长度必须介于 1 和 "+getDataLength()+" 之间\")");
		}
		else if ("String".equals(getJavaType()) && !"0".equals(getDataLength())){
			list.add("org.hibernate.validator.constraints.Length(min=0, max="+getDataLength()
					+", message=\""+getComments()+"长度必须介于 0 和 "+getDataLength()+" 之间\")");
		}
		return list;
	}

	/**
	 * 获取简写列注解列表
	 * @return
	 */
	public List<String> getSimpleAnnotationList(){
		List<String> list = Lists.newArrayList();
		for (String ann : getAnnotationList()){
			list.add(StringUtils.substringAfterLast(ann, "."));
		}
		return list;
	}

	/**
	 * 是否是基类字段
	 * @return
	 */
	public Boolean getIsNotBaseField(){
		return !StringUtils.equals(getSimpleJavaField(), "id")
				&& !StringUtils.equals(getSimpleJavaField(), "remarks")
				&& !StringUtils.equals(getSimpleJavaField(), "createBy")
				&& !StringUtils.equals(getSimpleJavaField(), "createDate")
				&& !StringUtils.equals(getSimpleJavaField(), "updateBy")
				&& !StringUtils.equals(getSimpleJavaField(), "updateDate")
				&& !StringUtils.equals(getSimpleJavaField(), "delFlag");
	}

}


