package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 日常服务项
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_daily_service_items")
@ApiModel(value = "DailyServiceItems", description = "日常服务项")
public class DailyServiceItemsEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("服务ID")
    @Column(length = 32)
    private String serviceId;

    @ApiModelProperty("名称")
    @Column(length = 100)
    private String name;

    @ApiModelProperty("图片")
    private String imagesPaht;

    @ApiModelProperty("详情ID")
    @Column(length = 32)
    private String detailId;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagesPaht() {
        return imagesPaht;
    }

    public void setImagesPaht(String imagesPaht) {
        this.imagesPaht = imagesPaht;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }
}
