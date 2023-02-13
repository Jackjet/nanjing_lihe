package d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * 协议签订情况
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_enterprise_agreement_signing")
@ApiModel(value = "EnterpriseAgreementSigning", description = "协议签订情况")
public class EnterpriseAgreementSigningEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("用户账户ID")
    @Column(length = 36)
    private String webUserId;

    @ApiModelProperty("协议签订时间")
    @Temporal(TemporalType.DATE)
    private Calendar agreementSigningDate;

    @ApiModelProperty("协议用地面积（亩）")
    @Column(length = 30)
    private String landArea;

    @ApiModelProperty("协议总投资")
    @Column(length = 30)
    private String totalInvestment;

    @ApiModelProperty("协议约定土地价格")
    @Column(length = 30)
    private String landPrice;

    @ApiModelProperty("协议约定年产值")
    @Column(length = 30)
    private String annualOutputValue;

    @ApiModelProperty("协议约定年税收")
    @Column(length = 30)
    private String annualTax;

    public String getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(String webUserId) {
        this.webUserId = webUserId;
    }

    public Calendar getAgreementSigningDate() {
        return agreementSigningDate;
    }

    public void setAgreementSigningDate(Calendar agreementSigningDate) {
        this.agreementSigningDate = agreementSigningDate;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public String getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(String totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public String getLandPrice() {
        return landPrice;
    }

    public void setLandPrice(String landPrice) {
        this.landPrice = landPrice;
    }

    public String getAnnualOutputValue() {
        return annualOutputValue;
    }

    public void setAnnualOutputValue(String annualOutputValue) {
        this.annualOutputValue = annualOutputValue;
    }

    public String getAnnualTax() {
        return annualTax;
    }

    public void setAnnualTax(String annualTax) {
        this.annualTax = annualTax;
    }
}
