package com.yjx.service;

import com.yjx.base.BaseService;
import com.yjx.dao.DatadicMapper;
import com.yjx.utils.AssertUtil;
import com.yjx.vo.Datadic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class DatadicService extends BaseService<Datadic, Integer> {

    @Resource
    private DatadicMapper datadicMapper;

    public void saveDatadic(Datadic datadic) {
        AssertUtil.isTrue(StringUtils.isBlank(datadic.getDataDicName()), "字典名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(datadic.getDataDicValue()), "字典值不能为空");
        datadic.setIsValid(1);
        AssertUtil.isTrue(insertSelective(datadic) < 1, "添加字典失败");
    }

    public void updateDatadic(Datadic datadic) {
        AssertUtil.isTrue(datadic.getId() == null, "待更新记录不存在");
        AssertUtil.isTrue(StringUtils.isBlank(datadic.getDataDicName()), "字典名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(datadic.getDataDicValue()), "字典值不能为空");
        AssertUtil.isTrue(updateByPrimaryKeySelective(datadic) < 1, "更新字典失败");
    }

    public void deleteDatadic(Integer id) {
        AssertUtil.isTrue(id == null, "请选择待删除记录");
        AssertUtil.isTrue(deleteByPrimaryKey(id) < 1, "删除失败");
    }

    public List<Map<String, Object>> queryAllDicNames() {
        return datadicMapper.queryAllDicNames();
    }
}
