package d1.project.nanjing.liuhe.quality.small.station.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 质量小站用户登录
 * @author wkk
 **/
@ApiModel(value = "WebUserSignInVm", description = "用户登录")
public class WebUserSignInVm {

    /**
     * 用于验证码绑定的ID
     **/
    @NotBlank(message = "验证码无效")
    @ApiModelProperty("用于验证码绑定的ID")
    private String verificationCodeId;

    /**
     * 手机号
     **/
    @NotBlank(message = "手机号不可为空")
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 密码
     **/
    @NotBlank(message = "密码不可为空")
    @ApiModelProperty("密码")
    private String password;

    /**
     * 验证码
     **/
    @NotBlank(message = "验证码不可为空")
    @ApiModelProperty("验证码")
    private String verificationCode;

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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getVerificationCodeId() {
        return verificationCodeId;
    }

    public void setVerificationCodeId(String verificationCodeId) {
        this.verificationCodeId = verificationCodeId;
    }
}
