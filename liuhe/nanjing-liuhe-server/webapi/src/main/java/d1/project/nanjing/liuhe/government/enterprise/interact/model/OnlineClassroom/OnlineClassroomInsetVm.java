package d1.project.nanjing.liuhe.government.enterprise.interact.model.OnlineClassroom;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 在线课堂-新增
 * @author wkk
 **/
@ApiModel(value = "OnlineClassroomInsetVm", description = "在线课堂-新增")
public class OnlineClassroomInsetVm {

    @ApiModelProperty("标题")
    @NotBlank(message = "标题不可为空")
    private String title;

    @ApiModelProperty("来源：部门ID")
    @NotBlank(message = "来源：部门ID不可为空")
    private String organizationId;

    @ApiModelProperty("来源：部门名称")
    @NotBlank(message = "来源：部门名称不可为空")
    private String organizationName;

    @ApiModelProperty("分类")
    @NotBlank(message = "分类不可为空")
    private String type;

    @ApiModelProperty("视频封面图片")
    @NotBlank(message = "视频封面图片不可为空")
    private String imagePath;

    @ApiModelProperty("视频")
    @NotBlank(message = "视频不可为空")
    private String attachmentPath;

    @ApiModelProperty("详情")
    @NotBlank(message = "详情不可为空")
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
