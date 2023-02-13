package d1.project.nanjing.liuhe.quality.small.station.model.HomeRotationChart;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 首页轮播图-更新
 * @author wkk
 **/
@ApiModel(value = "HomeRotationChartUpdateVm", description = "首页轮播图-更新")
public class HomeRotationChartUpdateVm {

    /**
     * 轮播图ID
     **/
    @ApiModelProperty("轮播图ID")
    @NotBlank(message = "ID不可为空")
    private String id;
    /**
     * 名称
     **/
    @ApiModelProperty("名称")
    @NotBlank(message = "名称不可为空")
    private String name;
    /**
     * 序号
     **/
    @ApiModelProperty("序号")
    private Integer sortNumber;
    /**
     * 图片路径
     **/
    @ApiModelProperty("图片路径")
    @NotBlank(message = "图片路径不可为空")
    private String imagesPaht;
    /**
     * 禁用，启用
     **/
    @ApiModelProperty("禁用，启用")
    @NotBlank(message = "启用禁用不可为空")
    private String enable;
    /**
     * 跳转页面
     **/
    @ApiModelProperty("跳转页面")
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getImagesPaht() {
        return imagesPaht;
    }

    public void setImagesPaht(String imagesPaht) {
        this.imagesPaht = imagesPaht;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
