package d1.project.nanjing.liuhe.quality.small.station.model.NewsAnnouncement;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.model.PageableVm;

/**
 * 新闻公告-分页查询
 * @author wkk
 **/
@ApiModel(value = "NewsAnnouncementFindVm", description = "新闻公告-分页查询")
public class NewsAnnouncementFindVm extends PageableVm {
    /**
     * 标题
     **/
    @ApiModelProperty("标题")
    private String title;
    /**
     * 分类:平台动态，行业新闻，法规文件与解读，通知公告
     **/
    @ApiModelProperty("分类:平台动态，行业新闻，法规文件与解读，通知公告")
    private String type;
    /**
     * 禁用，启用
     **/
    @ApiModelProperty("禁用，启用")
    private String enable;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
