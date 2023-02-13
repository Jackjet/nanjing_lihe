package d1.project.nanjing.liuhe.quality.small.station.model.Consult;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 我要咨询-回复
 * @author wkk
 **/
@ApiModel(value = "ConsultReplyVm", description = "我要咨询-回复")
public class ConsultLeavingMessageVm {
    /**
     * 咨询ID
     **/
    @ApiModelProperty("咨询ID")
    @NotBlank(message = "咨询ID不可为空")
    private String id;
    /**
     * 回复内容
     **/
    @ApiModelProperty("回复内容")
    @NotBlank(message = "回复内容不可为空")
    private String replyMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }
}
