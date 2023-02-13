package d1.project.nanjing.liuhe.government.enterprise.interact.model.WorkOrder;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "WorkOrderInsertVm", description = "工单管理-申请")
public class WorkOrderInsertVm {

    @NotBlank(message = "联系人不可为空")
    @ApiModelProperty("联系人姓名")
    private String liaisonName;

    @NotBlank(message = "联系人电话不可为空")
    @ApiModelProperty("联系人电话")
    private String liaisonTel;

    @NotBlank(message = "诉求标题不可为空")
    @ApiModelProperty("诉求标题")
    private String title;

    @NotBlank(message = "诉求内容不可为空")
    @ApiModelProperty("诉求内容")
    private String content;

    @NotBlank(message = "申请的服务名称不可为空")
    @ApiModelProperty("申请的服务名称")
    private String serviceName;

    @NotBlank(message = "服务项名称不可为空")
    @ApiModelProperty("服务项名称")
    private String serviceItemName;

    @ApiModelProperty("附件")
    private String attachmentPath;

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

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
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
