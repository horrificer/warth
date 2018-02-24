package com.jd.business.modules.sys.dao;


import com.jd.business.common.base.dao.CrudDao;
import common.persistence.annotation.MyBatisDao;
import com.jd.business.modules.sys.entity.Dict;

import java.util.List;

/**
 * 字典DAO接口
 * @author horrific
 * @version 2017-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	List<String> findTypeList(Dict dict);
	
}
