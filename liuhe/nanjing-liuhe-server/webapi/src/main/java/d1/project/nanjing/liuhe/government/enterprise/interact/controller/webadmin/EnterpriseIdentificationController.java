package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoIdentificationEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Enterprise.EnterpriseIdentificationFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Enterprise.EnterpriseIdentificationVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.EnterpriseIdentificationService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Auth("webadmin")
@RestController
@RequestMapping("/webadmin/enterprise/identification")
public class EnterpriseIdentificationController {

    private final EnterpriseIdentificationService enterpriseIdentificationService;

    public EnterpriseIdentificationController(EnterpriseIdentificationService enterpriseIdentificationService) {
        this.enterpriseIdentificationService = enterpriseIdentificationService;
    }


    /**
     * 分页查询-未审核
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAllByNotIdentification")
    public Result<Page<EnterpriseInfoIdentificationEntity>> findAllByNotIdentification(EnterpriseIdentificationFindVm model, HttpServletRequest request) {
        try {
            return ResultUtil.success(Constants.SUCCESS, enterpriseIdentificationService.findAllByNotIdentification((JSONObject) JSONObject.toJSON(model), request));
        } catch (Exception e) {
            return ResultUtil.fail("查询企业用户认证列表失败：" + e.getMessage());
        }
    }

    /**
     * 分页查询-审核过
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAllByExamine")
    public Result<Page<EnterpriseInfoIdentificationEntity>> findAllByExamine(EnterpriseIdentificationFindVm model, HttpServletRequest request) {
        try {
            return ResultUtil.success(Constants.SUCCESS, enterpriseIdentificationService.findAllByExamine((JSONObject) JSONObject.toJSON(model), request));
        } catch (Exception e) {
            return ResultUtil.fail("查询企业用户认证列表失败：" + e.getMessage());
        }
    }

    /**
     * 详情
     */
    @GetMapping("find")
    public Result<EnterpriseInfoIdentificationEntity> find(@RequestParam String id) throws DoValidException {
        return ResultUtil.success(Constants.SUCCESS, enterpriseIdentificationService.find(id));
    }

    /**
     * 审核认证
     * @param model
     **/
    @PutMapping("identificationExamine")
    public Result<String> identificationExamine(@Valid @RequestBody EnterpriseIdentificationVm model, HttpServletRequest request) {
        try {
            enterpriseIdentificationService.identificationExamine(model, request);
            return ResultUtil.success(Constants.EXAMINE_SUCCESS);
        } catch (Exception e) {
            return ResultUtil.fail(Constants.EXAMINE_FAIL + e.getMessage());
        }
    }
}
