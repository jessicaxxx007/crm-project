package com.yjx.dao;

import com.yjx.base.BaseMapper;
import com.yjx.vo.SaleChance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SaleChanceMapper extends BaseMapper<SaleChance, Integer> {
    List<Map<String, Object>> queryAllSales();
}
