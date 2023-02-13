package d1.project.nanjing.liuhe.quality.small.station.model.WebUser;

import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class WebUserSmsSignInVm {

    @NotBlank(message = "手机号不可为空")
    @ApiModelProperty("手机号")
    private String phone;

    @NotBlank(message = "短信验证码不可为空")
    @ApiModelProperty("短信验证码")
    private String smsCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
