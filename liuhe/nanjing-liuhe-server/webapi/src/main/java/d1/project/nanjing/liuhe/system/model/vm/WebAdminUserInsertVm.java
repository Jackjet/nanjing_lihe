package d1.project.nanjing.liuhe.system.model.vm;

import d1.framework.webapi.annotation.ApiModel;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.project.nanjing.liuhe.system.model.WebAdminUserBase;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 新增
 *
 * @author kikki
 * @date 2020-09-08 14:29
 */
@ApiModel(value = "WebAdminUserInsertVm", description = "新增")
public class WebAdminUserInsertVm extends WebAdminUserBase {

    @ApiModelProperty("账户")
    @Length(message = "账户长度超过100字符", min = 1, max = 100)
    @NotBlank(message = "账户不可为空")
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


}
