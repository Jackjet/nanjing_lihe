package d1.project.nanjing.liuhe.unify.manage.model;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.RegexpConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 发送短信
 * @author wkk
 **/
@ApiModel(value = "PublicSendSmsVm", description = "发送短信")
public class PublicSendSmsVm {
    /**
     * 手机号
     **/
    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不可为空")
    @Pattern(regexp = RegexpConstant.PHONE, message = "手机号格式不正确")
    private String phone;

    /**
     * 轮播图ID
     **/
    @ApiModelProperty("短信Token：手机号+tokenKey 生成MD5")
    @NotBlank(message = "smsToken不可为空")
    private String smsToken;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsToken() {
        return smsToken;
    }

    public void setSmsToken(String smsToken) {
        this.smsToken = smsToken;
    }
}
