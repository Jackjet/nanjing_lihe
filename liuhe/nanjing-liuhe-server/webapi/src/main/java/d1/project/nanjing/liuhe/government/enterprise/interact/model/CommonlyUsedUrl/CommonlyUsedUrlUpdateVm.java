package d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonlyUsedUrl;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;

/**
 * 常用链接-更新
 * @author wkk
 **/
@ApiModel(value = "CommonlyUsedUrlUpdateVm", description = "常用链接-更新")
public class CommonlyUsedUrlUpdateVm extends BaseIdVm {

    @ApiModelProperty("标题")
    @NotBlank(message = "标题不可为空")
    private String title;

    @ApiModelProperty("分类：等确认后再写")
    @NotBlank(message = "分类不可为空")
    private String type;

    @ApiModelProperty("链接地址")
    @NotBlank(message = "链接地址不可为空")
    private String url;

    @ApiModelProperty("图片")
    @NotBlank(message = "图片不可为空")
    private String imagesPaht;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagesPaht() {
        return imagesPaht;
    }

    public void setImagesPaht(String imagesPaht) {
        this.imagesPaht = imagesPaht;
    }
}
