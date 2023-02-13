package d1.project.nanjing.liuhe.quality.small.station.model.Consult;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

/**
 * 我要咨询-分页查询
 * @author wkk
 **/
@ApiModel(value = "ConsultFindVm", description = "我要咨询-分页查询")
public class ConsultFindVm extends PageableVm {
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
     * 回复状态：待回复、已回复
     **/
    @ApiModelProperty("回复状态：待回复、已回复")
    private String replyStatus;
    /**
     * 开始日期：2021-01-01
     **/
    @ApiModelProperty("开始日期：2021-01-01")
    private String startDate;
    /**
     * 结束日期：2021-01-01
     **/
    @ApiModelProperty("结束日期：2021-01-01")
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

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

    public String getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
    }
}
