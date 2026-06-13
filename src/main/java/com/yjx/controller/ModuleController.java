package com.yjx.controller;

import com.yjx.base.BaseController;
import com.yjx.base.ResultInfo;
import com.yjx.service.ModuleService;
import com.yjx.service.RoleService;
import com.yjx.vo.Module;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {

    @Resource
    private ModuleService moduleService;
    @Resource
    private RoleService roleService;

    @RequestMapping("index")
    public String index() {
        return "module/module";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list() {
        return moduleService.queryByParamsForTable(null);
    }

    @PostMapping("save")
    @ResponseBody
    public ResultInfo save(Module module) {
        moduleService.saveModule(module);
        return success();
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(Module module) {
        moduleService.updateModule(module);
        return success();
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer mid) {
        moduleService.deleteModule(mid);
        return success();
    }

    @RequestMapping("addModulePage")
    public String addModulePage(Integer grade, Integer parentId, Model model) {
        model.addAttribute("grade", grade);
        model.addAttribute("parentId", parentId);
        return "module/add";
    }

    @RequestMapping("updateModulePage")
    public String updateModulePage(Integer id, Model model) {
        model.addAttribute("module", moduleService.selectByPrimaryKey(id));
        return "module/update";
    }

    @RequestMapping("queryAllModules")
    @ResponseBody
    public List<Map<String, Object>> queryAllModules(Integer roleId) {
        return roleService.queryAllModules(roleId);
    }
}
