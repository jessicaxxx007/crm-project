package com.yjx.controller;

import com.yjx.base.BaseController;
import com.yjx.base.ResultInfo;
import com.yjx.query.DatadicQuery;
import com.yjx.service.DatadicService;
import com.yjx.vo.Datadic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("data_dic")
public class DatadicController extends BaseController {

    @Resource
    private DatadicService datadicService;

    @RequestMapping("index")
    public String index() { return "dataDic/data_dic"; }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(DatadicQuery query) {
        return datadicService.queryByParamsForTable(query);
    }

    @PostMapping("save")
    @ResponseBody
    public ResultInfo save(Datadic datadic) {
        datadicService.saveDatadic(datadic);
        return success();
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(Datadic datadic) {
        datadicService.updateDatadic(datadic);
        return success();
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer id) {
        datadicService.deleteDatadic(id);
        return success();
    }

    @RequestMapping("addOrUpdateDicPage")
    public String addOrUpdatePage(Integer id, Model model) {
        if (id != null) {
            model.addAttribute("datadic", datadicService.selectByPrimaryKey(id));
        }
        return "dataDic/add_update";
    }
}
