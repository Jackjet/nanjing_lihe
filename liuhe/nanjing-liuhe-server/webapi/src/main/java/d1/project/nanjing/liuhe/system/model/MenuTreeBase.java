package d1.project.nanjing.liuhe.system.model;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
 * 菜单基础
 *
 * @author kikki
 * @date 2020-09-10 14:43
 */
@ApiModel(value = "MenuTreeBase", description = "菜单基础")
public class MenuTreeBase {
    @ApiModelProperty("路由地址")
    @Length(message = "路由长度超过200字符", min = 0, max = 200)
    private String path;

    @ApiModelProperty("名称")
    @Length(message = "名称长度超过200字符", min = 0, max = 100)
    private String name;

    @ApiModelProperty("0 菜单，1 外链，2 按钮")
    private int type;

    @ApiModelProperty("图标")
    private String icon;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
