package d1.project.nanjing.liuhe.government.enterprise.interact.model.RotationChart;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 轮播图-更新
 * @author wkk
 **/
@ApiModel(value = "RotationChartUpdateVm", description = "轮播图-更新")
public class RotationChartUpdateVm {

    @ApiModelProperty("轮播图ID")
    @NotBlank(message = "ID不可为空")
    private String id;

    @NotBlank(message = "名称不可为空")
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图片路径")
    @NotBlank(message = "图片路径不可为空")
    private String imagesPaht;

    @ApiModelProperty("禁用，启用")
    @NotBlank(message = "启用禁用不可为空")
    private String enable;

    @ApiModelProperty("跳转页面")
    private String url;

    @ApiModelProperty("配图文字")
    private String imagesContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getImagesContent() {
        return imagesContent;
    }

    public void setImagesContent(String imagesContent) {
        this.imagesContent = imagesContent;
    }
}
