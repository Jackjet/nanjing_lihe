package d1.project.nanjing.liuhe.government.enterprise.interact.view;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseEntity;


/**
 * 常见问题
 * @author wkk
 **/
@ApiModel(value = "CommonProblem", description = "常见问题")
public class CommonProblemView extends BaseEntity {

    @ApiModelProperty("标题")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CommonProblemView(String id,String title) {
        super.setId(id);
        this.title = title;
    }
}
