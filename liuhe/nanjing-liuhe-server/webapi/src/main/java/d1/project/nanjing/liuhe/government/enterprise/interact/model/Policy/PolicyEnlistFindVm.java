package d1.project.nanjing.liuhe.government.enterprise.interact.model.Policy;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

import javax.validation.constraints.NotBlank;

/**
 * 政策体系-报名-分页查询
 * @author wkk
 **/
@ApiModel(value = "PolicyEnlistFindVm", description = "政策体系-报名-分页查询")
public class PolicyEnlistFindVm extends PageableVm {
    @NotBlank(message = "政策体系ID不可为空")
    @ApiModelProperty("政策体系ID")
    private String policyId;

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("企业名称")
    private String enterpriseName;

    @ApiModelProperty("联系人")
    private String liaisonName;

    @ApiModelProperty("联系人电话")
    private String liaisonTel;

    @ApiModelProperty(value = "开始时间", example = "2021-01-01 00:00:00")
    private String startTime;

    @ApiModelProperty(value = "结束时间", example = "2021-12-12 00:00:00")
    private String endTime;

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getLiaisonName() {
        return liaisonName;
    }

    public void setLiaisonName(String liaisonName) {
        this.liaisonName = liaisonName;
    }

    public String getLiaisonTel() {
        return liaisonTel;
    }

    public void setLiaisonTel(String liaisonTel) {
        this.liaisonTel = liaisonTel;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
