package d1.project.nanjing.liuhe.government.enterprise.interact.model.Activity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

/**
 * 动态资讯-分页查询
 * @author wkk
 **/
@ApiModel(value = "ActivityFindVm", description = "动态资讯-分页查询")
public class ActivityFindVm extends PageableVm {
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("禁用，启用")
    private String enable;

    @ApiModelProperty("部门ID")
    private String organizationId;

    @ApiModelProperty("部门名称")
    private String organizationName;

    @ApiModelProperty("分类：手动输入50个字以内")
    private String type;

    @ApiModelProperty("阶段：未开始、进行中、已结束")
    private String stage;

    @ApiModelProperty(value = "发布开始日期", example = "2021-01-01 00:00:00")
    private String startDate;

    @ApiModelProperty(value = "发布结束日期", example = "2021-01-01 00:00:00")
    private String endDate;

    @ApiModelProperty(value = "活动开始时间", example = "2021-01-01 00:00:00")
    private String startTime;

    @ApiModelProperty(value = "活动结束时间", example = "2021-12-12 00:00:00")
    private String endTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startData) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
}
