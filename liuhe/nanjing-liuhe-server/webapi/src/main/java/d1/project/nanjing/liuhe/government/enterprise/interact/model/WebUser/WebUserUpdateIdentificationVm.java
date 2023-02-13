package d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@ApiModel(value = "WebUserUpdateIdentificationVm", description = "企业用户认证")
public class WebUserUpdateIdentificationVm {

    @ApiModelProperty("用户账户ID")
    private String webUserId;

    @ApiModelProperty("统一信用代码")
    private String unifiedCreditCode;

    @ApiModelProperty("企业名称")
    private String enterpriseName;

    @ApiModelProperty("联系人")
    private String liaisonName;

    @ApiModelProperty("联系人电话")
    private String liaisonTel;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("行业")
    private String industry;

    @ApiModelProperty("规模")
    private String scale;

    @ApiModelProperty("成立日期")
    @Temporal(TemporalType.DATE)
    private Calendar establishmentDate;

    @ApiModelProperty("从业人员")
    private String employeesSize;

    @ApiModelProperty("企业LOGO")
    private String logo;

    @ApiModelProperty("企业简介")
    private String briefIntroduction;

    @ApiModelProperty("注册地址")
    private String address;

    @ApiModelProperty("注册资本")
    private String registeredCapital;

    @ApiModelProperty("企业类型")
    private String type;

    @ApiModelProperty("企业网站")
    private String url;

    public String getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(String webUserId) {
        this.webUserId = webUserId;
    }

    public String getUnifiedCreditCode() {
        return unifiedCreditCode;
    }

    public void setUnifiedCreditCode(String unifiedCreditCode) {
        this.unifiedCreditCode = unifiedCreditCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public Calendar getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Calendar establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public String getEmployeesSize() {
        return employeesSize;
    }

    public void setEmployeesSize(String employeesSize) {
        this.employeesSize = employeesSize;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
