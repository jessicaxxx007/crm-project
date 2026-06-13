package com.yjx.service;

import com.yjx.base.BaseService;
import com.yjx.dao.PermissionMapper;
import com.yjx.dao.RoleMapper;
import com.yjx.utils.AssertUtil;
import com.yjx.vo.Permission;
import com.yjx.vo.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleService extends BaseService<Role, Integer> {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    public void saveRole(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "角色名称不能为空");
        role.setIsValid(1);
        AssertUtil.isTrue(insertSelective(role) < 1, "添加角色失败");
    }

    public void updateRole(Role role) {
        AssertUtil.isTrue(role.getId() == null, "待更新记录不存在");
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "角色名称不能为空");
        AssertUtil.isTrue(updateByPrimaryKeySelective(role) < 1, "更新角色失败");
    }

    public void deleteRole(Integer id) {
        AssertUtil.isTrue(id == null, "请选择待删除记录");
        AssertUtil.isTrue(deleteByPrimaryKey(id) < 1, "删除失败");
    }

    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return roleMapper.queryAllRoles(userId);
    }

    @Transactional
    public void addGrant(Integer roleId, Integer[] mids) {
        List<Permission> oldPermissions = permissionMapper.selectByRoleId(roleId);
        if (oldPermissions != null && !oldPermissions.isEmpty()) {
            for (Permission p : oldPermissions) {
                permissionMapper.deleteByPrimaryKey(p.getId());
            }
        }
        if (mids != null && mids.length > 0) {
            List<Permission> list = new ArrayList<>();
            for (Integer mid : mids) {
                Permission p = new Permission();
                p.setRoleId(roleId);
                p.setModuleId(mid);
                list.add(p);
            }
            permissionMapper.insertBatch(list);
        }
    }

    public List<Map<String, Object>> queryAllModules(Integer roleId) {
        return permissionMapper.queryModulesByRoleId(roleId);
    }
}
