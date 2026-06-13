package com.yjx.controller;

import com.yjx.base.BaseController;
import com.yjx.service.CustomerLossService;
import com.yjx.service.CustomerServeService;
import com.yjx.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("report")
public class ReportController extends BaseController {

    @Resource
    private CustomerService customerService;

    @Resource
    private CustomerServeService customerServeService;

    @Resource
    private CustomerLossService customerLossService;

    @RequestMapping("0")
    public String contribution() { return "report/customer_contribution"; }

    @RequestMapping("1")
    public String composition() { return "report/customer_composition"; }

    @RequestMapping("2")
    public String service() { return "report/customer_service"; }

    @RequestMapping("3")
    public String loss() { return "report/customer_loss"; }

    @RequestMapping("customerLevel")
    @ResponseBody
    public List<Map<String, Object>> customerLevel() {
        return customerService.countByLevel();
    }

    @RequestMapping("customerArea")
    @ResponseBody
    public List<Map<String, Object>> customerArea() {
        return customerService.countByArea();
    }

    @RequestMapping("customerServe")
    @ResponseBody
    public List<Map<String, Object>> customerServe() {
        return customerServeService.countByState();
    }

    @RequestMapping("customerLoss")
    @ResponseBody
    public List<Map<String, Object>> customerLoss() {
        return customerLossService.countByState();
    }
}
