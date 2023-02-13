package d1.project.nanjing.liuhe.government.enterprise.interact.view;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateEntity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;

/**
 * 工单管理-列表
 * @author wkk
 **/
@Entity
@Immutable
@Subselect("SELECT t.id, t.enterprise_id, t.enterprise_name, t.liaison_name, " +
        "t.liaison_tel, t.enterprise_type, t.title, t.content, t.status, " +
        "t.create_time, t.create_by_id, " +
        "t.service_name, t.service_item, t.attachment_path, t.urge FROM d1_zqhd_work_order t")
@ApiModel(value = "WorkOrderView", description = "工单管理-列表")
public class WorkOrderView extends BaseCreateEntity {

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("企业名称")
    private String enterpriseName;

    @ApiModelProperty("联系人姓名")
    private String liaisonName;

    @ApiModelProperty("联系人电话")
    private String liaisonTel;

    @ApiModelProperty("企业类型")
    private String enterpriseType;

    @ApiModelProperty("诉求标题")
    private String title;

    @ApiModelProperty("诉求内容")
    private String content;

    @ApiModelProperty("工单状态：待处理、待评价、已评价")
    private String status;

    @ApiModelProperty("申请的服务")
    private String serviceName;

    @ApiModelProperty("服务项")
    private String serviceItemName;

    @ApiModelProperty("附件")
    private String attachmentPath;

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
