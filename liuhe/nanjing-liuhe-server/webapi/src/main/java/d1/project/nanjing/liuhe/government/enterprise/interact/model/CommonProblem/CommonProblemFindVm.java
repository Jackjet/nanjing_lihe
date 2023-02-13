package d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonProblem;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

/**
 * 常见问题-分页查询
 * @author wkk
 **/
@ApiModel(value = "CommonProblemFindVm", description = "常见问题-分页查询")
public class CommonProblemFindVm extends PageableVm {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("禁用，启用")
    private String enable;

    @ApiModelProperty("分类")
    private String type;

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

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
