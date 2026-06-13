package com.yjx.controller;

import com.yjx.base.BaseController;
import com.yjx.base.ResultInfo;
import com.yjx.query.CusDevPlanQuery;
import com.yjx.service.CusDevPlanService;
import com.yjx.service.SaleChanceService;
import com.yjx.vo.CusDevPlan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("cus_dev_plan")
public class CusDevPlanController extends BaseController {

    @Resource
    private CusDevPlanService cusDevPlanService;
    @Resource
    private SaleChanceService saleChanceService;

    @RequestMapping("index")
    public String index() {
        return "cusDevPlan/cus_dev_plan";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(CusDevPlanQuery query) {
        return cusDevPlanService.queryByParamsForTable(query);
    }

    @PostMapping("save")
    @ResponseBody
    public ResultInfo save(CusDevPlan cusDevPlan) {
        cusDevPlanService.saveCusDevPlan(cusDevPlan);
        return success();
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(CusDevPlan cusDevPlan) {
        cusDevPlanService.updateCusDevPlan(cusDevPlan);
        return success();
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer id) {
        cusDevPlanService.deleteCusDevPlan(id);
        return success();
    }

    @RequestMapping("toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Integer sid, Model model) {
        model.addAttribute("saleChance", saleChanceService.selectByPrimaryKey(sid));
        return "cusDevPlan/cus_dev_plan_data";
    }

    @RequestMapping("addOrUpdateCusDevPlanPage")
    public String addOrUpdatePage(Integer sid, Integer id, Model model) {
        model.addAttribute("sid", sid);
        if (id != null) {
            model.addAttribute("cusDevPlan", cusDevPlanService.selectByPrimaryKey(id));
        }
        return "cusDevPlan/add_update";
    }
}