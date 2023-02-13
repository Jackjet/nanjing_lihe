package d1.project.nanjing.liuhe.quality.small.station.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 修改密码
 * @author wkk
 **/
@ApiModel(value = "WebUserUpdatePwdVm", description = "修改密码")
public class WebUserUpdatePwdVm {

    /**
     * 手机号
     **/
    @NotBlank(message = "手机号不可为空")
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 新密码
     **/
    @NotBlank(message = "新密码不可为空")
    @ApiModelProperty("新密码")
    private String newPassword;

    /**
     * 验证码
     **/
    @NotBlank(message = "验证码不可为空")
    @ApiModelProperty("验证码")
    private String verificationCode;


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
