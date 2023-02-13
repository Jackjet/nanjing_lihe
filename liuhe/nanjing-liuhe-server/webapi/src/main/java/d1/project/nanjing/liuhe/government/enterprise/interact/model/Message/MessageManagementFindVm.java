package d1.project.nanjing.liuhe.government.enterprise.interact.model.Message;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

@ApiModel(value = "MessageManagementFindVm", description = "消息管理-分页查询")
public class MessageManagementFindVm extends PageableVm {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("开始时间：2021-01-01 12:12:12")
    private String startTime;

    @ApiModelProperty("结束时间：2021-01-01 12:12:12")
    private String endTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
