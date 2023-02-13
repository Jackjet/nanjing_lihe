package d1.project.nanjing.liuhe.government.enterprise.interact.model.OnlineClassroom;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;

/**
 * 在线课堂-发布
 * @author wkk
 **/
@ApiModel(value = "OnlineClassroomReleaseVm", description = "在线课堂-发布")
public class OnlineClassroomReleaseVm extends BaseIdVm {

    @ApiModelProperty("发布状态")
    @NotBlank(message = "发布状态不可为空")
    private String enable;

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
