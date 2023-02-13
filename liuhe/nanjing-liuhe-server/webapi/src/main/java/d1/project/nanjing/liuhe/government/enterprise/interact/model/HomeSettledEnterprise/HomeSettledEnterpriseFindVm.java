package d1.project.nanjing.liuhe.government.enterprise.interact.model.HomeSettledEnterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

/**
 * 首页入驻企业-分页查询
 * @author wkk
 **/
@ApiModel(value = "HomeSettledEnterpriseFindVm", description = "首页入驻企业-分页查询")
public class HomeSettledEnterpriseFindVm extends PageableVm {

    @ApiModelProperty("企业名称")
    private String enterpriseName;

    @ApiModelProperty("开始时间：2021-01-01 12:12:12")
    private String startTime;

    @ApiModelProperty("结束时间：2021-01-01 12:12:12")
    private String endTime;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
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
}
