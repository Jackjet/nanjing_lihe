package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.SettledEnterpriseEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.SettledEnterprise.SettledEnterpriseExamineVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.SettledEnterprise.SettledEnterpriseFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.SettledEnterpriseService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 入驻企业
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/settled/enterprise")
@RequestMapping("/webadmin/settled/enterprise")
public class SettledEnterpriseController {

    private final SettledEnterpriseService settledEnterpriseService;

    public SettledEnterpriseController(SettledEnterpriseService settledEnterpriseService) {
        this.settledEnterpriseService = settledEnterpriseService;
    }


    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<SettledEnterpriseEntity>> findAll(SettledEnterpriseFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, settledEnterpriseService.findAll((JSONObject) JSONObject.toJSON(model)));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 审批
     *
     * @param model
     * @param request
     * @return
     */
    @PutMapping("examine")
    public Result<String> examine(@Valid @RequestBody SettledEnterpriseExamineVm model, HttpServletRequest request) {
        try {
            settledEnterpriseService.examine(model, request);
            return ResultUtil.success(Constants.EXAMINE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.EXAMINE_FAIL + e.getMessage());
        }
    }

    /**
     * 详情
     *
     * @param id
     **/
    @GetMapping("findById")
    public Result<SettledEnterpriseEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, settledEnterpriseService.findById(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
