package com.yjx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Datadic {
    private Integer id;
    private String dataDicName;
    private String dataDicValue;
    private Integer isValid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDataDicName() { return dataDicName; }
    public void setDataDicName(String dataDicName) { this.dataDicName = dataDicName; }
    public String getDataDicValue() { return dataDicValue; }
    public void setDataDicValue(String dataDicValue) { this.dataDicValue = dataDicValue; }
    public Integer getIsValid() { return isValid; }
    public void setIsValid(Integer isValid) { this.isValid = isValid; }
    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }
    public Date getUpdateDate() { return updateDate; }
    public void setUpdateDate(Date updateDate) { this.updateDate = updateDate; }
}
