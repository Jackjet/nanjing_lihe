package d1.project.nanjing.liuhe.government.enterprise.interact.model.ContactYellowPage;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

/**
 * 联系人黄页-分页查询
 * @author wkk
 **/
@ApiModel(value = "ContactYellowPageFindVm", description = "联系人黄页-分页查询")
public class ContactYellowPageFindVm extends PageableVm {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("禁用，启用")
    private String enable;

    @ApiModelProperty("部门ID")
    private String organizationId;

    @ApiModelProperty("部门名称")
    private String organizationName;

    @ApiModelProperty("开始时间：2021-01-01 12:12:12")
    private String startTime;

    @ApiModelProperty("结束时间：2021-01-01 12:12:12")
    private String endTime;

    @ApiModelProperty("联系方式")
    private String liaisonTel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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

    public String getLiaisonTel() {
        return liaisonTel;
    }

    public void setLiaisonTel(String liaisonTel) {
        this.liaisonTel = liaisonTel;
    }
}
