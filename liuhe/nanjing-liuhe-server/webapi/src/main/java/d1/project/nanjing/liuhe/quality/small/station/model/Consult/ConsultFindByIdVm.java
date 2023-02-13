package d1.project.nanjing.liuhe.quality.small.station.model.Consult;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.quality.small.station.entity.ConsultReplyEntity;

import java.util.Calendar;
import java.util.List;

/**
 * 我要咨询-详情
 * @author wkk
 **/
@ApiModel(value = "ConsultFindByIdVm", description = "我要咨询-详情")
public class ConsultFindByIdVm {
    /**
     * 企业名称
     **/
    @ApiModelProperty("企业名称")
    private String enterpriseName;
    /**
     * 联系人姓名
     **/
    @ApiModelProperty("联系人姓名")
    private String liaisonName;
    /**
     * 联系人电话
     **/
    @ApiModelProperty("联系人电话")
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
    private String leavingMessage;
    /**
     * 回复状态：待回复、已回复
     **/
    @ApiModelProperty("回复状态：待回复、已回复")
    private String replyStatus;
    /**
     * 图片,多个逗号隔开
     **/
    @ApiModelProperty("图片,多个逗号隔开")
    private String imagesPahts;
    /**
     * 咨询时间
     **/
    @ApiModelProperty("咨询时间")
    private Calendar createTime;
    /**
     * 历史回复
     **/
    @ApiModelProperty("历史回复")
    private List<ConsultReplyEntity> replyList;

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

    public String getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
    }

    public String getImagesPahts() {
        return imagesPahts;
    }

    public void setImagesPahts(String imagesPahts) {
        this.imagesPahts = imagesPahts;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public List<ConsultReplyEntity> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ConsultReplyEntity> replyList) {
        this.replyList = replyList;
    }
}
