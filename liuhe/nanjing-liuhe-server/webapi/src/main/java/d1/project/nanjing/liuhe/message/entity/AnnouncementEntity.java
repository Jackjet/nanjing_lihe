package d1.project.nanjing.liuhe.message.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 通知公告
 *
 * @author kikki
 * @date 2020-11-19 16:02
 */
@Entity
@Table(name = "d1_announcement_entity")
@ApiModel(value = "AnnouncementEntity", description = "通知公告")
public class AnnouncementEntity extends BaseCreateAndUpdateEntity {
    /**
     * @mock @ctitle(0,100)
     */
    @ApiModelProperty("标题")
    @Column(length = 100)
    private String title;
    /**
     * @mock @cword(0,100)
     */
    @ApiModelProperty("内容")
    @Column(columnDefinition = "TEXT")
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
}
