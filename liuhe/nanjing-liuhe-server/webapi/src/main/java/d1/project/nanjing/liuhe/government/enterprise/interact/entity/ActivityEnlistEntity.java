package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 动态资讯-企业报名
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_activity_enlist")
@ApiModel(value = "ActivityEnlist", description = "企业报名")
public class ActivityEnlistEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("动态资讯ID")
    @Column(length = 32)
    private String activityId;

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
    @Column(length = 50)
    private String liaisonTel;

    @ApiModelProperty("附件")
    @Column(columnDefinition = "TEXT")
    private String attachmentPath;

    @ApiModelProperty("报名简介")
    private String content;

    @ApiModelProperty("回复内容")
    private String replyContent;

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

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
