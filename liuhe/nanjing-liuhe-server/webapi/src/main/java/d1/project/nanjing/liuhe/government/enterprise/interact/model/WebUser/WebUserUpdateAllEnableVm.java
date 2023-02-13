package d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author wkk
 **/
@ApiModel(value = "WebUserUpdateAllEnableVm", description = "web用户管理_启动禁用列表")
public class WebUserUpdateAllEnableVm {

    /**
     * ids
     **/
    @ApiModelProperty("ids")
    @Size(min = 1, message = "无效操作")
    private List<String> ids;

    /**
     * 0启用1禁用
     **/
    @ApiModelProperty("1启用，0禁用")
    @NotNull(message = "启用禁用不可为空")
    private Integer enable;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
