package d1.project.nanjing.liuhe.government.enterprise.interact.model.SettledEnterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.RegexpConstant;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 入驻企业-审批
 * @author wkk
 **/
@ApiModel(value = "SettledEnterpriseExamineVm", description = "入驻企业-审批")
public class SettledEnterpriseExamineVm extends BaseIdVm {

    @NotBlank(message = "入驻状态不可为空")
    @Pattern(regexp = RegexpConstant.SETTLED_STATUS, message = "非法入驻状态")
    @ApiModelProperty("入驻状态：已入驻、已驳回")
    private String status;

    @ApiModelProperty("审批原因")
    private String examineReason;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExamineReason() {
        return examineReason;
    }

    public void setExamineReason(String examineReason) {
        this.examineReason = examineReason;
    }
}
