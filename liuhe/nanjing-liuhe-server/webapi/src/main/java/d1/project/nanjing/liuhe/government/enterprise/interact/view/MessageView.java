package d1.project.nanjing.liuhe.government.enterprise.interact.view;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseEntity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Entity
@Immutable
@Subselect("SELECT mm.id, mm.receive_ids, mm.title, mm.source, mm.create_time, ms.status " +
        "FROM d1_zqhd_message_management mm INNER JOIN d1_zqhd_message_status ms ON mm.id = ms.message_id")
@ApiModel(value = "MessageView", description = "政企互动门户我的消息列表")
public class MessageView extends BaseEntity {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("来源")
    private String source;

    @ApiModelProperty("状态：已读、未读")
    private String status;

    @ApiModelProperty(value = "创建时间", example = "2018-01-01 01:01:01")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar createTime;

    @ApiModelProperty("接收者IDS")
    private String receiveIds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public String getReceiveIds() {
        return receiveIds;
    }

    public void setReceiveIds(String receiveIds) {
        this.receiveIds = receiveIds;
    }
}
