package com.jd.business.modules.gen.entity;

import com.jd.business.modules.gen.base.DataEntityCopy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 生成方案Entity
 *
 * @author horrific
 * @version 2013-10-15
 */

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class GenScheme extends DataEntityCopy<GenScheme> {

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
     * 生成包路径
     */
    private String packageName;

    /**
     * 生成模块名
     */
    private String moduleName;

    /**
     * 生成子模块名
     */
    private String subModuleName;

    /**
     * 生成功能名
     */
    private String functionName;

    /**
     * 生成功能名（简写）
     */
    private String functionNameSimple;

    /**
     * 生成功能作者
     */
    private String functionAuthor;

    /**
     * 业务表名
     */
    private GenTable genTable;

    /**
     * 0：保存方案； 1：保存方案并生成代码
     */
    private String flag;

    /**
     * 是否替换现有文件    0：不替换；1：替换文件
     */
    private Boolean replaceFile;

    public GenScheme() {
        super();
    }

}


