package com.yjx.dao;

import com.yjx.base.BaseMapper;
import com.yjx.vo.Module;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ModuleMapper extends BaseMapper<Module, Integer> {
}
