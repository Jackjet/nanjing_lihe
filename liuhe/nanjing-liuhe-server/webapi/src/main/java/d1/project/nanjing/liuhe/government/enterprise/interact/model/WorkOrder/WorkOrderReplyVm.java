package d1.project.nanjing.liuhe.government.enterprise.interact.model.WorkOrder;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "WorkOrderFindVm", description = "工单管理-回复")
public class WorkOrderReplyVm extends BaseIdVm {

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
