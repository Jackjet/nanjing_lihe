package d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;

/**
 * 日常服务详情-更新
 * @author wkk
 **/
@ApiModel(value = "DailyServiceDetailUpdateVm", description = "日常服务详情-更新")
public class DailyServiceDetailUpdateVm extends BaseIdVm {

    @NotBlank(message = "服务ID不可为空")
    @ApiModelProperty("服务ID")
    private String serviceId;

    @NotBlank(message = "服务项ID不可为空")
    @ApiModelProperty("服务项ID")
    private String itemId;

    @NotBlank(message = "标题不可为空")
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("附件")
    private String attachmentPath;

    @ApiModelProperty("详情")
    private String content;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
