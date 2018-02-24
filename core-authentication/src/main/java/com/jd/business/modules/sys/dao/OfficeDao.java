package com.jd.business.modules.sys.dao;

import com.jd.business.common.base.dao.TreeDao;
import common.persistence.annotation.MyBatisDao;
import com.jd.business.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author horrific
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
