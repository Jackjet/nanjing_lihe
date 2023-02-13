package d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 党群信息类
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_enterprise_party_masses")
@ApiModel(value = "EnterprisePartyMasses", description = "党群信息类")
public class EnterprisePartyMassesEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("用户账户ID")
    @Column(length = 36)
    private String webUserId;

    @ApiModelProperty("用工人数：（其中参保人数）")
    @Column(length = 30)
    private String insuredNumber;

    @ApiModelProperty("其中本科以上人数")
    @Column(length = 30)
    private String benkeNumber;

    @ApiModelProperty("其中研究生人数")
    @Column(length = 30)
    private String yanjiushengNumber;

    @ApiModelProperty("其中外籍人员人数及国别")
    @Column(length = 30)
    private String waijiNumber;

    @ApiModelProperty("研发人员数")
    @Column(length = 30)
    private String yanfaNumber;

    @ApiModelProperty("研发人员缴纳社保人数")
    @Column(length = 30)
    private String yanfaPaySecurityNumber;

    @ApiModelProperty("硕士或副高以上人数")
    @Column(length = 30)
    private String shuoshiNumber;

    @ApiModelProperty("留学回国人员人数")
    @Column(length = 30)
    private String huiguoNumber;

    @ApiModelProperty("党员人数")
    @Column(length = 30)
    private String partyMemberNumber;

    @ApiModelProperty("是否有党支部")
    @Column(length = 30)
    private String isPartyBranch;

    @ApiModelProperty("团员人数")
    @Column(length = 30)
    private String leagueMemberNumber;

    @ApiModelProperty("是否有团支部")
    @Column(length = 30)
    private String isLeagueBranch;

    @ApiModelProperty("是否有工会组织")
    @Column(length = 30)
    private String isTradeUnion;

    @ApiModelProperty("是否有妇联组织")
    @Column(length = 30)
    private Integer isWomanOrganize;

    public String getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(String webUserId) {
        this.webUserId = webUserId;
    }

    public String getInsuredNumber() {
        return insuredNumber;
    }

    public void setInsuredNumber(String insuredNumber) {
        this.insuredNumber = insuredNumber;
    }

    public String getBenkeNumber() {
        return benkeNumber;
    }

    public void setBenkeNumber(String benkeNumber) {
        this.benkeNumber = benkeNumber;
    }

    public String getYanjiushengNumber() {
        return yanjiushengNumber;
    }

    public void setYanjiushengNumber(String yanjiushengNumber) {
        this.yanjiushengNumber = yanjiushengNumber;
    }

    public String getWaijiNumber() {
        return waijiNumber;
    }

    public void setWaijiNumber(String waijiNumber) {
        this.waijiNumber = waijiNumber;
    }

    public String getYanfaNumber() {
        return yanfaNumber;
    }

    public void setYanfaNumber(String yanfaNumber) {
        this.yanfaNumber = yanfaNumber;
    }

    public String getYanfaPaySecurityNumber() {
        return yanfaPaySecurityNumber;
    }

    public void setYanfaPaySecurityNumber(String yanfaPaySecurityNumber) {
        this.yanfaPaySecurityNumber = yanfaPaySecurityNumber;
    }

    public String getShuoshiNumber() {
        return shuoshiNumber;
    }

    public void setShuoshiNumber(String shuoshiNumber) {
        this.shuoshiNumber = shuoshiNumber;
    }

    public String getHuiguoNumber() {
        return huiguoNumber;
    }

    public void setHuiguoNumber(String huiguoNumber) {
        this.huiguoNumber = huiguoNumber;
    }

    public String getPartyMemberNumber() {
        return partyMemberNumber;
    }

    public void setPartyMemberNumber(String partyMemberNumber) {
        this.partyMemberNumber = partyMemberNumber;
    }

    public String getIsPartyBranch() {
        return isPartyBranch;
    }

    public void setIsPartyBranch(String isPartyBranch) {
        this.isPartyBranch = isPartyBranch;
    }

    public String getLeagueMemberNumber() {
        return leagueMemberNumber;
    }

    public void setLeagueMemberNumber(String leagueMemberNumber) {
        this.leagueMemberNumber = leagueMemberNumber;
    }

    public String getIsLeagueBranch() {
        return isLeagueBranch;
    }

    public void setIsLeagueBranch(String isLeagueBranch) {
        this.isLeagueBranch = isLeagueBranch;
    }

    public String getIsTradeUnion() {
        return isTradeUnion;
    }

    public void setIsTradeUnion(String isTradeUnion) {
        this.isTradeUnion = isTradeUnion;
    }

    public Integer getIsWomanOrganize() {
        return isWomanOrganize;
    }

    public void setIsWomanOrganize(Integer isWomanOrganize) {
        this.isWomanOrganize = isWomanOrganize;
    }
}
