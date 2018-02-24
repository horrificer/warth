package com.jd.business.modules.sys.service;

import com.jd.business.modules.sys.dao.DictDao;
import com.jd.business.modules.sys.utils.DictUtils;
import com.jd.business.common.base.service.CrudService;
import common.utils.CacheUtils;
import com.jd.business.modules.sys.entity.Dict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
public class DictService extends CrudService<DictDao, Dict> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Override
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Override
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

}
