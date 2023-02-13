package d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 政策体系-报名-新增
 * @author wkk
 **/
@ApiModel(value = "PolicyEnlistInsetVm", description = "政策体系-报名-新增")
public class PolicyEnlistInsetVm {
    @NotBlank(message = "政策体系ID不可为空")
    @ApiModelProperty("政策体系ID")
    private String policyId;

    @NotBlank(message = "企业ID不可为空")
    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @NotBlank(message = "企业名称不可为空")
    @ApiModelProperty("企业名称")
    private String enterpriseName;

    @NotBlank(message = "联系人不可为空")
    @ApiModelProperty("联系人")
    private String liaisonName;

    @NotBlank(message = "联系人电话不可为空")
    @ApiModelProperty("联系人电话")
    private String liaisonTel;

    @ApiModelProperty("附件")
    private String attachmentPath;

    @NotBlank(message = "报名简介不可为空")
    @ApiModelProperty("报名简介")
    private String content;

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
