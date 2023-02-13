package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.CommonlyUsedUrlDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.CommonlyUsedUrlEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonlyUsedUrl.CommonlyUsedUrlInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonlyUsedUrl.CommonlyUsedUrlReleaseVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonlyUsedUrl.CommonlyUsedUrlUpdateVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 常用链接
 * @author wkk
 **/
@Service
public class CommonlyUsedUrlService {

    private final CommonlyUsedUrlDao commonlyUsedUrlDao;

    public CommonlyUsedUrlService(CommonlyUsedUrlDao commonlyUsedUrlDao) {
        this.commonlyUsedUrlDao = commonlyUsedUrlDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<CommonlyUsedUrlEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<CommonlyUsedUrlEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("title", "title")
                .sEqual("enable", "enable")
                .sContain("type", "type")
                .tBetween("releaseTime", "startTime", "startTime" , "yyyy-MM-dd HH:mm:ss")
                .dOrder("type")
                .build();
        return commonlyUsedUrlDao.findAll(specification, builder.getPageable());
    }

    /**
     * 启用的列表数据
     *
     * @author wkk
     **/
    public List<CommonlyUsedUrlEntity> findAll() throws Exception {
        JSONObject params = new JSONObject();
        params.put("enable", "启用");
        SpecificationBuilder<CommonlyUsedUrlEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("enable", "enable")
                .dOrder("createTime")
                .build();
        return commonlyUsedUrlDao.findAll(specification);
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(CommonlyUsedUrlInsetVm model, HttpServletRequest request) throws Exception {
        CommonlyUsedUrlEntity entity = new CommonlyUsedUrlEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        entity.setEnable("禁用");
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        commonlyUsedUrlDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    public void update(CommonlyUsedUrlUpdateVm model, HttpServletRequest request) throws DoValidException {
        CommonlyUsedUrlEntity entity = commonlyUsedUrlDao.findById(model.getId()).orElseThrow(() -> new DoValidException("常用链接ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        commonlyUsedUrlDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("常用链接ID不能为空");
        CommonlyUsedUrlEntity entity = commonlyUsedUrlDao.findById(id).orElseThrow(() -> new DoValidException("删除失败常用链接ID不存在"));
        commonlyUsedUrlDao.delete(entity);
    }

    /**
     * 批量删除
     * @param ids
     * @param request
     **/
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids, HttpServletRequest request) throws Exception{
        if (ids.isEmpty()) throw new DoValidException("常用链接IDS不能为空");
        String[] _ids = ids.split(",");
        List<String> idBatch = new ArrayList<String>();
        for (String id : _ids) {
            idBatch.add(id);
        }
        commonlyUsedUrlDao.deleteBatch(idBatch);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public CommonlyUsedUrlEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("常用链接ID不能为空");
        return commonlyUsedUrlDao.findById(id).orElseThrow(()-> new DoValidException("常用链接ID不存在"));
    }

    /**
     * 发布
     *
     * @param model
     * @param request
     * @return void
     **/
    public void release(CommonlyUsedUrlReleaseVm model, HttpServletRequest request) throws DoValidException {
        CommonlyUsedUrlEntity entity = commonlyUsedUrlDao.findById(model.getId()).orElseThrow(() -> new DoValidException("常用链接ID不存在：" + model.getId()));
        entity.setReleaseTime(Calendar.getInstance());
        entity.setEnable(model.getEnable());
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        commonlyUsedUrlDao.save(entity);
    }
}
