package com.yjx.dao;

import com.yjx.base.BaseMapper;
import com.yjx.vo.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission, Integer> {
    Integer countByModuleId(Integer moduleId);
    Integer countByRoleId(Integer roleId);
    List<Permission> selectByRoleId(Integer roleId);
    List<Map<String, Object>> queryModulesByRoleId(Integer roleId);
    Integer insertBatch(List<Permission> list);
}
