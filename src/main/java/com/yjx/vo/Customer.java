package com.yjx.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Customer {
    private Integer id;
    private String khno;
    private String name;
    private String area;
    private String cusManager;
    private String level;
    private String myd;
    private String xyd;
    private String address;
    private String postCode;
    private String phone;
    private String fax;
    private String webSite;
    private String yyzzzch;
    private String fr;
    private String zczj;
    private String nyye;
    private String khyh;
    private String khzh;
    private String dsdjh;
    private String gsdjh;
    private Integer state;
    private Integer isValid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getKhno() { return khno; }
    public void setKhno(String khno) { this.khno = khno; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public String getCusManager() { return cusManager; }
    public void setCusManager(String cusManager) { this.cusManager = cusManager; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public String getMyd() { return myd; }
    public void setMyd(String myd) { this.myd = myd; }
    public String getXyd() { return xyd; }
    public void setXyd(String xyd) { this.xyd = xyd; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPostCode() { return postCode; }
    public void setPostCode(String postCode) { this.postCode = postCode; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax; }
    public String getWebSite() { return webSite; }
    public void setWebSite(String webSite) { this.webSite = webSite; }
    public String getYyzzzch() { return yyzzzch; }
    public void setYyzzzch(String yyzzzch) { this.yyzzzch = yyzzzch; }
    public String getFr() { return fr; }
    public void setFr(String fr) { this.fr = fr; }
    public String getZczj() { return zczj; }
    public void setZczj(String zczj) { this.zczj = zczj; }
    public String getNyye() { return nyye; }
    public void setNyye(String nyye) { this.nyye = nyye; }
    public String getKhyh() { return khyh; }
    public void setKhyh(String khyh) { this.khyh = khyh; }
    public String getKhzh() { return khzh; }
    public void setKhzh(String khzh) { this.khzh = khzh; }
    public String getDsdjh() { return dsdjh; }
    public void setDsdjh(String dsdjh) { this.dsdjh = dsdjh; }
    public String getGsdjh() { return gsdjh; }
    public void setGsdjh(String gsdjh) { this.gsdjh = gsdjh; }
    public Integer getState() { return state; }
    public void setState(Integer state) { this.state = state; }
    public Integer getIsValid() { return isValid; }
    public void setIsValid(Integer isValid) { this.isValid = isValid; }
    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }
    public Date getUpdateDate() { return updateDate; }
    public void setUpdateDate(Date updateDate) { this.updateDate = updateDate; }
}
