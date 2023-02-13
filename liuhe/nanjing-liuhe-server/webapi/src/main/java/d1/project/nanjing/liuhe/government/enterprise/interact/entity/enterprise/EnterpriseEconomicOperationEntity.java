package d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 经济运营服务类（万元）
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_enterprise_economic_operation")
@ApiModel(value = "EnterpriseEconomicOperation", description = "经济运营服务类（万元）")
public class EnterpriseEconomicOperationEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("用户账户ID")
    @Column(length = 36)
    private String webUserId;

    @ApiModelProperty("年度")
    @Column(length = 4)
    private int unifiedCreditCode;

    @ApiModelProperty("销售收入")
    @Column(length = 30)
    private String salesRevenue;

    @ApiModelProperty("税收")
    @Column(length = 30)
    private String taxation;

    @ApiModelProperty("外贸进出口")
    @Column(length = 30)
    private String foreignTrade;

    @ApiModelProperty("研发费用")
    @Column(length = 30)
    private String yanfaCost;

    @ApiModelProperty("技术合同交易")
    private String contract;

    @ApiModelProperty("备注")
    private String scale;

    public String getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(String webUserId) {
        this.webUserId = webUserId;
    }

    public int getUnifiedCreditCode() {
        return unifiedCreditCode;
    }

    public void setUnifiedCreditCode(int unifiedCreditCode) {
        this.unifiedCreditCode = unifiedCreditCode;
    }

    public String getSalesRevenue() {
        return salesRevenue;
    }

    public void setSalesRevenue(String salesRevenue) {
        this.salesRevenue = salesRevenue;
    }

    public String getTaxation() {
        return taxation;
    }

    public void setTaxation(String taxation) {
        this.taxation = taxation;
    }

    public String getForeignTrade() {
        return foreignTrade;
    }

    public void setForeignTrade(String foreignTrade) {
        this.foreignTrade = foreignTrade;
    }

    public String getYanfaCost() {
        return yanfaCost;
    }

    public void setYanfaCost(String yanfaCost) {
        this.yanfaCost = yanfaCost;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
