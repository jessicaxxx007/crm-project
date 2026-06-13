package com.yjx.dao;

import com.yjx.base.BaseMapper;
import com.yjx.vo.CustomerServe;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerServeMapper extends BaseMapper<CustomerServe, Integer> {
    List<Map<String, Object>> countByState();
}
