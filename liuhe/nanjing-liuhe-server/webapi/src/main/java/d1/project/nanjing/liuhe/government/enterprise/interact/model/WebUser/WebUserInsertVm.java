package d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.RegexpConstant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Calendar;

@ApiModel(value = "WebUserInsertVm", description = "用户注册")
public class WebUserInsertVm {

    @NotBlank(message = "手机号不可为空")
    @Pattern(regexp = RegexpConstant.PHONE, message = "手机号格式不正确")
    @ApiModelProperty("手机号")
    private String phone;

    @NotBlank(message = "密码不可为空")
    @ApiModelProperty("密码")
    private String password;

    @NotBlank(message = "统一信用代码不可为空")
    @ApiModelProperty("统一信用代码")
    private String unifiedCreditCode;

    @NotBlank(message = "企业名称不可为空")
    @ApiModelProperty("企业名称")
    private String enterpriseName;

    @NotBlank(message = "联系人不可为空")
    @ApiModelProperty("联系人")
    private String liaisonName;

    @NotBlank(message = "联系人电话不可为空")
    @ApiModelProperty("联系人电话")
    private String liaisonTel;

    @NotBlank(message = "邮箱不可为空")
    @Pattern(regexp = RegexpConstant.EMAIL, message = "邮箱格式不正确")
    @ApiModelProperty("邮箱")
    private String email;

    @NotBlank(message = "行业不可为空")
    @ApiModelProperty("行业")
    private String industry;

    @NotBlank(message = "规模不可为空")
    @ApiModelProperty("规模")
    private String scale;

    @NotNull(message = "成立日期不可为空")
    @ApiModelProperty(value = "成立日期", example = "2021-01-01")
    private Calendar establishmentDate;

    @NotBlank(message = "从业人员不可为空")
    @ApiModelProperty("从业人员")
    private String employeesSize;

    @NotBlank(message = "企业简介不可为空")
    @ApiModelProperty("企业简介255个字符")
    private String briefIntroduction;

    @NotBlank(message = "注册地址不可为空")
    @ApiModelProperty("注册地址255个字符")
    private String address;

    @NotBlank(message = "注册资本不可为空")
    @ApiModelProperty("注册资本")
    private String registeredCapital;

    @ApiModelProperty("短信验证码")
    @NotBlank(message = "短信验证码无效")
    private String smsCode;

    @ApiModelProperty("企业类型")
    private String type;

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

    public String getUnifiedCreditCode() {
        return unifiedCreditCode;
    }

    public void setUnifiedCreditCode(String unifiedCreditCode) {
        this.unifiedCreditCode = unifiedCreditCode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public Calendar getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Calendar establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public String getEmployeesSize() {
        return employeesSize;
    }

    public void setEmployeesSize(String employeesSize) {
        this.employeesSize = employeesSize;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
