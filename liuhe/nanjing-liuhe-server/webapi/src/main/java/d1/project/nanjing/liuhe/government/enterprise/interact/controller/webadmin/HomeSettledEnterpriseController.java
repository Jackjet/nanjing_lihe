package d1.project.nanjing.liuhe.government.enterprise.interact.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.CommonlyUsedUrlEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.HomeSettledEnterprise.HomeSettledEnterpriseFindVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.HomeSettledEnterprise.HomeSettledEnterpriseInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.HomeSettledEnterpriseService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 首页入驻企业
 * @author wkk
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/home/settled/enterprise")
@RequestMapping("/webadmin/home/settled/enterprise")
public class HomeSettledEnterpriseController {

    private final HomeSettledEnterpriseService homeSettledEnterpriseService;

    public HomeSettledEnterpriseController(HomeSettledEnterpriseService homeSettledEnterpriseService) {
        this.homeSettledEnterpriseService = homeSettledEnterpriseService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<CommonlyUsedUrlEntity>> findAll(HomeSettledEnterpriseFindVm model) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, homeSettledEnterpriseService.findAll((JSONObject) JSONObject.toJSON(model)));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 新增
     *
     * @param request
     * @param model
     * @throws Exception
     */
    @PostMapping("save")
    public Result<String> save(@Valid @RequestBody HomeSettledEnterpriseInsetVm model, HttpServletRequest request) {
        try {
            homeSettledEnterpriseService.save(model, request);
            return ResultUtil.success(Constants.ADD_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.ADD_FAIL + e.getMessage());
        }
    }

    /**
     * 单条删除
     *
     * @param id
     **/
    @DeleteMapping("delete")
    public Result<String> delete(String id) {
        try {
            homeSettledEnterpriseService.delete(id);
            return ResultUtil.success(Constants.DELETE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.DELETE_FAIL + e.getMessage());
        }
    }

    /**
     * 详情
     *
     * @param id
     **/
    @GetMapping("findById")
    public Result<CommonlyUsedUrlEntity> findById(String id) {
        try {
            return ResultUtil.success(Constants.SUCCESS, homeSettledEnterpriseService.findById(id));
        } catch (DoValidException e) {
            return ResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }
}
