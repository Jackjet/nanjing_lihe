package d1.project.nanjing.liuhe.unify.manage.entity;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 字典
 * @author wkk
 **/
@Entity
@Table(name = "d1_public_dictionary")
@ApiModel(value = "Dictionary", description = "字典")
public class DictionaryEntity extends BaseEntity {

    @ApiModelProperty("显示")
    @Column(length = 100)
    private String label;

    @ApiModelProperty("数据值")
    @Column(length = 100)
    private String value;

    @ApiModelProperty("数据值")
    @Column(length = 100)
    private String type;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
