package com.yjx.controller;

import com.yjx.base.BaseController;
import com.yjx.base.ResultInfo;
import com.yjx.query.CustomerQuery;
import com.yjx.service.CustomerService;
import com.yjx.vo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Resource
    private CustomerService customerService;

    @RequestMapping("index")
    public String index() { return "customer/customer"; }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(CustomerQuery query) {
        return customerService.queryByParamsForTable(query);
    }

    @PostMapping("save")
    @ResponseBody
    public ResultInfo save(Customer customer) {
        customerService.saveCustomer(customer);
        return success();
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(Customer customer) {
        customerService.updateCustomer(customer);
        return success();
    }

    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids) {
        customerService.deleteCustomer(ids);
        return success();
    }

    @RequestMapping("addOrUpdateCustomerPage")
    public String addOrUpdatePage(Integer id, Model model) {
        if (id != null) {
            model.addAttribute("customer", customerService.selectByPrimaryKey(id));
        }
        return "customer/add_update";
    }
}
