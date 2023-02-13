package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * 线上课堂
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_online_classroom")
@ApiModel(value = "OnlineClassroom", description = "线上课堂")
public class OnlineClassroomEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("标题")
    @Column(length = 100)
    private String title;

    @ApiModelProperty("禁用，启用")
    @Column(length = 10)
    private String enable;

    @ApiModelProperty("来源：部门ID")
    @Column(length = 32)
    private String organizationId;

    @ApiModelProperty("来源：部门名称")
    @Column(length = 100)
    private String organizationName;

    @ApiModelProperty("分类")
    @Column(length = 50)
    private String type;

    @ApiModelProperty(value = "发布时间", example = "2021-01-01 12:12:12")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar releaseTime;

    @ApiModelProperty("视频封面图片")
    @Column(columnDefinition = "TEXT")
    private String imagePath;

    @ApiModelProperty("视频")
    @Column(columnDefinition = "TEXT")
    private String attachmentPath;

    @ApiModelProperty("详情")
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
