package d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "WebUserRetrievePasswordVm", description = "找回密码")
public class WebUserRetrievePasswordVm {

    @NotBlank(message = "手机号不可为空")
    @ApiModelProperty("手机号")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
