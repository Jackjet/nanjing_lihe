package d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonProblem;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.common.model.BaseIdVm;

import javax.validation.constraints.NotBlank;

/**
 * 常见问题-更新
 * @author wkk
 **/
@ApiModel(value = "CommonProblemUpdateVm", description = "常见问题-更新")
public class CommonProblemUpdateVm extends BaseIdVm {

    @ApiModelProperty("标题")
    @NotBlank(message = "标题不可为空")
    private String title;

    @ApiModelProperty("分类")
    @NotBlank(message = "分类不可为空")
    private String type;

    @ApiModelProperty("详情")
    @NotBlank(message = "详情不可为空")
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
