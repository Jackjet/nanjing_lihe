package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.ImportantNoticeDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ImportantNoticeEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.ImportantNotice.ImportantNoticeInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.ImportantNotice.ImportantNoticeReleaseVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.ImportantNotice.ImportantNoticeUpdateVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 重要通知
 * @author wkk
 **/
@Service
public class ImportantNoticeService {

    private final ImportantNoticeDao importantNoticeDao;

    public ImportantNoticeService(ImportantNoticeDao importantNoticeDao) {
        this.importantNoticeDao = importantNoticeDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<ImportantNoticeEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<ImportantNoticeEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("title", "title")
                .sEqual("enable", "enable")
                .sEqual("organizationId", "organizationId")
                .sContain("organizationName", "organizationName")
                .sContain("type", "type")
                .tBetween("releaseTime", "startTime", "endTime" , "yyyy-MM-dd HH:mm:ss")
                .dOrder("createTime")
                .build();
        return importantNoticeDao.findAll(specification, builder.getPageable());
    }

    /**
     * 启用的重要通知
     *
     * @author wkk
     **/
    public List<ImportantNoticeEntity> findAll() throws Exception {
        JSONObject params = new JSONObject();
        params.put("enable", "启用");
        SpecificationBuilder<ImportantNoticeEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("enable", "enable")
                .dOrder("createTime")
                .build();
        return importantNoticeDao.findAll(specification);
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(ImportantNoticeInsetVm model, HttpServletRequest request) throws Exception {
        ImportantNoticeEntity entity = new ImportantNoticeEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        entity.setEnable("禁用");
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        importantNoticeDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    public void update(ImportantNoticeUpdateVm model, HttpServletRequest request) throws DoValidException {
        ImportantNoticeEntity entity = importantNoticeDao.findById(model.getId()).orElseThrow(() -> new DoValidException("重要通知ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        importantNoticeDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("重要通知ID不能为空");
        ImportantNoticeEntity entity = importantNoticeDao.findById(id).orElseThrow(() -> new DoValidException("删除失败重要通知ID不存在"));
        importantNoticeDao.delete(entity);
    }

    /**
     * 批量删除
     * @param ids
     * @param request
     **/
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids, HttpServletRequest request) throws Exception{
        if (ids.isEmpty()) throw new DoValidException("重要通知IDS不能为空");
        String[] _ids = ids.split(",");
        List<String> idBatch = new ArrayList<String>();
        for (String id : _ids) {
            idBatch.add(id);
        }
        importantNoticeDao.deleteBatch(idBatch);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public ImportantNoticeEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("重要通知ID不能为空");
        return importantNoticeDao.findById(id).orElseThrow(()-> new DoValidException("重要通知ID不存在"));
    }

    /**
     * 发布
     *
     * @param model
     * @param request
     * @return void
     **/
    public void release(ImportantNoticeReleaseVm model, HttpServletRequest request) throws DoValidException {
        ImportantNoticeEntity entity = importantNoticeDao.findById(model.getId()).orElseThrow(() -> new DoValidException("重要通知ID不存在：" + model.getId()));
        entity.setReleaseTime(Calendar.getInstance());
        entity.setEnable(model.getEnable());
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        importantNoticeDao.save(entity);
    }
}
