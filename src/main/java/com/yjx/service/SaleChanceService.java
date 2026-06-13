package com.yjx.service;

import com.yjx.base.BaseService;
import com.yjx.dao.SaleChanceMapper;
import com.yjx.query.SaleChanceQuery;
import com.yjx.utils.AssertUtil;
import com.yjx.vo.SaleChance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SaleChanceService extends BaseService<SaleChance, Integer> {

    @Resource
    private SaleChanceMapper saleChanceMapper;

    public void saveSaleChance(SaleChance saleChance) {
        checkParams(saleChance);
        saleChance.setCreateDate(new Date());
        saleChance.setIsValid(1);
        saleChance.setState(0);
        saleChance.setDevResult(0);
        if (saleChance.getAssignMan() != null) {
            saleChance.setState(1);
        }
        AssertUtil.isTrue(insertSelective(saleChance) < 1, "添加营销机会失败");
    }

    public void updateSaleChance(SaleChance saleChance) {
        AssertUtil.isTrue(saleChance.getId() == null, "待更新记录不存在");
        checkParams(saleChance);
        AssertUtil.isTrue(updateByPrimaryKeySelective(saleChance) < 1, "更新营销机会失败");
    }

    private void checkParams(SaleChance saleChance) {
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getCustomerName()), "客户名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkMan()), "联系人不能为空");
    }

    public void deleteSaleChance(Integer[] ids) {
        AssertUtil.isTrue(ids == null || ids.length == 0, "请选择待删除记录");
        AssertUtil.isTrue(deleteBatch(ids) < 1, "删除失败");
    }

    public void updateDevResult(Integer id, Integer devResult) {
        AssertUtil.isTrue(id == null, "ID不能为空");
        SaleChance sc = new SaleChance();
        sc.setId(id);
        sc.setDevResult(devResult);
        AssertUtil.isTrue(updateByPrimaryKeySelective(sc) < 1, "更新开发状态失败");
    }

    public List<Map<String, Object>> queryAllSales() {
        return saleChanceMapper.queryAllSales();
    }
}
