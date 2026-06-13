package com.yjx.dao;

import com.yjx.base.BaseMapper;
import com.yjx.vo.CustomerLoss;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerLossMapper extends BaseMapper<CustomerLoss, Integer> {
    List<Map<String, Object>> countByState();
}
