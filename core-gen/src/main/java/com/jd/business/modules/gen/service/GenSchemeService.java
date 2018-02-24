package com.jd.business.modules.gen.service;

import java.util.List;
import java.util.Map;

import com.jd.business.modules.gen.dao.GenSchemeDao;
import com.jd.business.modules.gen.dao.GenTableDao;
import com.jd.business.modules.gen.entity.GenConfig;
import com.jd.business.modules.gen.entity.GenScheme;
import com.jd.business.modules.gen.entity.GenTable;
import com.jd.business.modules.gen.entity.GenTableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.persistence.Page;
import common.utils.StringUtils;
import com.jd.business.modules.gen.dao.GenTableColumnDao;
import com.jd.business.modules.gen.entity.GenTemplate;
import com.jd.business.modules.gen.util.GenUtils;

/**
 * 生成方案Service
 * @author horrific
 * @version 2017-10-15
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class GenSchemeService {

	@Autowired
	private GenSchemeDao genSchemeDao;
	@Autowired
	private GenTableDao genTableDao;
	@Autowired
	private GenTableColumnDao genTableColumnDao;
	
	public GenScheme get(String id) {
		return genSchemeDao.get(id);
	}
	
	public Page<GenScheme> find(Page<GenScheme> page, GenScheme genScheme) {
		GenUtils.getTemplatePath();
		genScheme.setPage(page);
		page.setList(genSchemeDao.findList(genScheme));
		return page;
	}
	
	@Transactional(readOnly = false)
	public String save(GenScheme genScheme) {
		if (StringUtils.isBlank(genScheme.getId())){
			genScheme.preInsert();
			genSchemeDao.insert(genScheme);
		}else{
			genScheme.preUpdate();
			genSchemeDao.update(genScheme);
		}
		// 生成代码
		if ("1".equals(genScheme.getFlag())){
			return generateCode(genScheme);
		}
		return "";
	}
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(GenScheme genScheme) {
		genSchemeDao.delete(genScheme);
	}
	
	private String generateCode(GenScheme genScheme){

		StringBuilder result = new StringBuilder();
		
		// 查询主表及字段列
		GenTable genTable = genTableDao.get(genScheme.getGenTable().getId());
		genTable.setColumnList(genTableColumnDao.findList(new GenTableColumn(new GenTable(genTable.getId()))));
		
		// 获取所有代码模板
		GenConfig config = GenUtils.getConfig();
		
		// 获取模板列表
		List<GenTemplate> templateList = GenUtils.getTemplateList(config, genScheme.getCategory(), false);

		// 生成主表模板代码
		genScheme.setGenTable(genTable);
		Map<String, Object> model = GenUtils.getDataModel(genScheme);
		for (GenTemplate tpl : templateList){
			result.append(GenUtils.generateToFile(tpl, model, genScheme.getReplaceFile()));
		}
		return result.toString();
	}
}
