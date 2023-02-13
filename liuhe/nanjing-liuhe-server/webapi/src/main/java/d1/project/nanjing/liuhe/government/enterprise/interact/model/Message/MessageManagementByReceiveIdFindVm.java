package d1.project.nanjing.liuhe.government.enterprise.interact.model.Message;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "MessageManagementByReceiveIdFindVm", description = "门户我的消息-分页查询")
public class MessageManagementByReceiveIdFindVm extends PageableVm {

    @NotBlank(message = "接收人ID不可为空")
    @ApiModelProperty("接收人ID")
    private String receiveId;

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }
}
