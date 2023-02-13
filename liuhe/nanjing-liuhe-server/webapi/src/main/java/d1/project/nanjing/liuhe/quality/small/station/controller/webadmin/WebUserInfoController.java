package d1.project.nanjing.liuhe.quality.small.station.controller.webadmin;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.quality.small.station.entity.WebUserInfoEntity;
import d1.project.nanjing.liuhe.quality.small.station.model.WebUser.WebUserInfoFindVm;
import d1.project.nanjing.liuhe.quality.small.station.model.WebUser.WebUserUpdateAllEnableVm;
import d1.project.nanjing.liuhe.quality.small.station.model.WebUser.WebUserUpdateEnableVm;
import d1.project.nanjing.liuhe.quality.small.station.service.WebUserInfoService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 质量小站管理端-用户管理
 * @author wkk
 * @return
 **/
@Auth(Constants.AUTH_WEBADMIN)
@RestController("/webadmin/user/info")
@RequestMapping("/webadmin/user/info")
public class WebUserInfoController {

    private final WebUserInfoService webUserInfoService;

    public WebUserInfoController(WebUserInfoService webUserInfoService) {
        this.webUserInfoService = webUserInfoService;
    }

    /**
     * 分页查询
     *
     * @param model
     * @throws Exception
     */
    @GetMapping("findAll")
    public Result<Page<WebUserInfoEntity>> findAll(WebUserInfoFindVm model, HttpServletRequest request) {
        try {
            return ResultUtil.success(Constants.SUCCESS, webUserInfoService.findAll((JSONObject) JSONObject.toJSON(model), request));
        } catch (Exception e) {
            return ResultUtil.fail("查询用户列表失败：" + e.getMessage());
        }
    }

    /**
     * 详情
     */
    @GetMapping(value = "find")
    public Result<WebUserInfoEntity> find(@RequestParam String id) throws DoValidException {
        return ResultUtil.success(Constants.SUCCESS, webUserInfoService.find(id));
    }

    /**
     * 启用禁用列表
     */
    @PutMapping(value = {"updateAllEnable"})
    public Result<String> updateAllEnable(@Valid @RequestBody WebUserUpdateAllEnableVm model, HttpServletRequest request) throws DoValidException {
        webUserInfoService.updateAllEnable(model, request);
        return ResultUtil.success(Constants.SUCCESS);
    }

    /**
     * 启用禁用
     */
    @PutMapping(value = {"updateEnable"})
    public Result<String> updateEnable(@Valid @RequestBody WebUserUpdateEnableVm model, HttpServletRequest request) throws DoValidException {
        webUserInfoService.updateEnable(model, request);
        return ResultUtil.success(Constants.SUCCESS);
    }
}
