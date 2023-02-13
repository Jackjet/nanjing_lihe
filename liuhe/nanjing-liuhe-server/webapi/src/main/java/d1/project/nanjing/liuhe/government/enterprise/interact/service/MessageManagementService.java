package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.configuration.DoValidException;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.framework.webapi.utils.TokenManager;
import d1.project.nanjing.liuhe.common.Constants;
import d1.project.nanjing.liuhe.common.utils.BaseUtils;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.EnterpriseInfoDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.MessageManagementDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.MessageStatusDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.MessageViewDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.MessageManagementEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.MessageStatusEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoEntity;
import d1.project.nanjing.liuhe.government.enterprise.interact.model.Message.MessageManagementInsertVm;
import d1.project.nanjing.liuhe.government.enterprise.interact.view.MessageView;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 消息管理
 * @author wkk
 **/
@Service
public class MessageManagementService {

    private final MessageManagementDao messageManagementDao;
    private final EnterpriseInfoDao enterpriseInfoDao;
    private final MessageStatusDao messageStatusDao;
    private final MessageViewDao messageViewDao;

    public MessageManagementService(MessageManagementDao messageManagementDao, EnterpriseInfoDao enterpriseInfoDao, MessageStatusDao messageStatusDao, MessageViewDao messageViewDao) {
        this.messageManagementDao = messageManagementDao;
        this.enterpriseInfoDao = enterpriseInfoDao;
        this.messageStatusDao = messageStatusDao;
        this.messageViewDao = messageViewDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<MessageManagementEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<MessageManagementEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("title", "title")
                .sContain("receiveIds", "receiveIds")
                .tBetween("createTime", "startTime", "endTime" , "yyyy-MM-dd HH:mm:ss")
                .dOrder("createTime")
                .build();
        return messageManagementDao.findAll(specification, builder.getPageable());
    }

    /**
     * 门户-列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<MessageView> findAllByEnterprise(JSONObject params) throws Exception {
        SpecificationBuilder<MessageView> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sContain("receiveIds", "receiveId")
                .dOrder("createTime")
                .build();
        return messageViewDao.findAll(specification, builder.getPageable());
    }

    /**
     * 保存
     *
     * @param model
     * @param request
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(MessageManagementInsertVm model, HttpServletRequest request) throws Exception {
        String messageId = BaseUtils.generate32Id();//消息id
        String userId = TokenManager.getInstance().getUserByToken(request).getString("id");//当前操作人id
        List<MessageStatusEntity> messageAll = new ArrayList<>();
        List<EnterpriseInfoEntity> all = "全部".equals(model.getReceiveIds()) ? enterpriseInfoDao.findAll() : enterpriseInfoDao.findAllByIdIn(Arrays.asList(model.getReceiveIds().split(",")));
        if (StringUtils.isEmpty(all)) throw new DoValidException("未找到企业信息");
        all.forEach(e -> {
            MessageStatusEntity messageStatusEntity = new MessageStatusEntity();
            messageStatusEntity.setId(BaseUtils.generate32Id());
            messageStatusEntity.setMessageId(messageId);
            messageStatusEntity.setReceiveId(e.getId());
            messageStatusEntity.setReceiveName(e.getEnterpriseName());
            messageStatusEntity.setStatus(Constants.READ_0);
            messageStatusEntity.setCreateById(userId);
            messageStatusEntity.setCreateTime(Calendar.getInstance());
            messageAll.add(messageStatusEntity);
        });
        messageStatusDao.saveAll(messageAll);

        MessageManagementEntity entity = new MessageManagementEntity();
        BaseUtils.copyProperties(model, entity);
        entity.setId(messageId);
        entity.setReceiveIds(all.stream().map(EnterpriseInfoEntity::getId).collect(Collectors.joining(",")));
        entity.setSource("管理员");
        TokenManager.getInstance().updateCreateIdAndTime(request, entity);
        messageManagementDao.save(entity);
    }

    /**
     * 根据id查询实体
     * @param id
     **/
    public MessageManagementEntity findById(String id) throws Exception {
        if (id.isEmpty()) throw new DoValidException("消息ID不能为空");
        return messageManagementDao.findById(id).orElseThrow(()-> new DoValidException("消息ID不存在"));
    }
}
