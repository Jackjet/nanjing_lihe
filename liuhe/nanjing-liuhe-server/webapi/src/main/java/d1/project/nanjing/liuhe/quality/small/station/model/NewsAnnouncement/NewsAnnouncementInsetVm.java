package d1.project.nanjing.liuhe.quality.small.station.model.NewsAnnouncement;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 新闻公告-新增
 * @author wkk
 **/
@ApiModel(value = "NewsAnnouncementInsetVm", description = "新闻公告-新增")
public class NewsAnnouncementInsetVm {
    /**
     * 标题
     **/
    @ApiModelProperty("标题")
    @NotBlank(message = "标题不可为空")
    private String title;
    /**
     * 分类:平台动态，行业新闻，法规文件与解读，通知公告
     **/
    @ApiModelProperty("分类:平台动态，行业新闻，法规文件与解读，通知公告")
    private String type;
    /**
     * 序号
     **/
    @ApiModelProperty("序号")
    private Integer sortNumber;
    /**
     * 图片路径
     **/
    @ApiModelProperty("图片路径")
    @NotBlank(message = "图片路径不可为空")
    private String imagesPaht;
    /**
     * 简介
     **/
    @ApiModelProperty("简介")
    private String introduce;
    /**
     * 内容
     **/
    @ApiModelProperty("内容")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getImagesPaht() {
        return imagesPaht;
    }

    public void setImagesPaht(String imagesPaht) {
        this.imagesPaht = imagesPaht;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
