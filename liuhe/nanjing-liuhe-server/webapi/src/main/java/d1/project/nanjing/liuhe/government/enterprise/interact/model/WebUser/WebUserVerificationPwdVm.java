package d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "WebUserVerificationPwdVm", description = "验证账号密码")
public class WebUserVerificationPwdVm {

    @NotBlank(message = "用户名不可为空")
    @ApiModelProperty("用户名")
    private String phone;

    @NotBlank(message = "密码不可为空")
    @ApiModelProperty("密码")
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
