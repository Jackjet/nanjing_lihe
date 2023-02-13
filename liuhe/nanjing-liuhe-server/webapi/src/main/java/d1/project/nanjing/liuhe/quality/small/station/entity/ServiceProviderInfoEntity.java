package d1.project.nanjing.liuhe.quality.small.station.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * 服务商信息
 * @author wkk
 **/
@Entity
@Table(name = "d1_service_provider_info")
@ApiModel(value = "ServiceProviderInfo", description = "服务商信息")
public class ServiceProviderInfoEntity extends BaseCreateAndUpdateEntity {
    /**
     * 企业名称
     **/
    @ApiModelProperty("企业名称")
    private String title;
    /**
     * 简介
     **/
    @ApiModelProperty("简介")
    private String introduce;
    /**
     * 内容
     **/
    @ApiModelProperty("内容")
    @Column(columnDefinition = "TEXT")
    private String content;
    /**
     * 序号
     **/
    @ApiModelProperty("序号")
    @Column(length = 3)
    private Integer sortNumber;
    /**
     * 图片
     **/
    @ApiModelProperty("图片")
    private String imagesPaht;
    /**
     * 启用，禁用
     **/
    @ApiModelProperty("启用，禁用")
    @Column(length = 10)
    private String enable;
    /**
     * 发布日期
     **/
    @ApiModelProperty(value = "发布日期", example = "2021-01-01")
    @Temporal(TemporalType.DATE)
    private Calendar releaseTime;

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

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Calendar getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Calendar releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
