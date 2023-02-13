package d1.project.nanjing.liuhe.government.enterprise.interact.model.Message;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

@ApiModel(value = "MessageStatusFindVm", description = "消息接收方-分页查询")
public class MessageStatusFindVm extends PageableVm {

    @ApiModelProperty("消息ID")
    private String messageId;

    @ApiModelProperty("状态：已读、未读")
    private String status;

    @ApiModelProperty("确认状态：同意、拒绝")
    private String state;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
