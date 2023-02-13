package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.RotationChartDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.RotationChartEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.RotationChart.RotationChartInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.RotationChart.RotationChartReleaseVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.RotationChart.RotationChartUpdateVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 轮播图
 * @author wkk
 **/
@Service
public class RotationChartService {

    private final RotationChartDao rotationChartDao;

    public RotationChartService(RotationChartDao rotationChartDao) {
        this.rotationChartDao = rotationChartDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<RotationChartEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<RotationChartEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("title", "title")
                .sContain("enable", "enable")
                .dOrder("createTime")
                .build();
        return rotationChartDao.findAll(specification, builder.getPageable());
    }

    /**
     * 启用的轮播图
     *
     * @author wkk
     **/
    public List<RotationChartEntity> findAll() throws Exception {
        JSONObject params = new JSONObject();
        params.put("enable", "启用");
        SpecificationBuilder<RotationChartEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("enable", "enable")
                .dOrder("createTime")
                .build();
        return rotationChartDao.findAll(specification);
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(RotationChartInsetVm model, HttpServletRequest request) throws Exception {
        RotationChartEntity entity = new RotationChartEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        entity.setEnable("禁用");
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        rotationChartDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    public void update(RotationChartUpdateVm model, HttpServletRequest request) throws DoValidException {
        RotationChartEntity entity = rotationChartDao.findById(model.getId()).orElseThrow(() -> new DoValidException("轮播图ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        rotationChartDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("轮播图ID不能为空");
        RotationChartEntity entity = rotationChartDao.findById(id).orElseThrow(() -> new DoValidException("删除失败轮播图ID不存在"));
        rotationChartDao.delete(entity);
    }

    /**
     * 批量删除
     * @param ids
     * @param request
     **/
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids, HttpServletRequest request) throws Exception{
        if (ids.isEmpty()) throw new DoValidException("轮播图IDS不能为空");
        String[] _ids = ids.split(",");
        List<String> idBatch = new ArrayList<String>();
        for (String id : _ids) {
            idBatch.add(id);
        }
        rotationChartDao.deleteBatch(idBatch);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public RotationChartEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("轮播图ID不能为空");
        return rotationChartDao.findById(id).orElseThrow(()-> new DoValidException("轮播图ID不存在"));
    }

    /**
     * 发布
     *
     * @param model
     * @param request
     * @return void
     **/
    public void release(RotationChartReleaseVm model, HttpServletRequest request) throws DoValidException {
        RotationChartEntity entity = rotationChartDao.findById(model.getId()).orElseThrow(() -> new DoValidException("轮播图ID不存在：" + model.getId()));
        entity.setEnable(model.getEnable());
        entity.setReleaseTime(Calendar.getInstance());
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        rotationChartDao.save(entity);
    }
}
