package com.yjx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class CustomerServe {
    private Integer id;
    private String serveType;
    private String overview;
    private String customer;
    private String state;
    private String serviceRequest;
    private String createPeople;
    private String assigner;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date assignTime;
    private String serviceProce;
    private String serviceProcePeople;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date serviceProceTime;
    private String serviceProceResult;
    private String myd;
    private Integer isValid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getServeType() { return serveType; }
    public void setServeType(String serveType) { this.serveType = serveType; }
    public String getOverview() { return overview; }
    public void setOverview(String overview) { this.overview = overview; }
    public String getCustomer() { return customer; }
    public void setCustomer(String customer) { this.customer = customer; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getServiceRequest() { return serviceRequest; }
    public void setServiceRequest(String serviceRequest) { this.serviceRequest = serviceRequest; }
    public String getCreatePeople() { return createPeople; }
    public void setCreatePeople(String createPeople) { this.createPeople = createPeople; }
    public String getAssigner() { return assigner; }
    public void setAssigner(String assigner) { this.assigner = assigner; }
    public Date getAssignTime() { return assignTime; }
    public void setAssignTime(Date assignTime) { this.assignTime = assignTime; }
    public String getServiceProce() { return serviceProce; }
    public void setServiceProce(String serviceProce) { this.serviceProce = serviceProce; }
    public String getServiceProcePeople() { return serviceProcePeople; }
    public void setServiceProcePeople(String serviceProcePeople) { this.serviceProcePeople = serviceProcePeople; }
    public Date getServiceProceTime() { return serviceProceTime; }
    public void setServiceProceTime(Date serviceProceTime) { this.serviceProceTime = serviceProceTime; }
    public String getServiceProceResult() { return serviceProceResult; }
    public void setServiceProceResult(String serviceProceResult) { this.serviceProceResult = serviceProceResult; }
    public String getMyd() { return myd; }
    public void setMyd(String myd) { this.myd = myd; }
    public Integer getIsValid() { return isValid; }
    public void setIsValid(Integer isValid) { this.isValid = isValid; }
    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }
    public Date getUpdateDate() { return updateDate; }
    public void setUpdateDate(Date updateDate) { this.updateDate = updateDate; }
}
