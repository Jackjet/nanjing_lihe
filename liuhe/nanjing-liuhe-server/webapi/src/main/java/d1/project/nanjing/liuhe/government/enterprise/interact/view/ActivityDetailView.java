package d1.project.nanjing.liuhe.government.enterprise.interact.view;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import java.util.Calendar;

/**
 * 动态资讯-详情
 * @author wkk
 **/
@ApiModel(value = "ActivityDetailView", description = "动态资讯-详情")
public class ActivityDetailView {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("部门ID")
    private String organizationId;

    @ApiModelProperty("部门名称")
    private String organizationName;

    @ApiModelProperty("分类")
    private String type;

    @ApiModelProperty("阶段：未开始、进行中、已结束")
    private String stage;

    @ApiModelProperty(value = "发布日期", example = "2021-01-01 00:00:00")
    private Calendar releaseTime;

    @ApiModelProperty(value = "开始时间", example = "2021-01-01 00:00:00")
    private Calendar startTime;

    @ApiModelProperty(value = "结束时间", example = "2021-12-12 00:00:00")
    private Calendar endTime;

    @ApiModelProperty("附件")
    private String attachmentPath;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("企业名称")
    private String enterpriseName;

    @ApiModelProperty("联系人")
    private String liaisonName;

    @ApiModelProperty("联系人电话")
    private String liaisonTel;

    @ApiModelProperty("报名附件")
    private String enlistAttachmentPath;

    @ApiModelProperty("报名简介")
    private String enlistContent;

    @ApiModelProperty("回复内容")
    private String replyContent;

    @ApiModelProperty(value = "回复时间", example = "2021-12-12 00:00:00")
    private Calendar replyTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getEnlistAttachmentPath() {
        return enlistAttachmentPath;
    }

    public void setEnlistAttachmentPath(String enlistAttachmentPath) {
        this.enlistAttachmentPath = enlistAttachmentPath;
    }

    public String getEnlistContent() {
        return enlistContent;
    }

    public void setEnlistContent(String enlistContent) {
        this.enlistContent = enlistContent;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Calendar getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Calendar replyTime) {
        this.replyTime = replyTime;
    }
}
