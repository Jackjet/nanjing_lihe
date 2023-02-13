package d1.project.nanjing.liuhe.government.enterprise.interact.model.RotationChart;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

/**
 * 首页轮播图-分页查询
 * @author wkk
 **/
@ApiModel(value = "RotationChartFindVm", description = "轮播图-分页查询")
public class RotationChartFindVm extends PageableVm {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("禁用，启用")
    private String enable;

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
}
