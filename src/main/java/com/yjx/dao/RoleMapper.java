package com.yjx.dao;

import com.yjx.base.BaseMapper;
import com.yjx.vo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper extends BaseMapper<Role, Integer> {
    List<Map<String, Object>> queryAllRoles(Integer userId);
    String queryRoleNamesByRoleIds(String roleIds);
}
