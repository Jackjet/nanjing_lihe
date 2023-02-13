package d1.project.nanjing.liuhe.quality.small.station.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * web用户管理_启动禁用
 * @author wkk
 **/
@ApiModel(value = "WebUserUpdateEnableVm", description = "web用户管理_启动禁用")
public class WebUserUpdateEnableVm {

    /**
     * id
     **/
    @ApiModelProperty("id")
    @NotNull(message = "用户ID不可为空")
    private String id;

    /**
     * 0：启用 1：禁用
     **/
    @ApiModelProperty("0：启用 1：禁用")
    @NotNull(message = "启用禁用不可为空")
    private Integer enable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
