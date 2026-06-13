package com.yjx.controller;

import com.yjx.base.BaseController;
import com.yjx.base.ResultInfo;
import com.yjx.query.SaleChanceQuery;
import com.yjx.service.SaleChanceService;
import com.yjx.vo.SaleChance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("sale_chance")
public class SaleChanceController extends BaseController {

    @Resource
    private SaleChanceService saleChanceService;

    @RequestMapping("index")
    public String index() {
        return "saleChance/sale_chance";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(SaleChanceQuery query) {
        return saleChanceService.queryByParamsForTable(query);
    }

    @PostMapping("save")
    @ResponseBody
    public ResultInfo save(SaleChance saleChance) {
        saleChanceService.saveSaleChance(saleChance);
        return success();
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(SaleChance saleChance) {
        saleChanceService.updateSaleChance(saleChance);
        return success();
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids) {
        saleChanceService.deleteSaleChance(ids);
        return success();
    }

    @RequestMapping("addOrUpdateSaleChancePage")
    public String addOrUpdatePage(Integer id, Model model) {
        if (id != null) {
            model.addAttribute("saleChance", saleChanceService.selectByPrimaryKey(id));
        }
        return "saleChance/add_update";
    }

    @PostMapping("updateSaleChanceDevResult")
    @ResponseBody
    public ResultInfo updateDevResult(Integer id, Integer devResult) {
        saleChanceService.updateDevResult(id, devResult);
        return success();
    }
}
