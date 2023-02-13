package d1.project.nanjing.liuhe.government.enterprise.interact.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseCreateAndUpdateEntity;

import javax.persistence.*;
import java.util.Calendar;

/**
 * 常见问题
 * @author wkk
 **/
@Entity
@Table(name = "d1_zqhd_common_problem")
@ApiModel(value = "CommonProblem", description = "常见问题")
public class CommonProblemEntity extends BaseCreateAndUpdateEntity {

    @ApiModelProperty("标题")
    @Column(length = 100)
    private String title;

    @ApiModelProperty("禁用，启用")
    @Column(length = 10)
    private String enable;

    @ApiModelProperty("分类")
    @Column(length = 50)
    private String type;

    @ApiModelProperty(value = "发布时间", example = "2021-01-01 12:12:12")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar releaseTime;

    @ApiModelProperty("内容")
    @Column(columnDefinition = "TEXT")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Calendar getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Calendar releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
