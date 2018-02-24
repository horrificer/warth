package com.jd.business.modules.gen.dao;

import java.util.List;

import com.jd.business.modules.gen.base.CrudDao;
import common.persistence.annotation.MyBatisDao;
import com.jd.business.modules.gen.entity.GenTable;
import com.jd.business.modules.gen.entity.GenTableColumn;

/**
 * 业务表字段DAO接口
 * @author horrific
 * @version 2017-10-15
 */
@MyBatisDao
public interface GenDataBaseDictDao extends CrudDao<GenTableColumn> {

	/**
	 * 查询表列表
	 * @param genTable
	 * @return
	 */
	List<GenTable> findTableList(GenTable genTable);

	/**
	 * 获取数据表字段
	 * @param genTable
	 * @return
	 */
	List<GenTableColumn> findTableColumnList(GenTable genTable);
	
	/**
	 * 获取数据表主键
	 * @param genTable
	 * @return
	 */
	List<String> findTablePK(GenTable genTable);
	
}
