package d1.project.nanjing.liuhe.government.enterprise.interact.model.Message;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "MessageManagementInsertVm", description = "消息管理-新增")
public class MessageManagementInsertVm {

    @NotBlank(message = "标题不可为空")
    @ApiModelProperty("标题")
    private String title;

    @NotBlank(message = "内容不可为空")
    @ApiModelProperty("内容")
    private String content;

    @NotBlank(message = "接收者ID不可为空")
    @ApiModelProperty("接收者IDS，多个逗号隔开")
    private String receiveIds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiveIds() {
        return receiveIds;
    }

    public void setReceiveIds(String receiveIds) {
        this.receiveIds = receiveIds;
    }
}
