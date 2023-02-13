package d1.project.nanjing.liuhe.quality.small.station.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.model.SignInResult;
import d1.framework.webapi.utils.CacheManger;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.log.model.vm.UserLogRequestInsertVm;
import d1.project.nanjing.liuhe.log.service.UserLogService;
import d1.project.nanjing.liuhe.quality.small.station.dao.WebUserDao;
import d1.project.nanjing.liuhe.quality.small.station.dao.WebUserInfoDao;
import d1.project.nanjing.liuhe.quality.small.station.entity.WebUserEntity;
import d1.project.nanjing.liuhe.quality.small.station.entity.WebUserInfoEntity;
import d1.project.nanjing.liuhe.quality.small.station.model.WebUser.WebUserSignInVm;
import d1.project.nanjing.liuhe.quality.small.station.model.WebUser.WebUserSmsSignInVm;
import net.dcrun.component.ehcache.IEhcacheService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebUserService {

    private final WebUserDao webUserDao;
    private final UserLogService userLogService;
    private final WebUserInfoDao webUserInfoDao;

    public WebUserService(WebUserDao webUserDao, UserLogService userLogService, WebUserInfoDao webUserInfoDao) {
        this.webUserDao = webUserDao;
        this.userLogService = userLogService;
        this.webUserInfoDao = webUserInfoDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @param request
     * @author wkk
     **/
    public Page<WebUserInfoEntity> findAll(JSONObject params, HttpServletRequest request) throws Exception {
        SpecificationBuilder<WebUserInfoEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("enterpriseName", "enterpriseName")
                .sContain("phone", "phone")
                .sContain("liaisonName", "liaisonName")
                .sContain("liaisonTel", "liaisonTel")
                .sEqual("province", "province")
                .sEqual("city", "city")
                .sEqual("area", "area")
                .sEqual("industry", "industry")
                .sEqual("enable", "enable")
                .dOrder("createTime")
                .build();
        return webUserInfoDao.findAll(specification, builder.getPageable());
    }

    /**
     * 详情
     * @param id
     * @author wkk
     **/
    public WebUserInfoEntity find(String id) throws DoValidException {
        return webUserInfoDao.findById(id).orElseThrow(() ->new DoValidException("用户ID不存在"));
    }

    /**
     * 重置密码列表
     */
    @Transactional(rollbackFor = Exception.class)
    public void updatePasswordReset(List<String> ids, HttpServletRequest request) throws DoValidException {
        List<WebUserEntity> webUsers = webUserDao.findAllById(ids).stream().peek(webUser -> webUser.setPassword("e10adc3949ba59abbe56e057f20f883e")).collect(Collectors.toList());
        webUserDao.saveAll(webUsers);

        /*清除所有重置密码的账户授权*/
        webUsers.forEach(this::deleteWebUserToken);
        List<String> collect = webUsers.stream().map(WebUserEntity::getPhone).collect(Collectors.toList());
        userLogService.insert(new UserLogRequestInsertVm(JSON.toJSONString(webUsers), "重置用户密码" + String.join(",", collect), "质量小站-重置密码", 1), request);
    }

    /**
     * 清空缓存
     * @param webUser
     * @author wkk
     **/
    private void deleteWebUserToken(WebUserEntity webUser) {
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
    public List<WebUserEntity> updateAllEnable(List<String> ids,Integer isEnable, HttpServletRequest request) throws DoValidException {
        String userid = TokenManager.getInstance().getUserByToken(request).getString("id");
        List<WebUserEntity> webUsers = webUserDao.findAllById(ids).stream().peek((webAdminUser) -> {
            webAdminUser.setEnable(isEnable);
            webAdminUser.setUpdateById(userid);
            webAdminUser.setUpdateTime(Calendar.getInstance());
        }).collect(Collectors.toList());
        webUserDao.saveAll(webUsers);
        List<String> collect = webUsers.stream().map(WebUserEntity::getPhone).collect(Collectors.toList());
        userLogService.insert(new UserLogRequestInsertVm(JSON.toJSONString(webUsers), isEnable == 1 ? "启用用户" : "禁用用户" + String.join(",", collect), "质量小站-启用禁用", 1), request);
        return webUsers;
    }

    /**
     * 更新启用
     */
    @Transactional(rollbackFor = Exception.class)
    public WebUserEntity updateEnable(String id,Integer isEnable, HttpServletRequest request) throws DoValidException {
        WebUserEntity webUser = webUserDao.findById(id).orElseThrow(() -> new DoValidException("未找到用户ID"));
        webUser.setEnable(isEnable);
        TokenManager.getInstance().updateUpdateIdAndTime(request, webUser);
        webUserDao.save(webUser);
        userLogService.insert(new UserLogRequestInsertVm(webUser.getId(), isEnable == 1 ? "启用用户" : "禁用用户" + webUser.getPhone(), "质量小站-启用禁用", 1), request);
        return webUser;
    }

    /**
     * 用户登录
     */
    public SignInResult signIn(WebUserSignInVm model) throws DoValidException {
        BaseUtils.verifyCode(model.getVerificationCodeId(), model.getVerificationCode());

        WebUserEntity user = webUserDao.findByPhone(model.getPhone()).orElseThrow(() -> new DoValidException("用户不存在"));
        if (1 != user.getEnable()) {
            throw new DoValidException("此用户已被停用，如有问题请联系工作人员。");
        }
        if (StringUtils.isEmpty(user.getPassword()) || !user.getPassword().equals(model.getPassword())) {
            throw new DoValidException("用户名或密码不正确");
        }
        String token = TokenManager.getInstance().createToken(Constants.AUTH_ZLXZ, (JSONObject) JSON.toJSON(user), Constants.TOKEN_EXPIRE_TIME);
        return new SignInResult(user.getId(), user.getPhone(), token);
    }

    /**
     * 用户注册
     */
    @Transactional(rollbackFor = Exception.class)
    public void register(JSONObject model) throws DoValidException {
        this.verificationRegisterData(model);

        String userId = BaseUtils.generate32Id();
        WebUserInfoEntity userInfoEntity = JSON.toJavaObject(model, WebUserInfoEntity.class);
        userInfoEntity.setWebUserId(userId);
        this.insertUserInfo(userInfoEntity);

        WebUserEntity userEntity = JSON.toJavaObject(model, WebUserEntity.class);
        userEntity.setId(userId);
        userEntity.setCreateTime(Calendar.getInstance());
        userEntity.setEnable(Constants.ENABLE_1);
        webUserDao.save(userEntity);
    }

    /**
     * 添加用户信息
     **/
    private void insertUserInfo(WebUserInfoEntity entity) throws DoValidException {
        entity.setId(BaseUtils.generate32Id());
        entity.setCreateTime(Calendar.getInstance());
        entity.setEnable(Constants.ENABLE_1);//启用
        webUserInfoDao.save(entity);
    }

    /**
     * 校验注册数据
     * @param model
     * @author wkk
     * @date 2021/7/5 10:47
     * @return void
     **/
    private void verificationRegisterData(JSONObject model) throws DoValidException {
        // TODO 短信验证码
//        BaseUtils.verifySmsCode(model.getString("phone"), model.getString("smsCode"));
        if (webUserDao.findByPhone(model.getString("phone")).isPresent()) throw new DoValidException("手机号已被注册");
    }

    /**
     * 修改密码
     **/
    @Transactional(rollbackFor = Exception.class)
    public void updatePwd(JSONObject params, HttpServletRequest request) throws DoValidException {
        String userid = TokenManager.getInstance().getUserByToken(request).getString("id");
        BaseUtils.verifyCode(userid, params.getString("verificationCode"));
        WebUserEntity entity = webUserDao.findById(userid).orElseThrow(() -> new DoValidException("用户不存在：" + userid));

        entity.setPassword(params.getString("newPassword"));
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        webUserDao.save(entity);
        userLogService.insert(new UserLogRequestInsertVm(userid, "用户修改密码：" + entity.getPhone(), "质量小站-修改密码", 1), request);
    }

    /**
     * 验证账号密码
     * @param model
     * @param request
     * @author wkk
     **/
    public void verificationAccountAndPassword(JSONObject model, HttpServletRequest request) throws DoValidException {
        WebUserEntity userEntity = webUserDao.findByPhone(model.getString("phone")).orElseThrow(() -> new DoValidException("用户不存在"));
        if (!userEntity.getPassword().equals(model.getString("password"))) {
            throw new DoValidException("密码不正确");
        }
    }

    /**
     * 手机号是否存在
     * @param phone
     * @author wkk
     **/
    public boolean verificationPhone(String phone) throws DoValidException {
        if (StringUtils.isEmpty(phone)) {
            throw new DoValidException("手机号不能为空");
        }
        return webUserDao.findByPhone(phone).isPresent();
    }

    /**
     * 查询用户基本信息
     **/
    public WebUserInfoEntity findUserByToken(HttpServletRequest request) throws Exception {
        String userid = TokenManager.getInstance().getUserByToken(request).getString("id");
        return webUserInfoDao.findFirstByWebUserId(userid).orElseThrow(() -> new DoValidException("Token无效"));
    }

    /**
     * 修改手机号
     * @param model
     * @param request
     * @author wkk
     **/
    @Transactional(rollbackFor = Exception.class)
    public void updatePhone(JSONObject model, HttpServletRequest request) throws DoValidException {
        // TODO 验证短信验证码

        String userid = TokenManager.getInstance().getUserByToken(request).getString("id");
        WebUserEntity entity = webUserDao.findById(userid).orElseThrow(() -> new DoValidException("用户不存在：" + userid));
        //验证手机号是否重复
        if (model.getString("phone").equals(entity.getPhone())) {
            throw new DoValidException("当前手机号并未修改，无需提交");
        }
        if (verificationPhone(model.getString("phone"))) {
            throw new DoValidException(model.getString("phone") + "手机号已被使用，无法修改");
        }

        //修改手机号
        entity.setPhone(model.getString("phone"));
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        webUserDao.save(entity);

        //修改用户基本信息的手机号
        WebUserInfoEntity userInfoEntity = webUserInfoDao.findFirstByWebUserId(userid).orElseThrow(() -> new DoValidException("系统异常，未找到用户基本信息。"));
        userInfoEntity.setPhone(model.getString("phone"));
        TokenManager.getInstance().updateUpdateIdAndTime(request, userInfoEntity);
        webUserInfoDao.save(userInfoEntity);
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

        WebUserEntity user = webUserDao.findByPhone(model.getPhone()).orElseThrow(() -> new DoValidException("该手机号未注册，请先注册！"));
        if (1 != user.getEnable()) {
            throw new DoValidException("此用户已被停用，如有问题请联系工作人员。");
        }
        String token = TokenManager.getInstance().createToken(Constants.AUTH_ZLXZ, (JSONObject) JSON.toJSON(user), Constants.TOKEN_EXPIRE_TIME);
        SignInResult signInResult = new SignInResult(user.getId(), user.getPhone(), token);
        userLogService.insert(new UserLogRequestInsertVm(JSON.toJSONString(signInResult), "质量小站门户用户" + user.getPhone() + "登录", "登录", 1), request);
        return signInResult;
    }
}
