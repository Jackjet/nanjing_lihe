package d1.project.nanjing.liuhe.system.model.vm;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.system.model.MenuTreeBase;

/**
 * 新增菜单
 *
 * @author kikki
 * @date 2020-09-10 14:43
 */
@ApiModel(value = "MenuTreeInsertVm", description = "新增菜单")
public class MenuTreeInsertVm extends MenuTreeBase {

    @ApiModelProperty("父级id")
    private String parentId;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
