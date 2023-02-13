package d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * 许可证信息
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_enterprise_licence")
@ApiModel(value = "EnterpriseLicence", description = "许可证信息")
public class EnterpriseLicenceEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("用户账户ID")
    @Column(length = 36)
    private String webUserId;

    @ApiModelProperty("许可证名称")
    @Column(length = 100)
    private String licenceName;

    @ApiModelProperty("许可证编号")
    @Column(length = 50)
    private String licenceCode;

    @ApiModelProperty("发证日期")
    @Temporal(TemporalType.DATE)
    private Calendar issueCertificatesDate;

    @ApiModelProperty("有效日期")
    @Temporal(TemporalType.DATE)
    private Calendar effectiveDate;

    @ApiModelProperty("证件图片")
    private String imagesPaht;

    public String getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(String webUserId) {
        this.webUserId = webUserId;
    }

    public String getLicenceName() {
        return licenceName;
    }

    public void setLicenceName(String licenceName) {
        this.licenceName = licenceName;
    }

    public String getLicenceCode() {
        return licenceCode;
    }

    public void setLicenceCode(String licenceCode) {
        this.licenceCode = licenceCode;
    }

    public Calendar getIssueCertificatesDate() {
        return issueCertificatesDate;
    }

    public void setIssueCertificatesDate(Calendar issueCertificatesDate) {
        this.issueCertificatesDate = issueCertificatesDate;
    }

    public Calendar getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Calendar effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getImagesPaht() {
        return imagesPaht;
    }

    public void setImagesPaht(String imagesPaht) {
        this.imagesPaht = imagesPaht;
    }
}
