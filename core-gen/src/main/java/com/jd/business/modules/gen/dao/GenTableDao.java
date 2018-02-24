package com.jd.business.modules.gen.dao;

import com.jd.business.modules.gen.base.CrudDao;
import common.persistence.annotation.MyBatisDao;
import com.jd.business.modules.gen.entity.GenTable;

/**
 * 业务表DAO接口
 * @author horrific
 * @version 2017-10-15
 */
@MyBatisDao
public interface GenTableDao extends CrudDao<GenTable> {
	
}
