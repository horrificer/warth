package com.jd.business.modules.gen.web;

import common.persistence.Page;
import common.utils.StringUtils;
import common.base.web.BaseController;
import com.jd.business.modules.gen.entity.GenScheme;
import com.jd.business.modules.gen.service.GenSchemeService;
import com.jd.business.modules.gen.service.GenTableService;
import com.jd.business.modules.gen.util.GenUtils;
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
 * 生成方案Controller
 *
 * @author horrific
 * @version 2017-12-12
 */
@Slf4j
@Controller
@RequestMapping(value = "${adminPath}/gen/genScheme")
public class GenSchemeController extends BaseController {

    @Autowired
    private GenSchemeService genSchemeService;

    @Autowired
    private GenTableService genTableService;

    @ModelAttribute
    public GenScheme get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return genSchemeService.get(id);
        } else {
            return new GenScheme();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String list(GenScheme genScheme, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<GenScheme> page = genSchemeService.find(new Page<GenScheme>(request, response), genScheme);
        model.addAttribute("page", page);

        return "modules/gen/genSchemeList";
    }

    @RequestMapping(value = "form")
    public String form(GenScheme genScheme, Model model) {
        if (StringUtils.isBlank(genScheme.getPackageName())) {
            genScheme.setPackageName("com.jd.business.modules");
        }
        model.addAttribute("genScheme", genScheme);
        model.addAttribute("config", GenUtils.getConfig());
        model.addAttribute("tableList", genTableService.findAll());
        return "modules/gen/genSchemeForm";
    }

    @RequestMapping(value = "save")
    public String save(GenScheme genScheme, Model model, RedirectAttributes redirectAttributes) {

        String result = genSchemeService.save(genScheme);
        addMessage(redirectAttributes, "操作生成方案'" + genScheme.getName() + "'成功<br/>" + result);
        return "redirect:" + adminPath + "/gen/genScheme/?repage";
    }

    @RequestMapping(value = "delete")
    public String delete(GenScheme genScheme, RedirectAttributes redirectAttributes) {
        genSchemeService.delete(genScheme);
        addMessage(redirectAttributes, "删除生成方案成功");
        return "redirect:" + adminPath + "/gen/genScheme/?repage";
    }

}
