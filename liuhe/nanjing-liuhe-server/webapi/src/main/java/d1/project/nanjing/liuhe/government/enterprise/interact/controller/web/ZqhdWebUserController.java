package d1.project.nanjing.liuhe.government.enterprise.interact.controller.web;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.annotation.ApiModelProperty;
import d1.framework.webapi.annotation.Auth;
import d1.framework.webapi.http.Result;
import d1.framework.webapi.http.ResultUtil;
import d1.framework.webapi.model.SignInResult;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser.*;
import d1.project.nanjing.liuhe.government.enterprise.interact.service.ZqhdWebUserService;
import d1.project.nanjing.liuhe.quality.small.station.model.WebUser.WebUserSmsSignInVm;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 政企互动门户用户
 * @author wkk
 * @return
 **/
@Auth(Constants.AUTH_ZQHD)
@RestController
@RequestMapping("/zqhd/user")
public class ZqhdWebUserController {

    private final ZqhdWebUserService zqhdWebUserService;

    public ZqhdWebUserController(ZqhdWebUserService zqhdWebUserService) {
        this.zqhdWebUserService = zqhdWebUserService;
    }

    /**
     * 登录
     **/
    @Auth("noauth")
    @ApiModelProperty(value = "用户登录", notes = "用户名密码登录")
    @GetMapping("signIn")
    public Result<SignInResult> signIn(@Valid WebUserSignInVm model, HttpServletRequest request) {
        try {
            SignInResult result = zqhdWebUserService.signIn(model, request);
            return ResultUtil.success("登录成功", result);
        } catch (Exception e) {
            return ResultUtil.fail("登录失败：" + e.getMessage(), e);
        }
    }

    /**
     * 小程序端登录
     **/
    @Auth("noauth")
    @ApiModelProperty(value = "小程序端用户登录", notes = "小程序端用户名密码登录")
    @GetMapping("signInApp")
    public Result<SignInResult> signInApp(@Valid WebUserSignInVm model, HttpServletRequest request) {
        try {
            SignInResult result = zqhdWebUserService.signInApp(model, request);
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
            zqhdWebUserService.register((JSONObject) JSONObject.toJSON(model));
            return ResultUtil.success("注册成功");
        } catch (Exception e) {
            return ResultUtil.fail("注册失败：" + e.getMessage());
        }
    }

    /**
     * 认证
     **/
    @PostMapping("identification")
    public Result<String> identification(@Valid @RequestBody WebUserUpdateIdentificationVm model, HttpServletRequest request){
        try {
            zqhdWebUserService.identification((JSONObject) JSONObject.toJSON(model), request);
            return ResultUtil.success("认证成功");
        } catch (Exception e) {
            return ResultUtil.fail("认证失败：" + e.getMessage());
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
            zqhdWebUserService.updatePwd((JSONObject) JSONObject.toJSON(model), request);
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
            zqhdWebUserService.verificationAccountAndPassword((JSONObject) JSONObject.toJSON(model));
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
            zqhdWebUserService.updatePhone((JSONObject) JSONObject.toJSON(model), request);
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
            return ResultUtil.success("验证成功", zqhdWebUserService.verificationPhone(phone));
        } catch (Exception e) {
            return ResultUtil.fail("验证失败：" + e.getMessage());
        }
    }

    /**
     * 找回密码-验证
     * @param model
     * @param request
     * @author wkk
     * @date 2021/7/6 16:24
     * @return d1.framework.webapi.http.Result<java.lang.String>
     **/
    @Auth("noauth")
    @PostMapping("retrievePassword")
    public Result<String> retrievePassword(@Valid @RequestBody WebUserRetrievePasswordVm model,HttpServletRequest request){
        try {
            return ResultUtil.success(zqhdWebUserService.retrievePassword(model));
        } catch (Exception e) {
            return ResultUtil.fail("验证失败：" + e.getMessage());
        }
    }

    /**
     * 找回密码-重置密码
     * @param model
     * @param request
     * @author wkk
     * @date 2021/7/6 16:24
     * @return d1.framework.webapi.http.Result<java.lang.String>
     **/
    @Auth("noauth")
    @PostMapping("resetPassword")
    public Result<String> resetPassword(@Valid @RequestBody WebUserResetPasswordVm model,HttpServletRequest request){
        try {
            zqhdWebUserService.resetPassword(model, request);
            return ResultUtil.success(Constants.SUCCESS);
        } catch (Exception e) {
            return ResultUtil.fail("重置密码失败：" + e.getMessage());
        }
    }

    /**
     * 查询企业用户基本信息
     */
    @GetMapping("findUserByToken")
    public Result<EnterpriseInfoEntity> findUserByToken(HttpServletRequest request) {
        try {
            return ResultUtil.success(Constants.SUCCESS, zqhdWebUserService.findUserByToken(request));
        } catch (Exception e) {
            return ResultUtil.fail("查询用户基本信息失败：" + e.getMessage());
        }
    }

    /**
     * 手机号短信登录
     **/
    @Auth("noauth")
    @GetMapping("smsSignIn")
    public Result<SignInResult> smsSignIn(@Valid WebUserSmsSignInVm model, HttpServletRequest request) {
        try {
            SignInResult result = zqhdWebUserService.smsSignIn(model, request);
            return ResultUtil.success("登录成功", result);
        } catch (Exception e) {
            return ResultUtil.fail("登录失败：" + e.getMessage(), e);
        }
    }

    /**
     * 手机号短信登录-小程序端
     **/
    @Auth("noauth")
    @GetMapping("smsSignInApp")
    public Result<SignInResult> smsSignInApp(@Valid WebUserSmsSignInVm model, HttpServletRequest request) {
        try {
            SignInResult result = zqhdWebUserService.smsSignInApp(model, request);
            return ResultUtil.success("登录成功", result);
        } catch (Exception e) {
            return ResultUtil.fail("登录失败：" + e.getMessage(), e);
        }
    }

}
