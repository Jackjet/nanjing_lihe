package d1.project.nanjing.liuhe.system.model.vm;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 更新管理用户密码
 *
 * @author kikki
 * @date 2020-09-11 10:06
 */
@ApiModel(value = "WebAdminUserUpdatePasswordVm", description = "更新管理用户密码")
public class WebAdminUserUpdatePasswordVm {

    @NotBlank(message = "id不能为空")
    @ApiModelProperty("id")
    private final String id;

    @NotBlank(message = "老密码不能为空")
    @ApiModelProperty("老密码")
    private final String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @ApiModelProperty("新密码")
    private final String newPassword;

    public WebAdminUserUpdatePasswordVm(String id, String oldPassword, String newPassword) {
        this.id = id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getId() {
        return id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

}
