package d1.project.nanjing.liuhe.quality.small.station.controller.web;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.framework.webapi.model.SignInResult;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.quality.small.station.entity.WebUserInfoEntity;
import d1.project.nanjing.liuhe.quality.small.station.model.WebUser.*;
import d1.project.nanjing.liuhe.quality.small.station.service.WebUserInfoService;
import d1.project.nanjing.liuhe.quality.small.station.service.WebUserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 质量小站-用户
 * @author wkk
 **/
@Auth(Constants.AUTH_ZLXZ)
@RestController("/zlxz/user")
@RequestMapping("/zlxz/user")
public class WebUserController {

    private final WebUserService webUserService;
    private final WebUserInfoService webUserInfoService;

    public WebUserController(WebUserService webUserService, WebUserInfoService webUserInfoService) {
        this.webUserService = webUserService;
        this.webUserInfoService = webUserInfoService;
    }

    /**
     * 登录
     **/
    @Auth("noauth")
    @ApiModelProperty(value = "用户登录", notes = "根据用户名密码登录")
    @GetMapping("signIn")
    public Result<SignInResult> signIn(@Valid WebUserSignInVm model) {
        try {
            SignInResult result = webUserService.signIn(model);
            return ResultUtil.success("登录成功", result);
        } catch (Exception e) {
            return ResultUtil.fail("登录失败：" + e.getMessage(), e);
        }
    }

    /**
     * 注册
     **/
    @Auth("noauth")
    @ApiModelProperty(value = "注册", notes = "注册")
    @PostMapping("register")
    public Result<String> register(@Valid @RequestBody WebUserInsertVm model) {
        try {
            webUserService.register((JSONObject) JSONObject.toJSON(model));
            return ResultUtil.success("注册成功");
        } catch (Exception e) {
            return ResultUtil.fail("注册失败：" + e.getMessage());
        }
    }

    /**
     * 修改密码
     * @param model
     * @param request
     * @author wkk
     **/
    @ApiModelProperty(value = "修改密码", notes = "修改密码")
    @PutMapping("updatePwd")
    public Result<String> updatePwd(@Valid @RequestBody WebUserUpdatePwdVm model, HttpServletRequest request) {
        try {
            webUserService.updatePwd((JSONObject) JSONObject.toJSON(model), request);
            return ResultUtil.success("更新成功");
        } catch (Exception e) {
            return ResultUtil.fail("更新失败：" + e.getMessage());
        }
    }

    /**
     * 校验账号密码
     * @param model
     * @author wkk
     **/
    @GetMapping("verificationAccountAndPassword")
    public Result<String> verificationAccountAndPassword(@Valid WebUserVerificationPwdVm model, HttpServletRequest request){
        try {
            webUserService.verificationAccountAndPassword((JSONObject) JSONObject.toJSON(model), request);
            return ResultUtil.success("验证成功");
        } catch (Exception e) {
            return ResultUtil.fail("验证失败：" + e.getMessage());
        }
    }

    /**
     * 修改手机号
     * @param model
     * @author wkk
     **/
    @PutMapping("updatePhone")
    public Result<String> updatePhone(@Valid @RequestBody WebUserUpdatePhoneVm model, HttpServletRequest request){
        try {
            webUserService.updatePhone((JSONObject) JSONObject.toJSON(model), request);
            return ResultUtil.success("修改手机号成功");
        } catch (Exception e) {
            return ResultUtil.fail("修改手机号失败：" + e.getMessage());
        }
    }

    /**
     * 验证手机号是否存在
     * @author wkk
     **/
    @GetMapping("verificationPhone")
    public Result<Boolean> verificationPhone(String phone) {
        try {
            return ResultUtil.success("验证成功", webUserService.verificationPhone(phone));
        } catch (Exception e) {
            return ResultUtil.fail("验证失败：" + e.getMessage());
        }
    }

    /**
     * 查询用户基本信息 BY token
     */
    @GetMapping("findUserByToken")
    public Result<WebUserInfoEntity> findUserByToken(HttpServletRequest request) {
        try {
            return ResultUtil.success(Constants.FIND_SUCCESS, webUserService.findUserByToken(request));
        } catch (Exception e) {
            return ResultUtil.fail(Constants.FIND_FAIL + e.getMessage());
        }
    }

    /**
     * 更新
     **/
    @PutMapping("update")
    public Result<String> update(@Valid @RequestBody WebUserUpdateVm model, HttpServletRequest request) {
        try {
            webUserInfoService.update(model, request);
            return ResultUtil.success(Constants.UPDATE_SUCCESS);
        } catch (DoValidException e) {
            return ResultUtil.fail(Constants.UPDATE_FAIL + e.getMessage());
        } catch (Exception e) {
            return ResultUtil.fail(e.getMessage());
        }
    }

    /**
     * 手机号短信登录
     **/
    @Auth("noauth")
    @ApiModelProperty(value = "手机号短信登录", notes = "根据手机号短信登录")
    @GetMapping("smsSignIn")
    public Result<SignInResult> smsSignIn(@Valid WebUserSmsSignInVm model, HttpServletRequest request) {
        try {
            SignInResult result = webUserService.smsSignIn(model, request);
            return ResultUtil.success("登录成功", result);
        } catch (Exception e) {
            return ResultUtil.fail("登录失败：" + e.getMessage(), e);
        }
    }

}
