package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 日常服务
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_daily_service")
@ApiModel(value = "DailyService", description = "日常服务")
public class DailyServiceEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("名称")
    @Column(length = 100)
    private String name;

    @ApiModelProperty("图片")
    private String imagesPaht;

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
}
