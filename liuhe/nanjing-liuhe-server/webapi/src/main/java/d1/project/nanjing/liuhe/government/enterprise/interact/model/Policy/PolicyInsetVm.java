package d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 政策体系-新增
 * @author wkk
 **/
@ApiModel(value = "PolicyInsetVm", description = "政策体系-新增")
public class PolicyInsetVm {
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
    @ApiModelProperty("分类")
    private String type;

    @ApiModelProperty("附件")
    private String attachmentPath;

    @ApiModelProperty("图片")
    private String imagesPaht;

    @NotBlank(message = "详情不可为空")
    @ApiModelProperty("详情")
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

    public String getImagesPaht() {
        return imagesPaht;
    }

    public void setImagesPaht(String imagesPaht) {
        this.imagesPaht = imagesPaht;
    }
}
