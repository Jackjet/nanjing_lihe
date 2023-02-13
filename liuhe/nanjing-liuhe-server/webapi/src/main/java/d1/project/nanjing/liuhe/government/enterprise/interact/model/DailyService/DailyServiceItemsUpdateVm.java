package d1.project.nanjing.liuhe.government.enterprise.interact.model.DailyService;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;

/**
 * 日常服务项-更新
 * @author wkk
 **/
@ApiModel(value = "DailyServiceItemsUpdateVm", description = "日常服务项-更新")
public class DailyServiceItemsUpdateVm extends BaseIdVm {

    @NotBlank(message = "服务ID不可为空")
    @ApiModelProperty("服务ID")
    private String serviceId;

    @NotBlank(message = "名称不可为空")
    @ApiModelProperty("名称")
    private String name;

    @NotBlank(message = "图片不可为空")
    @ApiModelProperty("图片")
    private String imagesPaht;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagesPaht() {
        return imagesPaht;
    }

    public void setImagesPaht(String imagesPaht) {
        this.imagesPaht = imagesPaht;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
