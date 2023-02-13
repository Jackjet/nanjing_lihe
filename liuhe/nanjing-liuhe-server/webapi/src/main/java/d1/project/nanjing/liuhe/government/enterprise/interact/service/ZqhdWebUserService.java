package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.model.SignInResult;
import d1.framework.webapi.utils.CacheManger;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.EnterpriseIdentificationDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.EnterpriseInfoDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.ZqhdWebUserDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ZqhdWebUserEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoIdentificationEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser.WebUserResetPasswordVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser.WebUserRetrievePasswordVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.WebUser.WebUserSignInVm;
import d1.project.nanjing.liuhe.log.model.vm.UserLogRequestInsertVm;
import d1.project.nanjing.liuhe.log.service.UserLogService;
import d1.project.nanjing.liuhe.quality.small.station.model.WebUser.WebUserSmsSignInVm;
import net.dcrun.component.ehcache.IEhcacheService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZqhdWebUserService {

    private final ZqhdWebUserDao webUserDao;
    private final UserLogService userLogService;

    private final EnterpriseInfoDao enterpriseInfoDao;
    private final EnterpriseIdentificationDao enterpriseIdentificationDao;

    public ZqhdWebUserService(ZqhdWebUserDao webUserDao, UserLogService userLogService, EnterpriseInfoDao enterpriseInfoDao, EnterpriseIdentificationDao enterpriseIdentificationDao) {
        this.webUserDao = webUserDao;
        this.userLogService = userLogService;
        this.enterpriseInfoDao = enterpriseInfoDao;
        this.enterpriseIdentificationDao = enterpriseIdentificationDao;
    }

    /**
     * 重置密码列表
     */
    @Transactional(rollbackFor = Exception.class)
    public void updatePasswordReset(List<String> ids, HttpServletRequest request) throws DoValidException {
        List<ZqhdWebUserEntity> webUsers = webUserDao.findAllById(ids).stream().peek(webUser -> webUser.setPassword("e10adc3949ba59abbe56e057f20f883e")).collect(Collectors.toList());
        webUserDao.saveAll(webUsers);

        /*清除所有重置密码的账户授权*/
        webUsers.forEach(this::deleteWebUserToken);
        List<String> collect = webUsers.stream().map(ZqhdWebUserEntity::getPhone).collect(Collectors.toList());
        userLogService.insert(new UserLogRequestInsertVm(JSON.toJSONString(webUsers), "重置用户密码" + String.join(",", collect), "政企互动-重置密码", 1), request);
    }

    /**
     * 清空缓存
     * @author wkk
     **/
    private void deleteWebUserToken(ZqhdWebUserEntity webUser) {
        IEhcacheService cache = CacheManger.getInstance().getCache();
        cache.traversal((k, v) -> {
            if (k.contains("token/")) {
                JSONObject jsonObject = JSONObject.parseObject(v);
                if (jsonObject != null) {
                    Object id = jsonObject.get("id");
                    if (id != null && id.toString().equals(webUser.getId())) {
                        cache.removeData(k);
                    }
                }

            }
        });
    }

    /**
     * 更新所有启用
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ZqhdWebUserEntity> updateAllEnable(List<String> ids, Integer isEnable, HttpServletRequest request) throws DoValidException {
        String userid = TokenManager.getInstance().getUserByToken(request).getString("id");
        List<ZqhdWebUserEntity> webUsers = webUserDao.findAllById(ids).stream().peek((webAdminUser) -> {
            webAdminUser.setEnable(isEnable);
            webAdminUser.setUpdateById(userid);
            webAdminUser.setUpdateTime(Calendar.getInstance());
        }).collect(Collectors.toList());
        webUserDao.saveAll(webUsers);
        List<String> collect = webUsers.stream().map(ZqhdWebUserEntity::getPhone).collect(Collectors.toList());
        userLogService.insert(new UserLogRequestInsertVm(JSON.toJSONString(webUsers), isEnable == 1 ? "启用用户" : "禁用用户" + String.join(",", collect), "政企互动-启用禁用", 1), request);
        return webUsers;
    }

    /**
     * 更新启用
     */
    @Transactional(rollbackFor = Exception.class)
    public ZqhdWebUserEntity updateEnable(String id, Integer isEnable, HttpServletRequest request) throws DoValidException {
        ZqhdWebUserEntity webUser = webUserDao.findById(id).orElseThrow(() -> new DoValidException("未找到用户ID"));
        webUser.setEnable(isEnable);
        TokenManager.getInstance().updateUpdateIdAndTime(request, webUser);
        webUserDao.save(webUser);
        userLogService.insert(new UserLogRequestInsertVm(webUser.getId(), isEnable == 1 ? "启用用户" : "禁用用户" + webUser.getPhone(), "政企互动-启用禁用", 1), request);
        return webUser;
    }

    /**
     * 用户登录
     */
    public SignInResult signIn(WebUserSignInVm model, HttpServletRequest request) throws DoValidException {
        BaseUtils.verifyCode(model.getVerificationCodeId(), model.getVerificationCode());
        ZqhdWebUserEntity user = webUserDao.findByPhone(model.getPhone()).orElseThrow(() -> new DoValidException("用户不存在"));
        if (1 != user.getEnable()) {
            throw new DoValidException("此用户已被停用，如有问题请联系工作人员。");
        }
        if (StringUtils.isEmpty(user.getPassword()) || !user.getPassword().equals(model.getPassword())) {
            throw new DoValidException("用户名或密码不正确");
        }
        String token = TokenManager.getInstance().createToken(Constants.AUTH_ZQHD, (JSONObject) JSON.toJSON(user), Constants.TOKEN_EXPIRE_TIME);
        SignInResult signInResult = new SignInResult(user.getId(), user.getPhone(), token);
        userLogService.insert(new UserLogRequestInsertVm(JSON.toJSONString(signInResult), "政企互动门户用户" + user.getPhone() + "登录", "登录", 1), request);
        return signInResult;
    }

    /**
     * 用户登录-小程序端
     */
    public SignInResult signInApp(WebUserSignInVm model, HttpServletRequest request) throws DoValidException {
        BaseUtils.verifyCode(model.getVerificationCodeId(), model.getVerificationCode());
        ZqhdWebUserEntity user = webUserDao.findByPhone(model.getPhone()).orElseThrow(() -> new DoValidException("用户不存在"));
        if (1 != user.getEnable()) {
            throw new DoValidException("此用户已被停用，如有问题请联系工作人员。");
        }
        if (StringUtils.isEmpty(user.getPassword()) || !user.getPassword().equals(model.getPassword())) {
            throw new DoValidException("用户名或密码不正确");
        }
        String token = TokenManager.getInstance().createToken(Constants.AUTH_ZQHD, (JSONObject) JSON.toJSON(user), Constants.APP_TOKEN_EXPIRE_TIME);
        SignInResult signInResult = new SignInResult(user.getId(), user.getPhone(), token);
        userLogService.insert(new UserLogRequestInsertVm(JSON.toJSONString(signInResult), "小程序端用户" + user.getPhone() + "登录", "登录", 1), request);
        return signInResult;
    }

    /**
     * 用户注册
     */
    @Transactional(rollbackFor = Exception.class)
    public void register(JSONObject model) throws DoValidException {
        this.verificationRegisterData(model);

        String userId = BaseUtils.generate32Id();
        EnterpriseInfoEntity enterpriseInfo = JSON.toJavaObject(model, EnterpriseInfoEntity.class);
        enterpriseInfo.setWebUserId(userId);
        this.insertEnterpriseInfo(enterpriseInfo);

        ZqhdWebUserEntity userEntity = JSON.toJavaObject(model, ZqhdWebUserEntity.class);
        userEntity.setId(userId);
        userEntity.setCreateTime(Calendar.getInstance());
        userEntity.setEnable(Constants.ENABLE_1);
        webUserDao.save(userEntity);
    }

    /**
     * 添加企业信息
     **/
    private void insertEnterpriseInfo(EnterpriseInfoEntity entity) throws DoValidException {
        entity.setId(BaseUtils.generate32Id());
        entity.setCreateTime(Calendar.getInstance());
        entity.setIdentification(Constants.IDENTIFICATION_0);//未认证
        entity.setEnable(Constants.ENABLE_1);//启用
        entity.setIsSettled(Constants.ENTERPRISE_IS_SETTLED_0);//未入驻
        enterpriseInfoDao.save(entity);
    }

    /**
     * 校验注册数据
     * @param model
     * @author wkk
     * @return void
     **/
    private void verificationRegisterData(JSONObject model) throws DoValidException {
        //验证图像验证码
        BaseUtils.verifySmsCode(model.getString("phone"), model.getString("smsCode"));
        if (webUserDao.existsByPhone(model.getString("phone"))) throw new DoValidException("手机号已被注册");
    }

    /**
     * 更新密码
     **/
    @Transactional(rollbackFor = Exception.class)
    public void updatePwd(JSONObject params, HttpServletRequest request) throws DoValidException {
        String userId = TokenManager.getInstance().getUserByToken(request).getString("id");
        BaseUtils.verifyCode(userId, params.getString("verificationCode"));

        ZqhdWebUserEntity entity = webUserDao.findById(userId).orElseThrow(() -> new DoValidException("用户不存在：" + userId));
        if (params.getString("password").equals(entity.getPassword())) {
            entity.setPassword(params.getString("newPassword"));
            TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
            webUserDao.save(entity);

            userLogService.insert(new UserLogRequestInsertVm(userId, "用户修改密码：" + entity.getPhone(), "政企互动-修改密码", 1), request);
        } else {
            throw new DoValidException("原密码错误");
        }
    }

    /**
     * 用户认证
     **/
    @Transactional(rollbackFor = Exception.class)
    public void identification(JSONObject model, HttpServletRequest request) throws DoValidException {
        //校验数据格式
//        this.verificationIdentificationData(model);
        String userid = TokenManager.getInstance().getUserByToken(request).getString("id");
        EnterpriseInfoEntity enterpriseInfo = JSON.toJavaObject(model, EnterpriseInfoEntity.class);
        EnterpriseInfoEntity entity = enterpriseInfoDao.findFirstByWebUserId(userid).orElseThrow(() -> new DoValidException("企业ID不存在"));
        //校验用户名是否重复
        if (!entity.getEnterpriseName().equals(enterpriseInfo.getEnterpriseName())
                && enterpriseInfoDao.existsByEnterpriseName(enterpriseInfo.getEnterpriseName())) {
            throw new DoValidException(enterpriseInfo.getEnterpriseName() + "，企业名称已存在。");
        }

        //复制属性值
        BaseUtils.copyProperties(enterpriseInfo, entity);
        entity.setIdentification(1);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        enterpriseInfoDao.save(entity);

        if (enterpriseIdentificationDao.existsByWebUserIdAndIdentification(entity.getWebUserId(), 1)) {
            throw new DoValidException("您的信息已在认证中，请不要重复提交。");
        }

        //添加认证信息
        EnterpriseInfoIdentificationEntity wie = new EnterpriseInfoIdentificationEntity();
        BaseUtils.copyProperties(entity, wie);
        wie.setId(BaseUtils.generate32Id());
        wie.setIdentification(1);//发起认证
        wie.setCreateById(userid);
        wie.setCreateTime(Calendar.getInstance());
        wie.setUpdateById(null);
        wie.setUpdateTime(null);
        enterpriseIdentificationDao.save(wie);
    }

    /**
     * 校验认证数据
     * @param model
     * @author wkk
     * @date 2021/7/5 10:47
     * @return void
     **/
//    private void verificationIdentificationData(JSONObject model) throws DoValidException {
//        if (StringUtils.isEmpty(model.getString("account"))) {
//            throw new DoValidException("用户名不可为空");
//        }
//        if (!StringUtils.isEmpty(model.getInteger("userType")) && model.getInteger("userType") == 1) {
//            if (StringUtils.isEmpty(model.getString("userName"))) {
//                throw new DoValidException("法人姓名不可为空");
//            }
//            if (StringUtils.isEmpty(model.getString("idCard"))) {
//                throw new DoValidException("社会信用代码不可为空");
//            }
//            if (StringUtils.isEmpty(model.getString("idCardPositiveImagePath"))) {
//                throw new DoValidException("营业执照图片不可为空");
//            }
//        } else {
//            if (StringUtils.isEmpty(model.getString("userName"))) {
//                throw new DoValidException("真实姓名不可为空");
//            }
//            if (StringUtils.isEmpty(model.getString("idCard"))) {
//                throw new DoValidException("证件号码不可为空");
//            }
//            if (StringUtils.isEmpty(model.getString("idType"))) {
//                throw new DoValidException("证件类型不可为空");
//            }
//            if (StringUtils.isEmpty(model.getString("idCardPositiveImagePath"))) {
//                throw new DoValidException("身份证图片正面不可为空");
//            }
//            if (StringUtils.isEmpty(model.getString("idCardNegativeImagePath"))) {
//                throw new DoValidException("身份证图片反面不可为空");
//            }
//        }
//        if (StringUtils.isEmpty(model.getString("phone"))) {
//            throw new DoValidException("手机号不可为空");
//        }
//        if (StringUtils.isEmpty(model.getString("email"))) {
//            throw new DoValidException("电子邮箱不可为空");
//        }
//        if (!MyUtil.Regx(model.getString("account"), RegexpConstant.ACCOUNT)) {
//            throw new DoValidException("用户名格式不正确");
//        }
//        if (!MyUtil.Regx(model.getString("phone"), RegexpConstant.PHONE)) {
//            throw new DoValidException("手机号格式不正确");
//        }
//        if (!MyUtil.Regx(model.getString("email"), RegexpConstant.EMAIL)) {
//            throw new DoValidException("电子邮箱格式不正确");
//        }
//    }

    /**
     * 验证账号密码
     * @param model
     * @author wkk
     * @date 2021/7/5 18:28
     * @return void
     **/
    public void verificationAccountAndPassword(JSONObject model) throws DoValidException {
        if (!webUserDao.existsByPhoneAndPassword(model.getString("phone"), model.getString("password"))) throw new DoValidException("密码不正确");
    }

    /**
     * 修改手机号
     * @param model
     * @param request
     * @author wkk
     **/
    public void updatePhone(JSONObject model, HttpServletRequest request) throws DoValidException {
        String userid = TokenManager.getInstance().getUserByToken(request).getString("id");
        ZqhdWebUserEntity entity = webUserDao.findById(userid).orElseThrow(() -> new DoValidException("用户不存在：" + userid));
        //验证手机号是否重复
        if (model.getString("phone").equals(entity.getPhone())) {
            throw new DoValidException("当前手机号并未修改，无需提交");
        }
        if (verificationPhone(model.getString("phone"))) throw new DoValidException(model.getString("phone") + "手机号已被使用，无法修改");
        //TODO 验证短信验证码

        //修改手机号
        entity.setPhone(model.getString("phone"));
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        webUserDao.save(entity);
    }

    /**
     * 手机号是否存在
     * @param phone
     * @author wkk
     * @return boolean
     **/
    public boolean verificationPhone(String phone) throws DoValidException {
        if (StringUtils.isEmpty(phone)) {
            throw new DoValidException("手机号不能为空");
        }
        return webUserDao.existsByPhone(phone);
    }

    /**
     * 找回密码-验证
     * @param model
     * @author wkk
     * @return void
     **/
    public String retrievePassword(WebUserRetrievePasswordVm model) throws DoValidException {
        webUserDao.findByPhone(model.getPhone()).orElseThrow(() -> new DoValidException("手机号不存在"));
        if (webUserDao.existsByPhone(model.getPhone())) throw new DoValidException("手机号不存在");
        String uuid = BaseUtils.generate32Id();
        CacheManger.getInstance().getCache().setExpireString(model.getPhone(), uuid, 5 * 1000);
        return uuid;
    }

    /**
     * 找回密码后-重置密码
     * @param model
     * @param request
     * @author wkk
     * @return void
     **/
    public void resetPassword(WebUserResetPasswordVm model, HttpServletRequest request) throws DoValidException {
        //验证临时Token
        if (!CacheManger.getInstance().getCache().containsKey(model.getPhone())) throw new DoValidException("重置密码失败");
        if (!CacheManger.getInstance().getCache().getString(model.getPhone()).equals(model.getToken())) {
            throw new DoValidException("重置密码失败");
        }
        //根据手机号查找用户
        ZqhdWebUserEntity userEntity = webUserDao.findByPhone(model.getPhone()).orElseThrow(() -> new DoValidException("手机号无效"));
        //修改密码
        userEntity.setPassword(model.getPassword());
        userEntity.setUpdateTime(Calendar.getInstance());
        //保存密码
        webUserDao.save(userEntity);
        CacheManger.getInstance().getCache().removeData(model.getPhone());
    }

    /**
     * 查询用户基本信息
     **/
    public EnterpriseInfoEntity findUserByToken(HttpServletRequest request) throws Exception {
        String userId = TokenManager.getInstance().getUserByToken(request).getString("id");
        return enterpriseInfoDao.findFirstByWebUserId(userId).orElseThrow(() -> new DoValidException("Token无效"));
    }

    /**
     * 短信登录
     * @param model
     * @param request
     * @author wkk
     **/
    public SignInResult smsSignIn(WebUserSmsSignInVm model, HttpServletRequest request) throws DoValidException {
        // TODO 短信验证码
//        BaseUtils.verifySmsCode(model.getPhone(), model.getSmsCode());

        ZqhdWebUserEntity user = webUserDao.findByPhone(model.getPhone()).orElseThrow(() -> new DoValidException("该手机号未注册，请先注册！"));
        if (1 != user.getEnable()) {
            throw new DoValidException("此用户已被停用，如有问题请联系工作人员。");
        }
        String token = TokenManager.getInstance().createToken(Constants.AUTH_ZQHD, (JSONObject) JSON.toJSON(user), Constants.TOKEN_EXPIRE_TIME);
        SignInResult signInResult = new SignInResult(user.getId(), user.getPhone(), token);
        userLogService.insert(new UserLogRequestInsertVm(JSON.toJSONString(signInResult), "政企互动门户用户短信" + user.getPhone() + "登录", "登录", 1), request);
        return signInResult;
    }

    /**
     * 短信登录
     * @param model
     * @param request
     * @author wkk
     **/
    public SignInResult smsSignInApp(WebUserSmsSignInVm model, HttpServletRequest request) throws DoValidException {
        // TODO 短信验证码
//        BaseUtils.verifySmsCode(model.getPhone(), model.getSmsCode());

        ZqhdWebUserEntity user = webUserDao.findByPhone(model.getPhone()).orElseThrow(() -> new DoValidException("该手机号未注册，请先注册！"));
        if (1 != user.getEnable()) {
            throw new DoValidException("此用户已被停用，如有问题请联系工作人员。");
        }
        String token = TokenManager.getInstance().createToken(Constants.AUTH_ZQHD, (JSONObject) JSON.toJSON(user), Constants.APP_TOKEN_EXPIRE_TIME);
        SignInResult signInResult = new SignInResult(user.getId(), user.getPhone(), token);
        userLogService.insert(new UserLogRequestInsertVm(JSON.toJSONString(signInResult), "小程序端用户短信" + user.getPhone() + "登录", "登录", 1), request);
        return signInResult;
    }
}
