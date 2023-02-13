package d1.project.nanjing.liuhe.government.enterprise.interact.model.HomeSettledEnterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

/**
 * 首页入驻企业-新增
 * @author wkk
 **/
@ApiModel(value = "HomeSettledEnterpriseInsetVm", description = "首页入驻企业-新增")
public class HomeSettledEnterpriseInsetVm {

    @ApiModelProperty("企业ID")
    private String enterpriseId;

    @ApiModelProperty("企业名称")
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
