package d1.project.nanjing.liuhe.government.enterprise.interact.model.SettledEnterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

/**
 * 入驻企业-退园
 * @author wkk
 **/
@ApiModel(value = "SettledEnterpriseExitVm", description = "入驻企业-退园")
public class SettledEnterpriseExitVm extends BaseIdVm {

    @ApiModelProperty("退园原因")
    private String exitReason;

    public String getExitReason() {
        return exitReason;
    }

    public void setExitReason(String exitReason) {
        this.exitReason = exitReason;
    }
}
