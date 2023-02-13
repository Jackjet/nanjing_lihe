package d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 产业科技类
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_enterprise_industrial_technology")
@ApiModel(value = "EnterpriseIndustrialTechnology", description = "产业科技类")
public class EnterpriseIndustrialTechnologyEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("用户账户ID")
    @Column(length = 36)
    private String webUserId;

    @ApiModelProperty("主营业务（产品介绍）")
    @Column(length = 100)
    private String mainBusiness;

    @ApiModelProperty("产业领域")
    @Column(length = 50)
    private String industryField;

    @ApiModelProperty("可否提供产品展示")
    @Column(length = 10)
    private String isProductDisplay;

    @ApiModelProperty("常见用途")
    private String commonUse;

    @ApiModelProperty("有无进出口业务")
    @Column(length = 100)
    private String exportBusiness;

    @ApiModelProperty("其中出口业务占主营业务比重")
    @Column(length = 100)
    private String businessProportion;

    @ApiModelProperty("商标认证")
    @Column(length = 100)
    private String trademarkCertification;

    @ApiModelProperty("瞪羚企业")
    @Column(length = 100)
    private String gazelles;

    @ApiModelProperty("拥有专利数")
    @Column(length = 5)
    private int patentedCount;

    @ApiModelProperty("其中发明专利数")
    @Column(length = 5)
    private int inventionPatentCount;

    @ApiModelProperty("实用新型专利数")
    @Column(length = 5)
    private int utilityModelPatentCount;

    @ApiModelProperty("知识产权管理体系认证")
    @Column(length = 50)
    private String intellectualProperty;

    @ApiModelProperty("企业技术中心")
    @Column(length = 50)
    private String technologyCenter;

    @ApiModelProperty("工程研究中心")
    @Column(length = 50)
    private Integer engineeringResearchCenters;

    @ApiModelProperty("工程技术研究中心")
    @Column(length = 50)
    private Integer engineeringResearchCenter;

    @ApiModelProperty("获得省级及以上荣誉")
    @Column(length = 50)
    private Integer provincialHonors;

    @ApiModelProperty("领军人才数")
    @Column(length = 50)
    private String leadingTalentsCount;

    @ApiModelProperty("知识产权管理体系认证")
    @Column(length = 50)
    private String intellectualPropertyIdentification;

    @ApiModelProperty("PCT数")
    @Column(length = 50)
    private String pctCount;

    @ApiModelProperty("高新技术企业")
    @Column(length = 50)
    private String highTechTechnology;

    @ApiModelProperty("规上企业")
    @Column(length = 50)
    private String gauge;

    @ApiModelProperty("专精特新企业")
    @Column(length = 50)
    private String mastery;

    @ApiModelProperty("科技型中小企业")
    @Column(length = 50)
    private String technologySize;

    @ApiModelProperty("科技创新投入，是列表数据存放json格式")
    @Column(columnDefinition = "TEXT")
    private String technologyInnovateInvestmentJson;

    public String getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(String webUserId) {
        this.webUserId = webUserId;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public String getIndustryField() {
        return industryField;
    }

    public void setIndustryField(String industryField) {
        this.industryField = industryField;
    }

    public String getIsProductDisplay() {
        return isProductDisplay;
    }

    public void setIsProductDisplay(String isProductDisplay) {
        this.isProductDisplay = isProductDisplay;
    }

    public String getCommonUse() {
        return commonUse;
    }

    public void setCommonUse(String commonUse) {
        this.commonUse = commonUse;
    }

    public String getExportBusiness() {
        return exportBusiness;
    }

    public void setExportBusiness(String exportBusiness) {
        this.exportBusiness = exportBusiness;
    }

    public String getBusinessProportion() {
        return businessProportion;
    }

    public void setBusinessProportion(String businessProportion) {
        this.businessProportion = businessProportion;
    }

    public String getTrademarkCertification() {
        return trademarkCertification;
    }

    public void setTrademarkCertification(String trademarkCertification) {
        this.trademarkCertification = trademarkCertification;
    }

    public String getGazelles() {
        return gazelles;
    }

    public void setGazelles(String gazelles) {
        this.gazelles = gazelles;
    }

    public int getPatentedCount() {
        return patentedCount;
    }

    public void setPatentedCount(int patentedCount) {
        this.patentedCount = patentedCount;
    }

    public int getInventionPatentCount() {
        return inventionPatentCount;
    }

    public void setInventionPatentCount(int inventionPatentCount) {
        this.inventionPatentCount = inventionPatentCount;
    }

    public int getUtilityModelPatentCount() {
        return utilityModelPatentCount;
    }

    public void setUtilityModelPatentCount(int utilityModelPatentCount) {
        this.utilityModelPatentCount = utilityModelPatentCount;
    }

    public String getIntellectualProperty() {
        return intellectualProperty;
    }

    public void setIntellectualProperty(String intellectualProperty) {
        this.intellectualProperty = intellectualProperty;
    }

    public String getTechnologyCenter() {
        return technologyCenter;
    }

    public void setTechnologyCenter(String technologyCenter) {
        this.technologyCenter = technologyCenter;
    }

    public Integer getEngineeringResearchCenters() {
        return engineeringResearchCenters;
    }

    public void setEngineeringResearchCenters(Integer engineeringResearchCenters) {
        this.engineeringResearchCenters = engineeringResearchCenters;
    }

    public Integer getEngineeringResearchCenter() {
        return engineeringResearchCenter;
    }

    public void setEngineeringResearchCenter(Integer engineeringResearchCenter) {
        this.engineeringResearchCenter = engineeringResearchCenter;
    }

    public Integer getProvincialHonors() {
        return provincialHonors;
    }

    public void setProvincialHonors(Integer provincialHonors) {
        this.provincialHonors = provincialHonors;
    }

    public String getLeadingTalentsCount() {
        return leadingTalentsCount;
    }

    public void setLeadingTalentsCount(String leadingTalentsCount) {
        this.leadingTalentsCount = leadingTalentsCount;
    }

    public String getIntellectualPropertyIdentification() {
        return intellectualPropertyIdentification;
    }

    public void setIntellectualPropertyIdentification(String intellectualPropertyIdentification) {
        this.intellectualPropertyIdentification = intellectualPropertyIdentification;
    }

    public String getPctCount() {
        return pctCount;
    }

    public void setPctCount(String pctCount) {
        this.pctCount = pctCount;
    }

    public String getHighTechTechnology() {
        return highTechTechnology;
    }

    public void setHighTechTechnology(String highTechTechnology) {
        this.highTechTechnology = highTechTechnology;
    }

    public String getGauge() {
        return gauge;
    }

    public void setGauge(String gauge) {
        this.gauge = gauge;
    }

    public String getMastery() {
        return mastery;
    }

    public void setMastery(String mastery) {
        this.mastery = mastery;
    }

    public String getTechnologySize() {
        return technologySize;
    }

    public void setTechnologySize(String technologySize) {
        this.technologySize = technologySize;
    }

    public String getTechnologyInnovateInvestmentJson() {
        return technologyInnovateInvestmentJson;
    }

    public void setTechnologyInnovateInvestmentJson(String technologyInnovateInvestmentJson) {
        this.technologyInnovateInvestmentJson = technologyInnovateInvestmentJson;
    }
}
