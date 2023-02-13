package d1.project.nanjing.liuhe.quality.small.station.model.ServiceProviderInfo;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;

/**
 * 服务商信息-编辑
 * @author wkk
 **/
@ApiModel(value = "ServiceProviderInfoUpdateVm", description = "服务商信息-编辑")
public class ServiceProviderInfoUpdateVm extends BaseIdVm {

    @ApiModelProperty("标题名称")
    @NotBlank(message = "标题不可为空")
    private String title;

    @ApiModelProperty("简介")
    private String introduce;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("图片")
    private String imagesPaht;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagesPaht() {
        return imagesPaht;
    }

    public void setImagesPaht(String imagesPaht) {
        this.imagesPaht = imagesPaht;
    }

}
