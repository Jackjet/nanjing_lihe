package d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * 工商主体信息
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_enterprise_industrial")
@ApiModel(value = "EnterpriseIndustrial", description = "工商主体信息")
public class EnterpriseIndustrialEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("用户账户ID")
    @Column(length = 36)
    private String webUserId;

    @ApiModelProperty("公司类型")
    @Column(length = 100)
    private String enterpriseType;

    @ApiModelProperty("注册资本")
    @Column(length = 100)
    private String registeredCapital;

    @ApiModelProperty("法定代表人")
    @Column(length = 100)
    private String legalRepresentative;

    @ApiModelProperty("成立日期")
    @Temporal(TemporalType.DATE)
    private Calendar establishmentDate;

    @ApiModelProperty("营业期限-开始日期")
    @Temporal(TemporalType.DATE)
    private Calendar businessStart;

    @ApiModelProperty("营业期限-结束日期")
    @Temporal(TemporalType.DATE)
    private Calendar businessEnd;

    @ApiModelProperty("登记机关")
    @Column(length = 100)
    private String registrationAuthority;

    @ApiModelProperty("经营状态")
    @Column(length = 50)
    private String manageStatus;

    @ApiModelProperty("统一信用代码")
    @Column(length = 100)
    private String unifiedCreditCode;

    @ApiModelProperty("监管行业分类")
    @Column(length = 100)
    private String regulatoryIndustryType;

    @ApiModelProperty("企业LOGO")
    private String logo;

    @ApiModelProperty("主体简介")
    private String briefIntroduction;

    @ApiModelProperty("经营场所")
    private String address;

    @ApiModelProperty("经营范围")
    @Column(length = 100)
    private String businessScope;

    public String getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(String webUserId) {
        this.webUserId = webUserId;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public Calendar getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Calendar establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public Calendar getBusinessStart() {
        return businessStart;
    }

    public void setBusinessStart(Calendar businessStart) {
        this.businessStart = businessStart;
    }

    public Calendar getBusinessEnd() {
        return businessEnd;
    }

    public void setBusinessEnd(Calendar businessEnd) {
        this.businessEnd = businessEnd;
    }

    public String getRegistrationAuthority() {
        return registrationAuthority;
    }

    public void setRegistrationAuthority(String registrationAuthority) {
        this.registrationAuthority = registrationAuthority;
    }

    public String getManageStatus() {
        return manageStatus;
    }

    public void setManageStatus(String manageStatus) {
        this.manageStatus = manageStatus;
    }

    public String getUnifiedCreditCode() {
        return unifiedCreditCode;
    }

    public void setUnifiedCreditCode(String unifiedCreditCode) {
        this.unifiedCreditCode = unifiedCreditCode;
    }

    public String getRegulatoryIndustryType() {
        return regulatoryIndustryType;
    }

    public void setRegulatoryIndustryType(String regulatoryIndustryType) {
        this.regulatoryIndustryType = regulatoryIndustryType;
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

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }
}
