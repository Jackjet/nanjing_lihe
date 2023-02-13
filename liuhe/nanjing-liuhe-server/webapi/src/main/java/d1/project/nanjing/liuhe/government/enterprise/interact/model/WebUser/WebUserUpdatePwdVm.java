package d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "WebUserUpdatePwdVm", description = "修改密码")
public class WebUserUpdatePwdVm {

    @NotBlank(message = "原密码不可为空")
    @ApiModelProperty("原密码")
    private String password;

    @NotBlank(message = "新密码不可为空")
    @ApiModelProperty("新密码")
    private String newPassword;

    @NotBlank(message = "验证码不可为空")
    @ApiModelProperty("图片验证码")
    private String verificationCode;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
