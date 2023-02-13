package d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "WebUserRetrievePasswordVm", description = "重置密码")
public class WebUserResetPasswordVm {

    @NotBlank(message = "手机号不可为空")
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 临时Token
     **/
    @NotBlank(message = "临时Token不可为空")
    @ApiModelProperty("临时Token")
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
