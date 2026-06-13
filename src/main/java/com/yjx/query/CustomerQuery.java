package com.yjx.query;

import com.yjx.base.BaseQuery;

public class CustomerQuery extends BaseQuery {
    private String name;
    private String khno;
    private String area;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getKhno() { return khno; }
    public void setKhno(String khno) { this.khno = khno; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
}
