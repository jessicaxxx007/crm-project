package com.yjx.service;

import com.yjx.base.BaseService;
import com.yjx.dao.CustomerServeMapper;
import com.yjx.dao.UserMapper;
import com.yjx.utils.AssertUtil;
import com.yjx.vo.CustomerServe;
import com.yjx.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServeService extends BaseService<CustomerServe, Integer> {

    @Resource
    private CustomerServeMapper customerServeMapper;

    @Resource
    private UserMapper userMapper;

    public void saveCustomerServe(CustomerServe serve) {
        AssertUtil.isTrue(StringUtils.isBlank(serve.getServeType()), "服务类型不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(serve.getCustomer()), "客户名称不能为空");
        serve.setIsValid(1);
        serve.setCreateDate(new Date());
        if (StringUtils.isBlank(serve.getState())) {
            serve.setState("fw_001");
        }
        AssertUtil.isTrue(insertSelective(serve) < 1, "创建服务失败");
    }

    public void updateCustomerServe(CustomerServe serve) {
        AssertUtil.isTrue(serve.getId() == null, "待更新记录不存在");
        // 服务分配：自动记录分配时间
        if ("fw_002".equals(serve.getState())) {
            serve.setAssignTime(new Date());
        }
        // 服务处理：自动记录处理时间和处理人
        if ("fw_003".equals(serve.getState())) {
            serve.setServiceProceTime(new Date());
            if (StringUtils.isBlank(serve.getServiceProcePeople())) {
                serve.setServiceProcePeople("admin");
            }
        }
        // 服务反馈→归档：反馈完成后自动归档
        if ("fw_004".equals(serve.getState()) && StringUtils.isNotBlank(serve.getServiceProceResult())) {
            serve.setState("fw_005");
        }
        AssertUtil.isTrue(updateByPrimaryKeySelective(serve) < 1, "更新服务失败");
    }

    public void deleteCustomerServe(Integer id) {
        AssertUtil.isTrue(id == null, "请选择待删除记录");
        AssertUtil.isTrue(deleteByPrimaryKey(id) < 1, "删除失败");
    }

    public List<Map<String, Object>> countByState() {
        return customerServeMapper.countByState();
    }

    /**
     * 获取所有有效用户列表（用于分配人下拉框）
     */
    public List<User> queryAllUsers() {
        return userMapper.queryAllUsers();
    }
}
