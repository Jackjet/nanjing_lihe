package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.CommonProblemDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.CommonProblemEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonProblem.CommonProblemInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonProblem.CommonProblemReleaseVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.CommonProblem.CommonProblemUpdateVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.CommonProblemView;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 常见问题
 * @author wkk
 **/
@Service
public class CommonProblemService {

    private final CommonProblemDao commonProblemDao;

    public CommonProblemService(CommonProblemDao commonProblemDao) {
        this.commonProblemDao = commonProblemDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<CommonProblemEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<CommonProblemEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("title", "title")
                .sEqual("enable", "enable")
                .sContain("type", "type")
                .tBetween("releaseTime", "startTime", "endTime" , "yyyy-MM-dd HH:mm:ss")
                .dOrder("createTime")
                .build();
        return commonProblemDao.findAll(specification, builder.getPageable());
    }

    /**
     * 启用的列表数据
     *
     * @author wkk
     **/
    public List<CommonProblemView> findAll() throws Exception {
        return commonProblemDao.findAllByEnableOrderByCreateTime("启用");
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(CommonProblemInsetVm model, HttpServletRequest request) throws Exception {
        CommonProblemEntity entity = new CommonProblemEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        entity.setEnable("禁用");
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        commonProblemDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    public void update(CommonProblemUpdateVm model, HttpServletRequest request) throws DoValidException {
        CommonProblemEntity entity = commonProblemDao.findById(model.getId()).orElseThrow(() -> new DoValidException("常见问题ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        commonProblemDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("常见问题ID不能为空");
        CommonProblemEntity entity = commonProblemDao.findById(id).orElseThrow(() -> new DoValidException("删除失败常见问题ID不存在"));
        commonProblemDao.delete(entity);
    }

    /**
     * 批量删除
     * @param ids
     * @param request
     **/
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids, HttpServletRequest request) throws Exception{
        if (ids.isEmpty()) throw new DoValidException("常见问题IDS不能为空");
        String[] _ids = ids.split(",");
        List<String> idBatch = new ArrayList<String>();
        for (String id : _ids) {
            idBatch.add(id);
        }
        commonProblemDao.deleteBatch(idBatch);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public CommonProblemEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("常见问题ID不能为空");
        return commonProblemDao.findById(id).orElseThrow(()-> new DoValidException("常见问题ID不存在"));
    }

    /**
     * 发布
     *
     * @param model
     * @param request
     * @return void
     **/
    public void release(CommonProblemReleaseVm model, HttpServletRequest request) throws DoValidException {
        CommonProblemEntity entity = commonProblemDao.findById(model.getId()).orElseThrow(() -> new DoValidException("常见问题ID不存在：" + model.getId()));
        entity.setReleaseTime(Calendar.getInstance());
        entity.setEnable(model.getEnable());
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        commonProblemDao.save(entity);
    }
}
