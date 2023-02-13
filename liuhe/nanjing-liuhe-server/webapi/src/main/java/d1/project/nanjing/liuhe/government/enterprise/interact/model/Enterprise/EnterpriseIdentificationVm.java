package d1.project.nanjing.liuhe.government.enterprise.interact.model.Enterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "EnterpriseIdentificationVm", description = "用户认证审核")
public class EnterpriseIdentificationVm {

    @NotBlank(message = "认证信息ID不可为空")
    @ApiModelProperty("认证信息ID")
    private String id;

    @NotNull(message = "认证状态不可为空")
    @ApiModelProperty("是否认证，2：认证通过，3：认证驳回")
    private Integer identification;

    @ApiModelProperty("备注原因")
    private String remarks;

    public Integer getIdentification() {
        return identification;
    }

    public void setIdentification(Integer identification) {
        this.identification = identification;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
