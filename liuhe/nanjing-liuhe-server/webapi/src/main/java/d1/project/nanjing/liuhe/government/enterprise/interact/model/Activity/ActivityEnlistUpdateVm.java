package d1.project.nanjing.liuhe.government.enterprise.interact.model.Activity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;

/**
 * 动态资讯-报名-更新
 * @author wkk
 **/
@ApiModel(value = "ActivityEnlistUpdateVm", description = "动态资讯-报名-更新")
public class ActivityEnlistUpdateVm extends BaseIdVm {
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
