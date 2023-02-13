package d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;

/**
 * 政策体系-报名-更新
 * @author wkk
 **/
@ApiModel(value = "PolicyEnlistUpdateVm", description = "政策体系-报名-更新")
public class PolicyEnlistUpdateVm extends BaseIdVm {
    @NotBlank(message = "回复内容不可为空")
    @ApiModelProperty("回复内容")
    private String replyContent;

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}
