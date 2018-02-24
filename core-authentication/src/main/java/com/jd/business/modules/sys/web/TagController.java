package com.jd.business.modules.sys.web;

import common.base.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 标签Controller
 * @author ThinkGem
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/tag")
public class TagController extends BaseController {
	
	/**
	 * 树结构选择标签（treeselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "treeselect")
	public String treeselect(HttpServletRequest request, Model model) {
		// 树结构数据URL
		model.addAttribute("url", request.getParameter("url"));
		// 排除的编号ID
		model.addAttribute("extId", request.getParameter("extId"));
		// 是否可复选
		model.addAttribute("checked", request.getParameter("checked"));
		// 指定默认选中的ID
		model.addAttribute("selectIds", request.getParameter("selectIds"));
		// 是否读取全部数据，不进行权限过滤
		model.addAttribute("isAll", request.getParameter("isAll"));
		// 过滤栏目模型（仅针对CMS的Category树）
		model.addAttribute("module", request.getParameter("module"));
		return "modules/sys/tagTreeselect";
	}
	
	/**
	 * 图标选择标签（iconselect.tag）
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "iconselect")
	public String iconselect(HttpServletRequest request, Model model) {
		model.addAttribute("value", request.getParameter("value"));
		return "modules/sys/tagIconselect";
	}
	
}
