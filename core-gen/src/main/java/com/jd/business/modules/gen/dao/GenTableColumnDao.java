package com.jd.business.modules.gen.dao;

import com.jd.business.modules.gen.base.CrudDao;
import common.persistence.annotation.MyBatisDao;
import com.jd.business.modules.gen.entity.GenTableColumn;

/**
 * 业务表字段DAO接口
 * @author horrific
 * @version 2017-10-15
 */
@MyBatisDao
public interface GenTableColumnDao extends CrudDao<GenTableColumn> {

	/**
	 * 通过tableId删除数据
	 * @param genTableId
	 */
	void deleteByGenTableId(String genTableId);
}
