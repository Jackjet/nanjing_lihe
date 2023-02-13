package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * 轮播图
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_rotation_char")
@ApiModel(value = "RotationChart", description = "轮播图")
public class RotationChartEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("标题")
    @Column(length = 100)
    private String title;

    @ApiModelProperty("图片")
    private String imagesPaht;

    @ApiModelProperty("禁用，启用")
    @Column(length = 10)
    private String enable;

    @ApiModelProperty("跳转页面")
    private String url;

    @ApiModelProperty("配图文字")
    private String imagesContent;

    @ApiModelProperty(value = "发布时间", example = "2021-01-01 12:12:12")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar releaseTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagesPaht() {
        return imagesPaht;
    }

    public void setImagesPaht(String imagesPaht) {
        this.imagesPaht = imagesPaht;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImagesContent() {
        return imagesContent;
    }

    public void setImagesContent(String imagesContent) {
        this.imagesContent = imagesContent;
    }

    public Calendar getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Calendar releaseTime) {
        this.releaseTime = releaseTime;
    }
}
