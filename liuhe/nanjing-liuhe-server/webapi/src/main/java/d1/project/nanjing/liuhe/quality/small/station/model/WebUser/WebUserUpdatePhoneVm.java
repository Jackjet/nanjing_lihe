package d1.project.nanjing.liuhe.quality.small.station.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "WebUserUpdatePhoneVm", description = "修改手机号")
public class WebUserUpdatePhoneVm {

    /**
     * 新手机号
     **/
    @NotBlank(message = "新手机号不可为空")
    @ApiModelProperty("新手机号")
    private String phone;

    /**
     * 短信验证码
     **/
    @NotBlank(message = "短信验证码无效")
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
