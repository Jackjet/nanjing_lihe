package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 消息管理
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_message_management")
@ApiModel(value = "MessageManagement", description = "消息管理")
public class MessageManagementEntity extends BaseCreateEntity {

    @ApiModelProperty("标题")
    @Column(length = 100)
    private String title;

    @ApiModelProperty("内容")
    @Column(columnDefinition = "TEXT")
    private String content;

    @ApiModelProperty("接收者IDS")
    @Column(columnDefinition = "TEXT")
    private String receiveIds;

    @ApiModelProperty("来源")
    @Column(length = 100)
    private String source;

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

    public String getReceiveIds() {
        return receiveIds;
    }

    public void setReceiveIds(String receiveIds) {
        this.receiveIds = receiveIds;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
