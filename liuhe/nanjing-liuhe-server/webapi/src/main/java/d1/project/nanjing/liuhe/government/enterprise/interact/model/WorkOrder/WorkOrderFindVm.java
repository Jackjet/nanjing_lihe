package d1.project.nanjing.liuhe.government.enterprise.interact.model.WorkOrder;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

@ApiModel(value = "WorkOrderFindVm", description = "工单管理-分页查询")
public class WorkOrderFindVm extends PageableVm {

    @ApiModelProperty("诉求标题")
    private String title;

    @ApiModelProperty("开始时间：2021-01-01 12:12:12")
    private String startTime;

    @ApiModelProperty("结束时间：2021-01-01 12:12:12")
    private String endTime;

    @ApiModelProperty("企业名称")
    private String enterpriseName;

    @ApiModelProperty("联系人姓名")
    private String liaisonName;

    @ApiModelProperty("联系人电话")
    private String liaisonTel;

    @ApiModelProperty("企业类型")
    private String enterpriseType;

    @ApiModelProperty("工单状态：待处理、待评价、已评价")
    private String status;

    @ApiModelProperty("申请的服务")
    private String serviceName;

    @ApiModelProperty("服务项")
    private String serviceItemName;

    @ApiModelProperty("催办：0：否，1：是")
    private Integer urge;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
