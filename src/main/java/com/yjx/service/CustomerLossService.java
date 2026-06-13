package com.yjx.service;

import com.yjx.base.BaseService;
import com.yjx.dao.CustomerLossMapper;
import com.yjx.utils.AssertUtil;
import com.yjx.vo.CustomerLoss;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CustomerLossService extends BaseService<CustomerLoss, Integer> {

    @Resource
    private CustomerLossMapper customerLossMapper;

    public void updateLossState(Integer id, String lossReason) {
        AssertUtil.isTrue(id == null, "ID不能为空");
        CustomerLoss loss = new CustomerLoss();
        loss.setId(id);
        loss.setState(1);
        loss.setLossReason(lossReason);
        loss.setConfirmLossTime(new java.util.Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(loss) < 1, "确认流失失败");
    }

    public List<Map<String, Object>> countByState() {
        return customerLossMapper.countByState();
    }
}
