package d1.project.nanjing.liuhe.quality.small.station.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 质量小站用户基本信息
 * @author wkk
 **/
@Entity
@Table(name = "d1_zlxz_web_user_info")
@ApiModel(value = "WebUserInfo", description = "质量小站用户基本信息")
public class WebUserInfoEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("用户账户ID")
    @Column(length = 36)
    private String webUserId;

    @ApiModelProperty("企业名称")
    @Column(length = 100)
    private String enterpriseName;

    @ApiModelProperty("手机号")
    @Column(length = 20)
    private String phone;

    @ApiModelProperty("联系人")
    @Column(length = 100)
    private String liaisonName;

    @ApiModelProperty("联系人电话")
    @Column(length = 100)
    private String liaisonTel;

    @ApiModelProperty("省")
    @Column(length = 50)
    private String province;

    @ApiModelProperty("市")
    @Column(length = 50)
    private String city;

    @ApiModelProperty("区")
    @Column(length = 50)
    private String area;

    @ApiModelProperty("详细地址")
    private String address;

    @ApiModelProperty("行业：O2O、信息技术、大数据、物联网、智能硬件、艺术设计、社交服务、房产酒店、TMT、" +
            "云计算、软件工程、新能源、互联网+、农业、高端制作、餐饮服务、大消费、教育、汽车交通、房户服务、" +
            "医疗健康、旅游、本地生活、游戏、广告营销、硬件")
    @Column(length = 50)
    private String industry;

    @ApiModelProperty("企业logo")
    private String logoPath;

    @ApiModelProperty("0:禁用，1:启用")
    @Column(length = 1)
    private Integer enable;

    public String getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(String webUserId) {
        this.webUserId = webUserId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLiaisonName() {
        return liaisonName;
    }

    public void setLiaisonName(String liaisonName) {
        this.liaisonName = liaisonName;
    }

    public String getLiaisonTel() {
        return liaisonTel;
    }

    public void setLiaisonTel(String liaisonTel) {
        this.liaisonTel = liaisonTel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
