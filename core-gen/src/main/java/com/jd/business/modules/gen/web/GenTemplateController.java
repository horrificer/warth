package com.jd.business.modules.gen.web;

import common.persistence.Page;
import common.utils.StringUtils;
import common.base.web.BaseController;
import com.jd.business.modules.gen.entity.GenTemplate;
import com.jd.business.modules.gen.service.GenTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 代码模板Controller
 *
 * @author horrific
 * @version 2017-10-15
 */
@Slf4j
@Controller
@RequestMapping(value = "${adminPath}/gen/genTemplate")
public class GenTemplateController extends BaseController {

    @Autowired
    private GenTemplateService genTemplateService;

    @ModelAttribute
    public GenTemplate get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return genTemplateService.get(id);
        } else {
            return new GenTemplate();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String list(GenTemplate genTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<GenTemplate> page = genTemplateService.find(new Page<GenTemplate>(request, response), genTemplate);
        model.addAttribute("page", page);
        return "modules/gen/genTemplateList";
    }

    @RequestMapping(value = "form")
    public String form(GenTemplate genTemplate, Model model) {
        model.addAttribute("genTemplate", genTemplate);
        return "modules/gen/genTemplateForm";
    }

    @RequestMapping(value = "save")
    public String save(GenTemplate genTemplate, Model model, RedirectAttributes redirectAttributes) {
        genTemplateService.save(genTemplate);
        addMessage(redirectAttributes, "保存代码模板'" + genTemplate.getName() + "'成功");
        return "redirect:" + adminPath + "/gen/genTemplate/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(GenTemplate genTemplate, RedirectAttributes redirectAttributes) {
        genTemplateService.delete(genTemplate);
        addMessage(redirectAttributes, "删除代码模板成功");
        return "redirect:" + adminPath + "/gen/genTemplate/?repage";
    }

}
