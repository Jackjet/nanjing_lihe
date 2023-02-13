package d1.project.nanjing.liuhe.quality.small.station.model.NewsAnnouncement;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;

/**
 * 新闻公告-发布
 * @author wkk
 **/
@ApiModel(value = "NewsAnnouncementReleaseVm", description = "新闻公告-发布")
public class NewsAnnouncementReleaseVm extends BaseIdVm {

    @ApiModelProperty("发布状态")
    @NotBlank(message = "发布状态不可为空")
    private String enable;

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
