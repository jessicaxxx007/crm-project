package com.yjx.controller;

import com.yjx.base.BaseController;
import com.yjx.base.BaseQuery;
import com.yjx.base.ResultInfo;
import com.yjx.service.CustomerLossService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("customer_loss")
public class CustomerLossController extends BaseController {

    @Resource
    private CustomerLossService customerLossService;

    @RequestMapping("index")
    public String index() { return "customerLoss/customer_loss"; }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(BaseQuery query) {
        return customerLossService.queryByParamsForTable(query);
    }

    @PostMapping("confirmLoss")
    @ResponseBody
    public ResultInfo confirmLoss(Integer id, String lossReason) {
        customerLossService.updateLossState(id, lossReason);
        return success();
    }
}
