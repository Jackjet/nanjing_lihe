package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 政企互动用户
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_web_user")
@ApiModel(value = "ZqhdWebUser", description = "政企互动用户")
public class ZqhdWebUserEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("手机号")
    @Column(length = 20)
    private String phone;

    @ApiModelProperty("密码")
    @Column(length = 50)
    private String password;

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
