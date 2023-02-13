package d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 法人信息
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_enterprise_legal_person")
@ApiModel(value = "EnterpriseLegalPerson", description = "法人信息")
public class EnterpriseLegalPersonEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("用户账户ID")
    @Column(length = 36)
    private String webUserId;

    @ApiModelProperty("法人姓名")
    @Column(length = 100)
    private String legalPersonName;

    @ApiModelProperty("证件类型")
    @Column(length = 50)
    private String idCardType;

    @ApiModelProperty("证件号码")
    @Column(length = 20)
    private String idCardCode;

    @ApiModelProperty("手机号")
    @Column(length = 20)
    private String phone;

    public String getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(String webUserId) {
        this.webUserId = webUserId;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardCode() {
        return idCardCode;
    }

    public void setIdCardCode(String idCardCode) {
        this.idCardCode = idCardCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
