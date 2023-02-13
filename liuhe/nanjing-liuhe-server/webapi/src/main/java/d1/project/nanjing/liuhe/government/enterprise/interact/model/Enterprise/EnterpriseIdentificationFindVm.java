package d1.project.nanjing.liuhe.government.enterprise.interact.model.Enterprise;

import d1.framework.webapi.annotation.ApiModelProperty;

import javax.persistence.Column;

public class EnterpriseIdentificationFindVm {

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

    @ApiModelProperty("是否认证，2：认证通过，3：认证驳回")
    private Integer identification;

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

    public Integer getIdentification() {
        return identification;
    }

    public void setIdentification(Integer identification) {
        this.identification = identification;
    }
}
