package d1.project.nanjing.liuhe.quality.small.station.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 用户注册
 * @author wkk
 **/
@ApiModel(value = "WebUserInsertVm", description = "用户注册")
public class WebUserInsertVm {

    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不可为空")
    private String phone;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不可为空")
    private String password;

    @ApiModelProperty("企业名称")
    @NotBlank(message = "企业名称不可为空")
    private String enterpriseName;

    @ApiModelProperty("联系人")
    @NotBlank(message = "联系人不可为空")
    private String liaisonName;

    @ApiModelProperty("联系电话")
    @NotBlank(message = "联系电话不可为空")
    private String liaisonTel;

    @ApiModelProperty("省")
    @NotBlank(message = "省不可为空")
    private String province;

    @ApiModelProperty("市")
    @NotBlank(message = "市不可为空")
    private String city;

    @ApiModelProperty("区")
    @NotBlank(message = "区不可为空")
    private String area;

    @ApiModelProperty("详细地址")
    @NotBlank(message = "详细地址不可为空")
    private String address;

    @ApiModelProperty("短信验证码")
    @NotBlank(message = "短信验证码无效")
    private String smsCode;

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

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getLiaisonName() {
        return liaisonName;
    }

    public void setLiaisonName(String liaisonName) {
        this.liaisonName = liaisonName;
    }

    public String getLiaisonTel() {
        return liaisonTel;
    }

    public void setLiaisonTel(String liaisonTel) {
        this.liaisonTel = liaisonTel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
