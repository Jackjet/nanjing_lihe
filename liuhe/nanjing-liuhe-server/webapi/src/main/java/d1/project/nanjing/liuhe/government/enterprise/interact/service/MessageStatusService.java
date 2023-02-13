package d1.project.nanjing.liuhe.government.enterprise.interact.service;

import com.alibaba.fastjson.JSONObject;
import d1.framework.webapi.utils.SpecificationBuilder;
import d1.project.nanjing.liuhe.government.enterprise.interact.dao.MessageStatusDao;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.MessageStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * 消息接收方
 * @author wkk
 **/
@Service
public class MessageStatusService {

    private final MessageStatusDao messageStatusDao;

    public MessageStatusService(MessageStatusDao messageStatusDao) {
        this.messageStatusDao = messageStatusDao;
    }

    /**
     * 列表分页查询
     *
     * @param params 查询参数
     * @author wkk
     **/
    public Page<MessageStatusEntity> findAll(JSONObject params) throws Exception {
        SpecificationBuilder<MessageStatusEntity> builder = new SpecificationBuilder<>();
        Specification specification = builder.init(params)
                .sEqual("messageId", "messageId")
                .sEqual("status", "status")
                .sEqual("state", "state")
                .dOrder("createTime")
                .build();
        return messageStatusDao.findAll(specification, builder.getPageable());
    }


}
