package com.yjx.service;

import com.yjx.base.BaseService;
import com.yjx.dao.CustomerMapper;
import com.yjx.utils.AssertUtil;
import com.yjx.vo.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService extends BaseService<Customer, Integer> {

    @Resource
    private CustomerMapper customerMapper;

    public void saveCustomer(Customer customer) {
        AssertUtil.isTrue(StringUtils.isBlank(customer.getName()), "客户名称不能为空");
        customer.setIsValid(1);
        customer.setCreateDate(new Date());
        AssertUtil.isTrue(insertSelective(customer) < 1, "添加客户失败");
    }

    public void updateCustomer(Customer customer) {
        AssertUtil.isTrue(customer.getId() == null, "待更新记录不存在");
        AssertUtil.isTrue(StringUtils.isBlank(customer.getName()), "客户名称不能为空");
        AssertUtil.isTrue(updateByPrimaryKeySelective(customer) < 1, "更新客户失败");
    }

    public void deleteCustomer(Integer[] ids) {
        AssertUtil.isTrue(ids == null || ids.length == 0, "请选择待删除记录");
        AssertUtil.isTrue(deleteBatch(ids) < 1, "删除失败");
    }

    public List<Map<String, Object>> countByLevel() {
        return customerMapper.countByLevel();
    }

    public List<Map<String, Object>> countByArea() {
        return customerMapper.countByArea();
    }
}
