package d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 政务服务类
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_enterprise_government")
@ApiModel(value = "EnterpriseGovernment", description = "政务服务类")
public class EnterpriseGovernmentEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("用户账户ID")
    @Column(length = 36)
    private String webUserId;

    @ApiModelProperty("占地面积")
    @Column(length = 30)
    private String areaCovered;

    public String getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(String webUserId) {
        this.webUserId = webUserId;
    }

    public String getAreaCovered() {
        return areaCovered;
    }

    public void setAreaCovered(String areaCovered) {
        this.areaCovered = areaCovered;
    }
}
