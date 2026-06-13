package com.yjx.service;

import com.yjx.base.BaseService;
import com.yjx.dao.PermissionMapper;
import com.yjx.utils.AssertUtil;
import com.yjx.vo.Module;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ModuleService extends BaseService<Module, Integer> {

    @Resource
    private PermissionMapper permissionMapper;

    public void saveModule(Module module) {
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "菜单名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()), "权限码不能为空");
        module.setIsValid(1);
        AssertUtil.isTrue(insertSelective(module) < 1, "添加菜单失败");
    }

    public void updateModule(Module module) {
        AssertUtil.isTrue(module.getId() == null, "待更新记录不存在");
        AssertUtil.isTrue(StringUtils.isBlank(module.getModuleName()), "菜单名称不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(module.getOptValue()), "权限码不能为空");
        AssertUtil.isTrue(updateByPrimaryKeySelective(module) < 1, "更新菜单失败");
    }

    public void deleteModule(Integer mid) {
        AssertUtil.isTrue(mid == null, "请选择待删除记录");
        AssertUtil.isTrue(deleteByPrimaryKey(mid) < 1, "删除失败");
    }
}
