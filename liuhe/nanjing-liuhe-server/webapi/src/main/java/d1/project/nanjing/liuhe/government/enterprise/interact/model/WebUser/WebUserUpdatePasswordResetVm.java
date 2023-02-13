package d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author wkk
 **/
@ApiModel(value = "WebUserUpdatePasswordResetVm", description = "批量重置密码")
public class WebUserUpdatePasswordResetVm {

    @ApiModelProperty("ids列表")
    @Size(min = 1, message = "无效操作")
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
