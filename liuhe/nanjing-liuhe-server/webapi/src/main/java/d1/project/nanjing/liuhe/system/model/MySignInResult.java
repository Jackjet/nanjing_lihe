package d1.project.nanjing.liuhe.system.model;

import d1.framework.webapi.model.SignInResult;

import java.util.Arrays;
import java.util.List;

/**
 * d1Project
 *
 * @author kikki
 * @date 2020-09-15 21:56
 */
public class MySignInResult extends SignInResult {
    private List<String> roles;
    private List<String> roleNames;

    public MySignInResult(WebAdminUserVm webAdminUserVm, String token) {
        super(webAdminUserVm.getId(), webAdminUserVm.getName(), token);
        setRoles(Arrays.asList(webAdminUserVm.getRoleId().split(",")));
        setRoleNames(Arrays.asList(webAdminUserVm.getRoleName().split(",")));
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
