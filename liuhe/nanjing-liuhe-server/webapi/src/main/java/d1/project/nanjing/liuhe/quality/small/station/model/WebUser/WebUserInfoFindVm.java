package d1.project.nanjing.liuhe.quality.small.station.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

@ApiModel(value = "WebUserInfoFindVm", description = "用户信息分页查询")
public class WebUserInfoFindVm extends PageableVm {

    @ApiModelProperty("企业名称")
    private String enterpriseName;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("联系人")
    private String liaisonName;

    @ApiModelProperty("联系人电话")
    private String liaisonTel;

    @ApiModelProperty("省")
    private String province;

    @ApiModelProperty("市")
    private String city;

    @ApiModelProperty("区")
    private String area;

    @ApiModelProperty("行业：O2O、信息技术、大数据、物联网、智能硬件、艺术设计、社交服务、房产酒店、TMT、" +
            "云计算、软件工程、新能源、互联网+、农业、高端制作、餐饮服务、大消费、教育、汽车交通、房户服务、" +
            "医疗健康、旅游、本地生活、游戏、广告营销、硬件")
    private String industry;

    @ApiModelProperty("0:禁用，1:启用")
    private Integer enable;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
