package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 工单管理
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_work_order")
@ApiModel(value = "WorkOrder", description = "工单管理")
public class WorkOrderEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("企业ID")
    @Column(length = 32)
    private String enterpriseId;

    @ApiModelProperty("企业名称")
    @Column(length = 100)
    private String enterpriseName;

    @ApiModelProperty("联系人姓名")
    @Column(length = 50)
    private String liaisonName;

    @ApiModelProperty("联系人电话")
    @Column(length = 20)
    private String liaisonTel;

    @ApiModelProperty("企业类型")
    @Column(length = 50)
    private String enterpriseType;

    @ApiModelProperty("诉求标题")
    @Column(length = 100)
    private String title;

    @ApiModelProperty("诉求内容")
    private String content;

    @ApiModelProperty("工单状态：待处理、待评价、已评价")
    @Column(length = 50)
    private String status;

    @ApiModelProperty("申请的服务")
    @Column(length = 50)
    private String serviceName;

    @ApiModelProperty("服务项")
    @Column(length = 50)
    private String serviceItemName;

    @ApiModelProperty("附件")
    @Column(columnDefinition = "TEXT")
    private String attachmentPath;

    @ApiModelProperty("回复内容")
    @Column(columnDefinition = "TEXT")
    private String replyContent;

    @ApiModelProperty("评分1")
    @Column(length = 1)
    private Integer giveMark1;

    @ApiModelProperty("评分2")
    @Column(length = 1)
    private Integer giveMark2;

    @ApiModelProperty("评分3")
    @Column(length = 1)
    private Integer giveMark3;

    @ApiModelProperty("评分4")
    @Column(length = 1)
    private Integer giveMark4;

    @ApiModelProperty("评分5")
    @Column(length = 1)
    private Integer giveMark5;

    @ApiModelProperty("评价内容")
    private String evaluateContent;

    @ApiModelProperty("加急：0：否，1：是")
    private Integer urge;

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

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Integer getGiveMark1() {
        return giveMark1;
    }

    public void setGiveMark1(Integer giveMark1) {
        this.giveMark1 = giveMark1;
    }

    public Integer getGiveMark2() {
        return giveMark2;
    }

    public void setGiveMark2(Integer giveMark2) {
        this.giveMark2 = giveMark2;
    }

    public Integer getGiveMark3() {
        return giveMark3;
    }

    public void setGiveMark3(Integer giveMark3) {
        this.giveMark3 = giveMark3;
    }

    public Integer getGiveMark4() {
        return giveMark4;
    }

    public void setGiveMark4(Integer giveMark4) {
        this.giveMark4 = giveMark4;
    }

    public Integer getGiveMark5() {
        return giveMark5;
    }

    public void setGiveMark5(Integer giveMark5) {
        this.giveMark5 = giveMark5;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public Integer getUrge() {
        return urge;
    }

    public void setUrge(Integer urge) {
        this.urge = urge;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceItemName() {
        return serviceItemName;
    }

    public void setServiceItemName(String serviceItemName) {
        this.serviceItemName = serviceItemName;
    }
}
