package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 入驻企业
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_settled_enterprise")
@ApiModel(value = "SettledEnterprise", description = "入驻企业")
public class SettledEnterpriseEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("企业ID")
    @Column(length = 32)
    private String enterpriseId;

    @ApiModelProperty("企业名称")
    @Column(length = 100)
    private String enterpriseName;

    @ApiModelProperty("联系人")
    @Column(length = 100)
    private String liaisonName;

    @ApiModelProperty("联系人电话")
    @Column(length = 100)
    private String liaisonTel;

    @ApiModelProperty("企业类型")
    @Column(length = 100)
    private String type;

    @ApiModelProperty("入驻状态：未申请、申请中、已入驻、已驳回、已退园")
    @Column(length = 10)
    private String status;

    @ApiModelProperty("附件")
    @Column(columnDefinition = "TEXT")
    private String attachmentPath;

    @ApiModelProperty("入驻原因")
    private String settledReason;

    @ApiModelProperty("退园原因")
    private String exitReason;

    @ApiModelProperty("审批原因")
    private String examineReason;

    public String getExamineReason() {
        return examineReason;
    }

    public void setExamineReason(String examineReason) {
        this.examineReason = examineReason;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getExitReason() {
        return exitReason;
    }

    public void setExitReason(String exitReason) {
        this.exitReason = exitReason;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
