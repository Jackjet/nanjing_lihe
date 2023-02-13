package d1.project.nanjing.liuhe.quality.small.station.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 咨询回复
 * @author wkk
 **/
@Entity
@Table(name = "d1_consult_reply")
@ApiModel(value = "ConsultReply", description = "咨询回复")
public class ConsultReplyEntity extends BaseCreateEntity {

    @ApiModelProperty("咨询ID")
    @Column(length = 36)
    private String consultId;

    @ApiModelProperty("回复人")
    @Column(length = 100)
    private String replyUserName;

    @ApiModelProperty("回复内容")
    private String replyMessage;

    public String getConsultId() {
        return consultId;
    }

    public void setConsultId(String consultId) {
        this.consultId = consultId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }
}
