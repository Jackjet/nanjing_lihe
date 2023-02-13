package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * 动态资讯
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_activity")
@ApiModel(value = "Activity", description = "动态资讯")
public class ActivityEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("标题")
    @Column(length = 100)
    private String title;

    @ApiModelProperty("禁用，启用")
    @Column(length = 10)
    private String enable;

    @ApiModelProperty("部门ID")
    @Column(length = 32)
    private String organizationId;

    @ApiModelProperty("部门名称")
    @Column(length = 100)
    private String organizationName;

    @ApiModelProperty("分类：手动输入50个字以内")
    @Column(length = 50)
    private String type;

    @ApiModelProperty("阶段：未开始、进行中、已结束")
    @Column(length = 50)
    private String stage;

    @ApiModelProperty(value = "发布时间", example = "2021-01-01 12:12:12")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar releaseTime;

    @ApiModelProperty(value = "开始时间", example = "2021-01-01 00:00:00")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar startTime;

    @ApiModelProperty(value = "结束时间", example = "2021-12-12 00:00:00")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar endTime;

    @ApiModelProperty("附件")
    @Column(columnDefinition = "TEXT")
    private String attachmentPath;

    @ApiModelProperty("内容")
    @Column(columnDefinition = "TEXT")
    private String content;

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
}
