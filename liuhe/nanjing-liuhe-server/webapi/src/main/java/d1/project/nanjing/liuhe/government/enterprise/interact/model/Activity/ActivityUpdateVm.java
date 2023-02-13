package d1.project.nanjing.liuhe.government.enterprise.interact.model.Activity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;
import java.util.Calendar;

/**
 * 动态资讯-更新
 * @author wkk
 **/
@ApiModel(value = "ActivityUpdateVm", description = "动态资讯-更新")
public class ActivityUpdateVm extends BaseIdVm {
    @NotBlank(message = "标题不可为空")
    @ApiModelProperty("标题")
    private String title;

    @NotBlank(message = "部门ID不可为空")
    @ApiModelProperty("部门ID")
    private String organizationId;

    @NotBlank(message = "部门名称不可为空")
    @ApiModelProperty("部门名称")
    private String organizationName;

    @NotBlank(message = "分类不可为空")
    @ApiModelProperty("分类：手动输入50个字以内")
    private String type;

    @NotBlank(message = "分类不可为空")
    @ApiModelProperty("阶段：未开始、进行中、已结束")
    private String stage;

    @ApiModelProperty(value = "开始时间", example = "2021-01-01 00:00:00")
    private Calendar startTime;

    @ApiModelProperty(value = "结束时间", example = "2021-12-12 00:00:00")
    private Calendar endTime;

    @ApiModelProperty("附件")
    private String attachmentPath;

    @ApiModelProperty("内容")
    private String content;

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
}
