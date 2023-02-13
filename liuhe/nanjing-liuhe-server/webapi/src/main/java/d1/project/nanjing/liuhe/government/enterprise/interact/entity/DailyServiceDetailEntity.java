package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 日常服务详情
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_daily_service_detail")
@ApiModel(value = "DailyServiceDetail", description = "日常服务详情")
public class DailyServiceDetailEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("服务ID")
    @Column(length = 32)
    private String serviceId;

    @ApiModelProperty("服务项ID")
    @Column(length = 32)
    private String itemId;

    @ApiModelProperty("标题")
    @Column(length = 100)
    private String title;

    @ApiModelProperty("附件")
    @Column(columnDefinition = "TEXT")
    private String attachmentPath;

    @ApiModelProperty("详情")
    @Column(columnDefinition = "TEXT")
    private String content;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
