package d1.project.nanjing.liuhe.government.enterprise.interact.model.ContactYellowPage;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;

/**
 * 联系人黄页-更新
 * @author wkk
 **/
@ApiModel(value = "ContactYellowPageUpdateVm", description = "联系人黄页-更新")
public class ContactYellowPageUpdateVm extends BaseIdVm {

    @ApiModelProperty("姓名")
    @NotBlank(message = "姓名不可为空")
    private String name;

    @ApiModelProperty("部门ID")
    @NotBlank(message = "部门ID不可为空")
    private String organizationId;

    @ApiModelProperty("部门名称")
    @NotBlank(message = "部门名称不可为空")
    private String organizationName;

    @ApiModelProperty("联系方式")
    @NotBlank(message = "联系方式不可为空")
    private String liaisonTel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLiaisonTel() {
        return liaisonTel;
    }

    public void setLiaisonTel(String liaisonTel) {
        this.liaisonTel = liaisonTel;
    }
}
