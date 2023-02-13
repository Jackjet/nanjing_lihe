package d1.project.nanjing.liuhe.quality.small.station.model.ServiceProviderInfo;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

/**
 * 服务商信息-分页查询
 * @author wkk
 **/
@ApiModel(value = "ServiceProviderInfoFindVm", description = "服务商信息-分页查询")
public class ServiceProviderInfoFindVm extends PageableVm {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("分类:平台动态，行业新闻，法规文件与解读，通知公告")
    private String type;

    @ApiModelProperty("禁用，启用")
    private String enable;

    @ApiModelProperty("开始日期：2021-01-01")
    private String startDate;

    @ApiModelProperty("结束日期：2021-01-01")
    private String endDate;

    @ApiModelProperty("创建人")
    private String createById;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

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

    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }
}
