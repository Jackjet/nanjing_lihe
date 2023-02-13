package d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 投资人信息
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_enterprise_investor")
@ApiModel(value = "EnterpriseInvestor", description = "投资人信息")
public class EnterpriseInvestorEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("用户账户ID")
    @Column(length = 36)
    private String webUserId;

    @ApiModelProperty("投资者姓名")
    @Column(length = 50)
    private String investorName;

    @ApiModelProperty("投资金额（万元）")
    @Column(length = 50)
    private String money;

    @ApiModelProperty("投资比例")
    @Column(length = 50)
    private String proportion;

    public String getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(String webUserId) {
        this.webUserId = webUserId;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }
}
