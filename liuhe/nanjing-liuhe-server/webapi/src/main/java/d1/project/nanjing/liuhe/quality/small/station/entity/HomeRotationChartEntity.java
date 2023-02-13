package d1.project.nanjing.liuhe.quality.small.station.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 首页轮播图
 * @author wkk
 **/
@Entity
@Table(name = "d1_home_rotation_char")
@ApiModel(value = "HomeRotationChar", description = "首页轮播图")
public class HomeRotationChartEntity extends BaseCreateAndUpdateEntity {
    /**
     * 名称
     **/
    @ApiModelProperty("名称")
    @Column(length = 100)
    private String name;
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
     * 禁用，启用
     **/
    @ApiModelProperty("禁用，启用")
    @Column(length = 10)
    private String enable;
    /**
     * 跳转页面
     **/
    @ApiModelProperty("跳转页面")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
