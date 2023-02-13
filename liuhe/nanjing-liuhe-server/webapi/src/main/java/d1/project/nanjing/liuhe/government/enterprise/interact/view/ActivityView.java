package d1.project.nanjing.liuhe.government.enterprise.interact.view;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import java.util.Calendar;

/**
 * 动态资讯
 * @author wkk
 **/
@Entity
@Immutable
@Subselect("Select id, create_by_id, create_time, update_by_id, update_time, " +
        "enable, end_time, organization_id, organization_name, release_time, " +
        "stage, start_time, title, type , attachment_path From d1_zqhd_activity")
@ApiModel(value = "Activity", description = "动态资讯")
public class ActivityView extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("禁用，启用")
    private String enable;

    @ApiModelProperty("部门ID")
    private String organizationId;

    @ApiModelProperty("部门名称")
    private String organizationName;

    @ApiModelProperty("分类")
    private String type;

    @ApiModelProperty("阶段：未开始、进行中、已结束")
    private String stage;

    @ApiModelProperty(value = "发布时间", example = "2021-01-01 12:12:12")
    private Calendar releaseTime;

    @ApiModelProperty(value = "开始时间", example = "2021-01-01 00:00:00")
    private Calendar startTime;

    @ApiModelProperty(value = "结束时间", example = "2021-12-12 00:00:00")
    private Calendar endTime;

    @ApiModelProperty("附件")
    private String attachmentPath;

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

    public Calendar getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Calendar releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }
}
