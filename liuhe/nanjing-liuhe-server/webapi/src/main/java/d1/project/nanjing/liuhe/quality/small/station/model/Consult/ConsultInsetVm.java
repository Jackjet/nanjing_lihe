package d1.project.nanjing.liuhe.quality.small.station.model.Consult;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 我要咨询-新增
 * @author wkk
 **/
@ApiModel(value = "ConsultInsetVm", description = "我要咨询-新增")
public class ConsultInsetVm {
    /**
     * 企业名称
     **/
    @ApiModelProperty("企业名称")
    @NotBlank(message = "企业名称不可为空")
    private String enterpriseName;
    /**
     * 联系人姓名
     **/
    @ApiModelProperty("联系人姓名")
    @NotBlank(message = "联系人姓名不可为空")
    private String liaisonName;
    /**
     * 联系人电话
     **/
    @ApiModelProperty("联系人电话")
    @NotBlank(message = "联系人电话不可为空")
    private String liaisonTel;
    /**
     * 详细地址
     **/
    @ApiModelProperty("详细地址")
    private String address;
    /**
     * 标题
     **/
    @ApiModelProperty("标题")
    @NotBlank(message = "标题不可为空")
    private String title;
    /**
     * 留言
     **/
    @ApiModelProperty("留言")
    private String leavingMessage;
    /**
     * 图片,多个逗号隔开
     **/
    @ApiModelProperty("图片,多个逗号隔开")
    private String imagesPahts;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getLiaisonName() {
        return liaisonName;
    }

    public void setLiaisonName(String liaisonName) {
        this.liaisonName = liaisonName;
    }

    public String getLiaisonTel() {
        return liaisonTel;
    }

    public void setLiaisonTel(String liaisonTel) {
        this.liaisonTel = liaisonTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLeavingMessage() {
        return leavingMessage;
    }

    public void setLeavingMessage(String leavingMessage) {
        this.leavingMessage = leavingMessage;
    }

    public String getImagesPahts() {
        return imagesPahts;
    }

    public void setImagesPahts(String imagesPahts) {
        this.imagesPahts = imagesPahts;
    }
}
