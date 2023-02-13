package d1.project.nanjing.liuhe.common.model;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "BaseIdVm", description = "基类ID")
public class BaseIdVm {

    @ApiModelProperty("ID")
    @NotBlank(message = "ID不可为空")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
