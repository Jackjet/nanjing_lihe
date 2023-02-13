package d1.project.nanjing.liuhe.government.enterprise.interact.view;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseEntity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import java.util.Calendar;

/**
 * 线上课堂
 * @author wkk
 **/
@Entity
@Immutable
@Subselect("Select id, title, type,release_time,image_path, enable From d1_zqhd_online_classroom")
@ApiModel(value = "OnlineClassroom", description = "线上课堂")
public class OnlineClassroomView extends BaseEntity {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("分类")
    private String type;

    @ApiModelProperty(value = "发布时间", example = "2021-01-01 12:12:12")
    private Calendar releaseTime;

    @ApiModelProperty("视频封面图片")
    private String imagePath;

    @ApiModelProperty("启用、禁用")
    private String enable;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
