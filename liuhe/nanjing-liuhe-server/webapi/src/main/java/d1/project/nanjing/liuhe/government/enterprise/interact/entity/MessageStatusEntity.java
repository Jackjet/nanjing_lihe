package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 消息状态表
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_message_status")
@ApiModel(value = "MessageStatus", description = "消息状态表")
public class MessageStatusEntity extends BaseCreateEntity {

    @ApiModelProperty("消息ID")
    @Column(length = 32)
    private String messageId;

    @ApiModelProperty("接收者ID")
    @Column(length = 32)
    private String receiveId;

    @ApiModelProperty("接收者名称")
    private String receiveName;

    @ApiModelProperty("状态：已读、未读")
    @Column(length = 10)
    private String status;

    @ApiModelProperty("确认状态：同意、拒绝")
    @Column(length = 10)
    private String state;

    @ApiModelProperty("拒绝原因")
    private String content;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }
}
