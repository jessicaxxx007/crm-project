package com.yjx.service;

import com.yjx.base.BaseService;
import com.yjx.utils.AssertUtil;
import com.yjx.vo.CusDevPlan;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CusDevPlanService extends BaseService<CusDevPlan, Integer> {

    public void saveCusDevPlan(CusDevPlan cusDevPlan) {
        checkParams(cusDevPlan);
        cusDevPlan.setIsValid(1);
        cusDevPlan.setCreateDate(new Date());
        AssertUtil.isTrue(insertSelective(cusDevPlan) < 1, "添加计划项失败");
    }

    public void updateCusDevPlan(CusDevPlan cusDevPlan) {
        AssertUtil.isTrue(cusDevPlan.getId() == null, "待更新记录不存在");
        checkParams(cusDevPlan);
        AssertUtil.isTrue(updateByPrimaryKeySelective(cusDevPlan) < 1, "更新计划项失败");
    }

    private void checkParams(CusDevPlan cusDevPlan) {
        AssertUtil.isTrue(StringUtils.isBlank(cusDevPlan.getPlanItem()), "计划项内容不能为空");
        AssertUtil.isTrue(cusDevPlan.getPlanDate() == null, "计划时间不能为空");
    }

    public void deleteCusDevPlan(Integer id) {
        AssertUtil.isTrue(id == null, "请选择待删除记录");
        AssertUtil.isTrue(deleteByPrimaryKey(id) < 1, "删除失败");
    }

    public CusDevPlan getById(Integer id) {
        return null;
    }

    public void saveOrUpdate(CusDevPlan cusDevPlan) {
    }
}
