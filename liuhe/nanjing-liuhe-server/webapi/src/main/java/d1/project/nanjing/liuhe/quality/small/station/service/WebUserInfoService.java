package d1.project.nanjing.liuhe.quality.small.station.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.quality.small.station.dao.WebUserInfoDao;
import d1.project.nanjing.liuhe.quality.small.station.entity.WebUserInfoEntity;
import d1.project.nanjing.liuhe.quality.small.station.model.WebUser.WebUserUpdateAllEnableVm;
import d1.project.nanjing.liuhe.quality.small.station.model.WebUser.WebUserUpdateEnableVm;
import d1.project.nanjing.liuhe.quality.small.station.model.WebUser.WebUserUpdateVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebUserInfoService {

    private final WebUserInfoDao webUserInfoDao;
    private final WebUserService webUserService;

    public WebUserInfoService(WebUserInfoDao webUserInfoDao, WebUserService webUserService) {
        this.webUserInfoDao = webUserInfoDao;
        this.webUserService = webUserService;
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
                .sContain("province", "province")
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
     * 批量更新所有启用
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateAllEnable(WebUserUpdateAllEnableVm model, HttpServletRequest request) throws DoValidException {
        List<String> ids = new ArrayList<>();
        List<WebUserInfoEntity> webUserInfoEntitys = webUserInfoDao.findAllById(model.getIds()).stream().peek(webUserInfo -> webUserInfo.setEnable(model.getEnable())).collect(Collectors.toList());
        webUserInfoDao.saveAll(webUserInfoEntitys);

        webUserInfoEntitys.forEach(webUserInfo -> ids.add(webUserInfo.getWebUserId()));
        webUserService.updateAllEnable(ids, model.getEnable(), request);
    }

    /**
     * 更新启用禁用
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateEnable(WebUserUpdateEnableVm model, HttpServletRequest request) throws DoValidException {
        WebUserInfoEntity webUserInfoEntity = webUserInfoDao.findById(model.getId()).orElseThrow(() -> new DoValidException("用户ID不存在"));
        webUserInfoEntity.setEnable(model.getEnable());
        webUserInfoDao.save(webUserInfoEntity);
        webUserService.updateEnable(webUserInfoEntity.getWebUserId(), model.getEnable() , request);
    }

    /**
     * 更新用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(WebUserUpdateVm model, HttpServletRequest request) throws DoValidException {
        WebUserInfoEntity userinfo = webUserInfoDao.findFirstByWebUserId(TokenManager.getInstance().getUserByToken(request).getString("id")).orElseThrow(()-> new DoValidException("用户不存在"));
        BaseUtils.copyProperties(model, userinfo);
        webUserInfoDao.save(userinfo);
    }
}
