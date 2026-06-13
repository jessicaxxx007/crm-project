package com.yjx.dao;

import com.yjx.base.BaseMapper;
import com.yjx.vo.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer, Integer> {
    List<Map<String, Object>> countByLevel();
    List<Map<String, Object>> countByArea();
}
