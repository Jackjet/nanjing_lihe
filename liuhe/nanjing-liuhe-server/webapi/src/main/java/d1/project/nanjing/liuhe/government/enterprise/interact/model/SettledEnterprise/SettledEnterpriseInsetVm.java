package d1.project.nanjing.liuhe.government.enterprise.interact.model.SettledEnterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 入驻企业-申请入驻
 * @author wkk
 **/
@ApiModel(value = "SettledEnterpriseInsetVm", description = "入驻企业-申请入驻")
public class SettledEnterpriseInsetVm {

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

    @NotBlank(message = "企业类型不可为空")
    @ApiModelProperty("企业类型")
    private String type;

    @ApiModelProperty("附件")
    private String attachmentPath;

    @NotBlank(message = "入驻原因不可为空")
    @ApiModelProperty("入驻原因")
    private String settledReason;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getSettledReason() {
        return settledReason;
    }

    public void setSettledReason(String settledReason) {
        this.settledReason = settledReason;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }
}
