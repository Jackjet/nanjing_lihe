package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.ContactYellowPageDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ContactYellowPageEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.ContactYellowPage.ContactYellowPageInsetVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.ContactYellowPage.ContactYellowPageReleaseVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.ContactYellowPage.ContactYellowPageUpdateVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 联系人黄页
 * @author wkk
 **/
@Service
public class ContactYellowPageService {

    private final ContactYellowPageDao contactYellowPageDao;

    public ContactYellowPageService(ContactYellowPageDao contactYellowPageDao) {
        this.contactYellowPageDao = contactYellowPageDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<ContactYellowPageEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<ContactYellowPageEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("name", "name")
                .sEqual("enable", "enable")
                .sEqual("organizationId", "organizationId")
                .sContain("organizationName", "organizationName")
                .tBetween("releaseTime", "startTime", "endTime" , "yyyy-MM-dd HH:mm:ss")
                .dOrder("createTime")
                .build();
        return contactYellowPageDao.findAll(specification, builder.getPageable());
    }

    /**
     * 启用的列表数据
     *
     * @author wkk
     **/
    public List<ContactYellowPageEntity> findAll() throws Exception {
        JSONObject params = new JSONObject();
        params.put("enable", "启用");
        SpecificationBuilder<ContactYellowPageEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("enable", "enable")
                .dOrder("createTime")
                .build();
        return contactYellowPageDao.findAll(specification);
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    public void save(ContactYellowPageInsetVm model, HttpServletRequest request) throws Exception {
        ContactYellowPageEntity entity = new ContactYellowPageEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        entity.setEnable("禁用");
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        contactYellowPageDao.save(entity);
    }

    /**
     * 更新
     *
     * @param model
     * @param request
     * @return void
     **/
    public void update(ContactYellowPageUpdateVm model, HttpServletRequest request) throws DoValidException {
        ContactYellowPageEntity entity = contactYellowPageDao.findById(model.getId()).orElseThrow(() -> new DoValidException("联系人黄页ID不存在：" + model.getId()));
        BaseUtils.copyProperties(model, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        contactYellowPageDao.save(entity);
    }

    /**
     * 删除
     * @param id
     **/
    public void delete(String id) throws DoValidException {
        if (id.isEmpty()) throw new DoValidException("联系人黄页ID不能为空");
        ContactYellowPageEntity entity = contactYellowPageDao.findById(id).orElseThrow(() -> new DoValidException("删除失败联系人黄页ID不存在"));
        contactYellowPageDao.delete(entity);
    }

    /**
     * 批量删除
     * @param ids
     * @param request
     **/
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String ids, HttpServletRequest request) throws Exception{
        if (ids.isEmpty()) throw new DoValidException("联系人黄页IDS不能为空");
        String[] _ids = ids.split(",");
        List<String> idBatch = new ArrayList<String>();
        for (String id : _ids) {
            idBatch.add(id);
        }
        contactYellowPageDao.deleteBatch(idBatch);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public ContactYellowPageEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("联系人黄页ID不能为空");
        return contactYellowPageDao.findById(id).orElseThrow(()-> new DoValidException("联系人黄页ID不存在"));
    }

    /**
     * 发布
     *
     * @param model
     * @param request
     * @return void
     **/
    public void release(ContactYellowPageReleaseVm model, HttpServletRequest request) throws DoValidException {
        ContactYellowPageEntity entity = contactYellowPageDao.findById(model.getId()).orElseThrow(() -> new DoValidException("联系人黄页ID不存在：" + model.getId()));
        entity.setReleaseTime(Calendar.getInstance());
        entity.setEnable(model.getEnable());
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        contactYellowPageDao.save(entity);
    }
}
