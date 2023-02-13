package d1.project.nanjing.liuhe.quality.small.station.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.quality.small.station.dao.ServiceProviderInfoDao;
import d1.project.nanjing.liuhe.quality.small.station.entity.ServiceProviderInfoEntity;
import d1.project.nanjing.liuhe.quality.small.station.model.ServiceProviderInfo.ServiceProviderInfoInsetVm;
import d1.project.nanjing.liuhe.quality.small.station.model.ServiceProviderInfo.ServiceProviderInfoReleaseVm;
import d1.project.nanjing.liuhe.quality.small.station.model.ServiceProviderInfo.ServiceProviderInfoUpdateVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 服务商信息
 * @author wkk
 **/
@Service
public class ServiceProviderInfoService {

    private final ServiceProviderInfoDao serviceProviderInfoDao;

    public ServiceProviderInfoService(ServiceProviderInfoDao serviceProviderInfoDao) {
        this.serviceProviderInfoDao = serviceProviderInfoDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<ServiceProviderInfoEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<ServiceProviderInfoEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("title", "title")
                .sEqual("enable", "enable")
                .sContain("createById", "createById")
                .tBetween("releaseTime", "startDate", "endDate" , "yyyy-MM-dd")
                .dOrder("createTime")
                .build();
        return serviceProviderInfoDao.findAll(specification, builder.getPageable());
    }

    /**
     * 首页服务商信息
     *
     * @author wkk
     **/
    public List<ServiceProviderInfoEntity> findAllByProvider() throws Exception {
        JSONObject params = new JSONObject();
        params.put("enable", "启用");
        SpecificationBuilder<ServiceProviderInfoEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sEqual("enable", "enable")
                .dOrder("createTime")
                .build();
        return serviceProviderInfoDao.findAll(specification);
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(ServiceProviderInfoInsetVm model, HttpServletRequest request) throws Exception {
        ServiceProviderInfoEntity entity = new ServiceProviderInfoEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        entity.setEnable("禁用");
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        serviceProviderInfoDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    public void update(ServiceProviderInfoUpdateVm model, HttpServletRequest request) throws DoValidException {
        ServiceProviderInfoEntity entity = serviceProviderInfoDao.findById(model.getId()).orElseThrow(() -> new DoValidException("服务商信息ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        serviceProviderInfoDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("新闻公告ID不能为空");
        ServiceProviderInfoEntity entity = serviceProviderInfoDao.findById(id).orElseThrow(() -> new DoValidException("删除失败服务商信息ID不存在"));
        serviceProviderInfoDao.delete(entity);
    }

    /**
     * 批量删除
     * @param ids
     * @param request
     **/
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids, HttpServletRequest request) throws Exception{
        if (ids.isEmpty()) throw new DoValidException("服务商信息IDS不能为空");
        String[] _ids = ids.split(",");
        List<String> idBatch = new ArrayList<String>();
        for (String id : _ids) {
            idBatch.add(id);
        }
        serviceProviderInfoDao.deleteBatch(idBatch);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public ServiceProviderInfoEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("服务商信息ID不能为空");
        return serviceProviderInfoDao.findById(id).orElseThrow(()-> new DoValidException("服务商信息ID不存在"));
    }

    /**
     * 发布
     *
     * @param model
     * @param request
     * @return void
     **/
    @Transactional(rollbackFor = Exception.class)
    public void release(ServiceProviderInfoReleaseVm model, HttpServletRequest request) throws DoValidException {
        if (model.getId().isEmpty()) throw new DoValidException("服务商信息ID不能为空");
        ServiceProviderInfoEntity entity = serviceProviderInfoDao.findById(model.getId()).orElseThrow(() -> new DoValidException("服务商信息ID不存在：" + model.getId()));
        entity.setReleaseTime(Calendar.getInstance());
        entity.setEnable(model.getEnable());
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        serviceProviderInfoDao.save(entity);
    }
}
