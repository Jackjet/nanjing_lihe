package d1.project.nanjing.liuhe.government.enterprise.interact.model.WorkOrder;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "WorkOrderFindVm", description = "工单管理-评价")
public class WorkOrderEvaluateVm extends BaseIdVm {

    @ApiModelProperty("评分1")
    private Integer giveMark1;

    @ApiModelProperty("评分2")
    private Integer giveMark2;

    @ApiModelProperty("评分3")
    private Integer giveMark3;

    @ApiModelProperty("评分4")
    private Integer giveMark4;

    @ApiModelProperty("评分5")
    private Integer giveMark5;

    @NotBlank(message = "评价内容不可为空")
    @ApiModelProperty("评价内容")
    private String evaluateContent;

    public Integer getGiveMark1() {
        return giveMark1;
    }

    public void setGiveMark1(Integer giveMark1) {
        this.giveMark1 = giveMark1;
    }

    public Integer getGiveMark2() {
        return giveMark2;
    }

    public void setGiveMark2(Integer giveMark2) {
        this.giveMark2 = giveMark2;
    }

    public Integer getGiveMark3() {
        return giveMark3;
    }

    public void setGiveMark3(Integer giveMark3) {
        this.giveMark3 = giveMark3;
    }

    public Integer getGiveMark4() {
        return giveMark4;
    }

    public void setGiveMark4(Integer giveMark4) {
        this.giveMark4 = giveMark4;
    }

    public Integer getGiveMark5() {
        return giveMark5;
    }

    public void setGiveMark5(Integer giveMark5) {
        this.giveMark5 = giveMark5;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }
}
