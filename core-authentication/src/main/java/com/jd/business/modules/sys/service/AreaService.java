package com.jd.business.modules.sys.service;

import com.jd.business.modules.sys.dao.AreaDao;
import com.jd.business.modules.sys.utils.UserUtils;
import com.jd.business.common.base.service.TreeService;
import com.jd.business.modules.sys.entity.Area;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Override
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}

	@Override
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
}
