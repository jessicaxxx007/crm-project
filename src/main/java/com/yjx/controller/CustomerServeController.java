package com.yjx.controller;

import com.yjx.base.BaseController;
import com.yjx.base.ResultInfo;
import com.yjx.query.CustomerServeQuery;
import com.yjx.service.CustomerServeService;
import com.yjx.vo.CustomerServe;
import com.yjx.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("customer_serve")
public class CustomerServeController extends BaseController {

    @Resource
    private CustomerServeService customerServeService;

    @RequestMapping("index/{state}")
    public String index(@PathVariable Integer state, Model model) {
        String[] states = {"fw_001", "fw_002", "fw_003", "fw_004", "fw_005"};
        model.addAttribute("state", states[state - 1]);
        model.addAttribute("stateNum", state);
        return "customerServe/customer_serve";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(CustomerServeQuery query) {
        return customerServeService.queryByParamsForTable(query);
    }

    @PostMapping("save")
    @ResponseBody
    public ResultInfo save(CustomerServe serve) {
        customerServeService.saveCustomerServe(serve);
        return success();
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(CustomerServe serve) {
        customerServeService.updateCustomerServe(serve);
        return success();
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer id) {
        customerServeService.deleteCustomerServe(id);
        return success();
    }

    @RequestMapping("addOrUpdateServePage")
    public String addOrUpdatePage(Integer id, Integer stateNum, Model model) {
        if (id != null) {
            model.addAttribute("serve", customerServeService.selectByPrimaryKey(id));
        }
        model.addAttribute("stateNum", stateNum);
        return "customerServe/add_update";
    }

    @RequestMapping("assignPage")
    public String assignPage(Integer id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("users", customerServeService.queryAllUsers());
        return "customerServe/assign";
    }

    @RequestMapping("queryAllUsers")
    @ResponseBody
    public List<User> queryAllUsers() {
        return customerServeService.queryAllUsers();
    }
}
