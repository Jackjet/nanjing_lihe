package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.MessageStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MessageStatusDao extends JpaRepository<MessageStatusEntity, String>, JpaSpecificationExecutor<MessageStatusEntity> {
}
