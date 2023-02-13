package d1.project.nanjing.liuhe.quality.small.station.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "WebUserVerificationPwdVm", description = "验证账号密码")
public class WebUserVerificationPwdVm {

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
