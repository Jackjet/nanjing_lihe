package d1.project.nanjing.liuhe.system.model.vm;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.system.model.WebAdminUserBase;

import javax.validation.constraints.NotBlank;

/**
 * 更新管理用户
 *
 * @author kikki
 * @date 2020-09-08 14:29
 */
@ApiModel(value = "WebAdminUserUpdateVm", description = "更新管理用户")
public class WebAdminUserUpdateVm extends WebAdminUserBase {

    @ApiModelProperty("id")
    @NotBlank(message = "id不可为空")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
