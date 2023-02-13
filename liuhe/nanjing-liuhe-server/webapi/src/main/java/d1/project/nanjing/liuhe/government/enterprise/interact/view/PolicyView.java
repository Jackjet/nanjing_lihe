package d1.project.nanjing.liuhe.government.enterprise.interact.view;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import java.util.Calendar;

/**
 * 政策体系
 * @author wkk
 **/
@Entity
@Immutable
@Subselect("Select id, create_by_id, create_time, update_by_id, update_time, " +
        "enable, organization_id, organization_name, release_time, " +
        "title, type From d1_zqhd_policy")
@ApiModel(value = "Policy", description = "政策体系")
public class PolicyView extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("分类")
    private String type;

    @ApiModelProperty(value = "发布时间", example = "2021-01-01 12:12:12")
    private Calendar releaseTime;

    @ApiModelProperty("来源：部门ID")
    private String organizationId;

    @ApiModelProperty("来源：部门名称")
    private String organizationName;

    @ApiModelProperty("禁用，启用")
    private String enable;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Calendar getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Calendar releaseTime) {
        this.releaseTime = releaseTime;
    }
}
