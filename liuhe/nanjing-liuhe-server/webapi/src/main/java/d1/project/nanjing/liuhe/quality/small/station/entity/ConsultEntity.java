package d1.project.nanjing.liuhe.quality.small.station.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 我要咨询
 * @author wkk
 **/
@Entity
@Table(name = "d1_consult")
@ApiModel(value = "Consult", description = "我要咨询")
public class ConsultEntity extends BaseCreateAndUpdateEntity {
    /**
     * 企业名称
     **/
    @ApiModelProperty("企业名称")
    @Column(length = 100)
    private String enterpriseName;
    /**
     * 联系人姓名
     **/
    @ApiModelProperty("联系人姓名")
    @Column(length = 100)
    private String liaisonName;
    /**
     * 联系人电话
     **/
    @ApiModelProperty("联系人电话")
    @Column(length = 100)
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
    private String title;
    /**
     * 留言
     **/
    @ApiModelProperty("留言")
    @Column(columnDefinition = "TEXT")
    private String leavingMessage;
    /**
     * 回复状态：待回复、已回复
     **/
    @ApiModelProperty("回复状态：待回复、已回复")
    @Column(length = 10)
    private String replyStatus;
    /**
     * 图片,多个逗号隔开
     **/
    @ApiModelProperty("图片,多个逗号隔开")
    @Column(columnDefinition = "TEXT")
    private String imagesPahts;

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
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

    public String getLeavingMessage() {
        return leavingMessage;
    }

    public void setLeavingMessage(String leavingMessage) {
        this.leavingMessage = leavingMessage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
    }

    public String getLiaisonName() {
        return liaisonName;
    }

    public void setLiaisonName(String liaisonName) {
        this.liaisonName = liaisonName;
    }

    public String getImagesPahts() {
        return imagesPahts;
    }

    public void setImagesPahts(String imagesPahts) {
        this.imagesPahts = imagesPahts;
    }
}
