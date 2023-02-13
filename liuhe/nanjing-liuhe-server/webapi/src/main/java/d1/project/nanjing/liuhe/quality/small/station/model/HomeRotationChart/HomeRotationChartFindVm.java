package d1.project.nanjing.liuhe.quality.small.station.model.HomeRotationChart;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

/**
 * 首页轮播图-分页查询
 * @author wkk
 **/
@ApiModel(value = "HomeRotationChartFindVm", description = "首页轮播图-分页查询")
public class HomeRotationChartFindVm extends PageableVm {
    /**
     * 名称
     **/
    @ApiModelProperty("名称")
    private String name;
    /**
     * 禁用，启用
     **/
    @ApiModelProperty("禁用，启用")
    private String enable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
