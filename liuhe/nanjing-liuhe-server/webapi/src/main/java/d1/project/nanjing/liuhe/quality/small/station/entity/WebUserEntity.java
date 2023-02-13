package d1.project.nanjing.liuhe.quality.small.station.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 质量小站用户
 * @author wkk
 **/
@Entity
@Table(name = "d1_zlxz_web_user")
@ApiModel(value = "WebUser", description = "质量小站用户")
public class WebUserEntity extends BaseCreateAndUpdateEntity {
    /**
     * 手机号
     **/
    @ApiModelProperty("手机号")
    @Column(length = 20)
    private String phone;
    /**
     * 密码
     **/
    @ApiModelProperty("密码")
    @Column(length = 50)
    private String password;
    /**
     * 0:禁用，1:启用
     **/
    @ApiModelProperty("0:禁用，1:启用")
    @Column(length = 1)
    private Integer enable;

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

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
