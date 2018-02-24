package com.jd.business.modules.sys.dao;


import com.jd.business.common.base.dao.TreeDao;
import common.persistence.annotation.MyBatisDao;
import com.jd.business.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}
