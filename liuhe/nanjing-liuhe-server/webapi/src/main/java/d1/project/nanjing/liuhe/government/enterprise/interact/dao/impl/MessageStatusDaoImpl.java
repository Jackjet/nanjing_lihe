package d1.project.nanjing.liuhe.government.enterprise.interact.dao.impl;

import d1.framework.webapi.dao.BatchExecuteRepository;
import d1.project.nanjing.liuhe.government.enterprise.interact.entity.MessageStatusEntity;

import javax.persistence.EntityManager;

public class MessageStatusDaoImpl  extends BatchExecuteRepository<MessageStatusEntity, String> {
    public MessageStatusDaoImpl(EntityManager entityManager) {
        super(MessageStatusEntity.class, entityManager);
    }
}
