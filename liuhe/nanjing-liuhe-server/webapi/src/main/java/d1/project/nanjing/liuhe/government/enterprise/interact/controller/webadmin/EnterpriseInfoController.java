package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Enterprise.EnterpriseInfoFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser.WebUserUpdateAllEnableVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser.WebUserUpdateEnableVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser.WebUserUpdatePasswordResetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.EnterpriseInfoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 企业用户
 * @author wkk
 * @return
 **/
@Auth("webadmin")
@RestController
@RequestMapping("/webadmin/enterprise/info")
public class EnterpriseInfoController {

    private final EnterpriseInfoService enterpriseInfoService;

    public EnterpriseInfoController(EnterpriseInfoService enterpriseInfoService) {
        this.enterpriseInfoService = enterpriseInfoService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<EnterpriseInfoEntity>> findAll(EnterpriseInfoFindVm model) {
        try {
            return ResultUtil.success(Constants.SUCCESS, enterpriseInfoService.findAll((JSONObject) JSONObject.toJSON(model)));
        } catch (Exception e) {
            return ResultUtil.fail("查询企业用户列表失败：" + e.getMessage());
        }
    }

    /**
     * 详情
     */
    @GetMapping("find")
    public Result<EnterpriseInfoEntity> find(@RequestParam String id) throws DoValidException {
        return ResultUtil.success(Constants.SUCCESS, enterpriseInfoService.find(id));
    }

    /**
     * 重置密码列表
     */
    @PutMapping("updatePasswordReset")
    public Result<String> updatePasswordReset(@Valid @RequestBody WebUserUpdatePasswordResetVm model, HttpServletRequest request) throws DoValidException {
        enterpriseInfoService.updatePasswordReset(model, request);
        return ResultUtil.success(Constants.SUCCESS);
    }

    /**
     * 启用禁用列表
     */
    @PutMapping("updateAllEnable")
    public Result<String> updateAllEnable(@Valid @RequestBody WebUserUpdateAllEnableVm model, HttpServletRequest request) throws DoValidException {
        enterpriseInfoService.updateAllEnable(model, request);
        return ResultUtil.success(Constants.SUCCESS);
    }

    /**
     * 启用禁用
     */
    @PutMapping("updateEnable")
    public Result<String> updateEnable(@Valid @RequestBody WebUserUpdateEnableVm model, HttpServletRequest request) throws DoValidException {
        enterpriseInfoService.updateEnable(model, request);
        return ResultUtil.success(Constants.SUCCESS);
    }


}
