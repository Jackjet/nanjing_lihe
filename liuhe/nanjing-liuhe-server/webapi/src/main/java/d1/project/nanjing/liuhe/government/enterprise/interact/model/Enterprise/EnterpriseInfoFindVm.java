package d1.project.nanjing.liuhe.government.enterprise.interact.model.Enterprise;

import d1.framework.webapi.annotation.ApiModelProperty;

public class EnterpriseInfoFindVm {

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

    @ApiModelProperty("从业人员")
    private String employeesSize;

    @ApiModelProperty("注册资本")
    private String registeredCapital;

    @ApiModelProperty("0:未入驻，1:已入驻")
    private Integer isSettled;

    @ApiModelProperty("0:禁用，1:启用")
    private Integer enable;

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

    public String getEmployeesSize() {
        return employeesSize;
    }

    public void setEmployeesSize(String employeesSize) {
        this.employeesSize = employeesSize;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public Integer getIsSettled() {
        return isSettled;
    }

    public void setIsSettled(Integer isSettled) {
        this.isSettled = isSettled;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
