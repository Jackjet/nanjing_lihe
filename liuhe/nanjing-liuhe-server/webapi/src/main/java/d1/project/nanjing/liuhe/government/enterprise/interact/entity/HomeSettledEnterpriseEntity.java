package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 首页-入驻企业
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_home_settled_enterprise")
@ApiModel(value = "HomeSettledEnterprise", description = "首页-入驻企业")
public class HomeSettledEnterpriseEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("企业ID")
    @Column(length = 32)
    private String enterpriseId;

    @ApiModelProperty("企业名称")
    @Column(length = 100)
    private String enterpriseName;

    @ApiModelProperty("企业LOGO")
    private String logo;

    @ApiModelProperty("企业网站")
    private String url;

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
