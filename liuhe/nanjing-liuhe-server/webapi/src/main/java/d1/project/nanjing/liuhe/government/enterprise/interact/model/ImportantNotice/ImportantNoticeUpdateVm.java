package d1.project.nanjing.liuhe.government.enterprise.interact.model.ImportantNotice;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;

/**
 * 重要通知-更新
 * @author wkk
 **/
@ApiModel(value = "ImportantNoticeUpdateVm", description = "重要通知-更新")
public class ImportantNoticeUpdateVm extends BaseIdVm {

    @ApiModelProperty("标题")
    @NotBlank(message = "标题不可为空")
    private String title;

    @ApiModelProperty("图片路径")
    @NotBlank(message = "图片路径不可为空")
    private String imagesPaht;

    @ApiModelProperty("配图文字")
    @NotBlank(message = "配图文字不可为空")
    private String imagesContent;

    @ApiModelProperty("部门ID")
    @NotBlank(message = "部门ID不可为空")
    private String organizationId;

    @ApiModelProperty("部门名称")
    @NotBlank(message = "部门名称不可为空")
    private String organizationName;

    @ApiModelProperty("分类：等确认后再写")
    @NotBlank(message = "分类不可为空")
    private String type;

    @ApiModelProperty("详情")
    @NotBlank(message = "详情不可为空")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagesPaht() {
        return imagesPaht;
    }

    public void setImagesPaht(String imagesPaht) {
        this.imagesPaht = imagesPaht;
    }

    public String getImagesContent() {
        return imagesContent;
    }

    public void setImagesContent(String imagesContent) {
        this.imagesContent = imagesContent;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
