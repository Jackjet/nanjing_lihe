package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.OnlineClassroomDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.OnlineClassroomViewDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.OnlineClassroomEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.OnlineClassroom.OnlineClassroomInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.OnlineClassroom.OnlineClassroomReleaseVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.OnlineClassroom.OnlineClassroomUpdateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.OnlineClassroomView;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 线上课堂
 * @author wkk
 **/
@Service
public class OnlineClassroomService {

    private final OnlineClassroomDao onlineClassroomDao;
    private final OnlineClassroomViewDao onlineClassroomViewDao;

    public OnlineClassroomService(OnlineClassroomDao onlineClassroomDao, OnlineClassroomViewDao onlineClassroomViewDao) {
        this.onlineClassroomDao = onlineClassroomDao;
        this.onlineClassroomViewDao = onlineClassroomViewDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<OnlineClassroomEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<OnlineClassroomEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("title", "title")
                .sEqual("enable", "enable")
                .sContain("type", "type")
                .sEqual("organizationId", "organizationId")
                .sContain("organizationName", "organizationName")
                .tBetween("releaseTime", "startTime", "endTime" , "yyyy-MM-dd HH:mm:ss")
                .dOrder("createTime")
                .build();
        return onlineClassroomDao.findAll(specification, builder.getPageable());
    }

    /**
     * 启用的列表数据
     *
     * @author wkk
     **/
    public Page<OnlineClassroomView> findAllByEnable(JSONObject params) throws Exception {
        params.put("enable", "启用");
        SpecificationBuilder<OnlineClassroomEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("enable", "enable")
                .sEqual("type", "type")
                .tBetween("releaseTime", "startTime", "endTime" , "yyyy-MM-dd HH:mm:ss")
                .dOrder("releaseTime")
                .build();
        return onlineClassroomViewDao.findAll(specification, builder.getPageable());
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(OnlineClassroomInsetVm model, HttpServletRequest request) throws Exception {
        OnlineClassroomEntity entity = new OnlineClassroomEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        entity.setEnable("禁用");
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        onlineClassroomDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    public void update(OnlineClassroomUpdateVm model, HttpServletRequest request) throws DoValidException {
        OnlineClassroomEntity entity = onlineClassroomDao.findById(model.getId()).orElseThrow(() -> new DoValidException("线上课堂ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        onlineClassroomDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("线上课堂ID不能为空");
        OnlineClassroomEntity entity = onlineClassroomDao.findById(id).orElseThrow(() -> new DoValidException("删除失败线上课堂ID不存在"));
        onlineClassroomDao.delete(entity);
    }

    /**
     * 批量删除
     * @param ids
     * @param request
     **/
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids, HttpServletRequest request) throws Exception{
        if (ids.isEmpty()) throw new DoValidException("线上课堂IDS不能为空");
        String[] _ids = ids.split(",");
        List<String> idBatch = new ArrayList<String>();
        for (String id : _ids) {
            idBatch.add(id);
        }
        onlineClassroomDao.deleteBatch(idBatch);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public OnlineClassroomEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("线上课堂ID不能为空");
        return onlineClassroomDao.findById(id).orElseThrow(()-> new DoValidException("线上课堂ID不存在"));
    }

    /**
     * 发布
     *
     * @param model
     * @param request
     * @return void
     **/
    public void release(OnlineClassroomReleaseVm model, HttpServletRequest request) throws DoValidException {
        OnlineClassroomEntity entity = onlineClassroomDao.findById(model.getId()).orElseThrow(() -> new DoValidException("线上课堂ID不存在：" + model.getId()));
        entity.setReleaseTime(Calendar.getInstance());
        entity.setEnable(model.getEnable());
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        onlineClassroomDao.save(entity);
    }
}
