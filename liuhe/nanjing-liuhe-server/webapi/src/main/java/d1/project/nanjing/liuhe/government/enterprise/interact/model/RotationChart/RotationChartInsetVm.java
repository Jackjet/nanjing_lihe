package d1.project.nanjing.liuhe.government.enterprise.interact.model.RotationChart;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 轮播图-新增
 * @author wkk
 **/
@ApiModel(value = "RotationChartInsetVm", description = "轮播图-新增")
public class RotationChartInsetVm {

    @ApiModelProperty("标题")
    @NotBlank(message = "标题不可为空")
    private String title;

    @ApiModelProperty("图片路径")
    @NotBlank(message = "图片路径不可为空")
    private String imagesPaht;

    @ApiModelProperty("跳转页面")
    private String url;

    @ApiModelProperty("配图文字")
    private String imagesContent;

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
