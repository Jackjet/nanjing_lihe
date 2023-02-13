package d1.project.nanjing.liuhe.quality.small.station.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.quality.small.station.dao.ConsultDao;
import d1.project.nanjing.liuhe.quality.small.station.dao.ConsultReplyDao;
import d1.project.nanjing.liuhe.quality.small.station.entity.ConsultEntity;
import d1.project.nanjing.liuhe.quality.small.station.entity.ConsultReplyEntity;
import d1.project.nanjing.liuhe.quality.small.station.model.Consult.ConsultFindByIdVm;
import d1.project.nanjing.liuhe.quality.small.station.model.Consult.ConsultInsetVm;
import d1.project.nanjing.liuhe.quality.small.station.model.Consult.ConsultLeavingMessageVm;
import d1.project.nanjing.liuhe.quality.small.station.model.Consult.ConsultReplyVm;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * 我要咨询
 * @author wkk
 **/
@Service
public class ConsultService {

    private final ConsultDao consultDao;
    private final ConsultReplyDao consultReplyDao;

    public ConsultService(ConsultDao consultDao, ConsultReplyDao consultReplyDao) {
        this.consultDao = consultDao;
        this.consultReplyDao = consultReplyDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<ConsultEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<ConsultEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("enterpriseName", "enterpriseName")
                .sContain("liaisonName", "liaisonName")
                .sContain("liaisonTel", "liaisonTel")
                .sEqual("replyStatus", "replyStatus")
                .tBetween("createTime", "startDate", "endDate" , "yyyy-MM-dd")
                .dOrder("updateTime")
                .build();
        return consultDao.findAll(specification, builder.getPageable());
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(ConsultInsetVm model, HttpServletRequest request) throws Exception {
        String id = TokenManager.getInstance().getUserByToken(request).getString("id");
        ConsultEntity entity = new ConsultEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(BaseUtils.generate32Id());
        entity.setReplyStatus(Constants.REPLY_STATUS_0);
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        consultDao.save(entity);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public ConsultFindByIdVm findById(String id) throws DoValidException {
        ConsultFindByIdVm vm = new ConsultFindByIdVm();
        if (id.isEmpty()) throw new DoValidException("咨询ID不能为空");
        ConsultEntity entity = consultDao.findById(id).orElseThrow(() -> new DoValidException("咨询信息不存在"));
        BaseUtils.copyProperties(entity, vm);
        vm.setReplyList(consultReplyDao.findByConsultIdOrderByCreateTimeAsc(id));
        return vm;
    }

    /**
     * 回复消息
     * @param vm
     **/
    @Transactional(rollbackFor = Exception.class)
    public void replyMessage(ConsultReplyVm vm, HttpServletRequest request) throws DoValidException {
        ConsultEntity entity = consultDao.findById(vm.getId()).orElseThrow(() -> new DoValidException("咨询信息不存在"));
        entity.setReplyStatus(Constants.REPLY_STATUS_1);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        consultDao.save(entity);

        ConsultReplyEntity replyEntity = new ConsultReplyEntity();
        replyEntity.setId(BaseUtils.generate32Id());
        replyEntity.setReplyUserName(TokenManager.getInstance().getUserByToken(request).getString("name"));
        replyEntity.setReplyMessage(vm.getReplyMessage());
        replyEntity.setConsultId(vm.getId());
        TokenManager.getInstance().updateCreateIdAndTime(request, replyEntity);
        consultReplyDao.save(replyEntity);
    }

    /**
     * 企业留言
     * @param vm
     **/
    @Transactional(rollbackFor = Exception.class)
    public void leavingMessage(ConsultLeavingMessageVm vm, HttpServletRequest request) throws DoValidException {
        ConsultEntity entity = consultDao.findById(vm.getId()).orElseThrow(() -> new DoValidException("咨询信息不存在"));
        entity.setReplyStatus(Constants.REPLY_STATUS_0);
        TokenManager.getInstance().updateUpdateIdAndTime(request, entity);
        consultDao.save(entity);

        ConsultReplyEntity replyEntity = new ConsultReplyEntity();
        replyEntity.setId(BaseUtils.generate32Id());
        replyEntity.setReplyUserName(entity.getEnterpriseName());
        replyEntity.setConsultId(vm.getId());
        replyEntity.setReplyMessage(vm.getReplyMessage());
        TokenManager.getInstance().updateCreateIdAndTime(request, replyEntity);
        consultReplyDao.save(replyEntity);
    }
}
