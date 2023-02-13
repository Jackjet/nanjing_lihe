package d1.project.nanjing.liuhe.quality.small.station.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.quality.small.station.dao.HomeRotationChartDao;
import d1.project.nanjing.liuhe.quality.small.station.entity.HomeRotationChartEntity;
import d1.project.nanjing.liuhe.quality.small.station.model.HomeRotationChart.HomeRotationChartInsetVm;
import d1.project.nanjing.liuhe.quality.small.station.model.HomeRotationChart.HomeRotationChartUpdateVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页轮播图
 * @author wkk
 **/
@Service
public class HomeRotationChartService {

    private final HomeRotationChartDao homeRotationChartDao;

    public HomeRotationChartService(HomeRotationChartDao homeRotationChartDao) {
        this.homeRotationChartDao = homeRotationChartDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<HomeRotationChartEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<HomeRotationChartEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("name", "name")
                .sContain("enable", "enable")
                .dOrder("createTime")
                .build();
        return homeRotationChartDao.findAll(specification, builder.getPageable());
    }

    /**
     * 启用的轮播图
     *
     * @author wkk
     **/
    public List<HomeRotationChartEntity> findAll() throws Exception {
        JSONObject params = new JSONObject();
        params.put("enable", "启用");
        SpecificationBuilder<HomeRotationChartEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("enable", "enable")
                .dOrder("createTime")
                .build();
        return homeRotationChartDao.findAll(specification);
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(HomeRotationChartInsetVm model, HttpServletRequest request) throws Exception {
        HomeRotationChartEntity entity = new HomeRotationChartEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        homeRotationChartDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    public void update(HomeRotationChartUpdateVm model, HttpServletRequest request) throws DoValidException {
        HomeRotationChartEntity entity = homeRotationChartDao.findById(model.getId()).orElseThrow(() -> new DoValidException("轮播图ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        homeRotationChartDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("轮播图ID不能为空");
        HomeRotationChartEntity entity = homeRotationChartDao.findById(id).orElseThrow(() -> new DoValidException("删除失败轮播图ID不存在"));
        homeRotationChartDao.delete(entity);
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
        homeRotationChartDao.deleteBatch(idBatch);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public HomeRotationChartEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("轮播图ID不能为空");
        return homeRotationChartDao.findById(id).orElseThrow(()-> new DoValidException("轮播图ID不存在"));
    }
}
