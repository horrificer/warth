package com.jd.business.modules.gen.web;

import common.persistence.Page;
import common.utils.StringUtils;
import common.base.web.BaseController;
import com.jd.business.modules.gen.entity.GenTable;
import com.jd.business.modules.gen.service.GenTableService;
import com.jd.business.modules.gen.util.GenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 业务表Controller
 *
 * @author horrific
 * @version 2017-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/gen/genTable")
public class GenTableController extends BaseController {

    @Autowired
    private GenTableService genTableService;

    @ModelAttribute
    public GenTable get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return genTableService.get(id);
        } else {
            return new GenTable();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String list(GenTable genTable, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GenTable> page = genTableService.find(new Page<GenTable>(request, response), genTable);
        model.addAttribute("page", page);
        return "modules/gen/genTableList";
    }

    @RequestMapping(value = "form")
    public String form(GenTable genTable, Model model) {
        // 获取物理表列表
        List<GenTable> tableList = genTableService.findTableListFormDb(new GenTable());
        model.addAttribute("tableList", tableList);
        // 验证表是否存在
        if (StringUtils.isBlank(genTable.getId()) && !genTableService.checkTableName(genTable.getName())) {
            addMessage(model, "下一步失败！" + genTable.getName() + " 表已经添加！");
            genTable.setName("");
        }
        // 获取物理表字段
        else {
            genTable = genTableService.getTableFormDb(genTable);
        }
        model.addAttribute("genTable", genTable);
        model.addAttribute("config", GenUtils.getConfig());
        return "modules/gen/genTableForm";
    }

    @RequestMapping(value = "save")
    public String save(GenTable genTable, Model model, RedirectAttributes redirectAttributes) {
        // 验证表是否已经存在
        if (StringUtils.isBlank(genTable.getId()) && !genTableService.checkTableName(genTable.getName())) {
            addMessage(model, "保存失败！" + genTable.getName() + " 表已经存在！");
            genTable.setName("");
            return form(genTable, model);
        }
        genTableService.save(genTable);
        addMessage(redirectAttributes, "保存业务表'" + genTable.getName() + "'成功");
        return "redirect:" + adminPath + "/gen/genTable/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(GenTable genTable, RedirectAttributes redirectAttributes) {
        genTableService.delete(genTable);
        addMessage(redirectAttributes, "删除业务表成功");
        return "redirect:" + adminPath + "/gen/genTable/?repage";
    }

}
