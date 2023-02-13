package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.MessageManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MessageManagementDao extends JpaRepository<MessageManagementEntity, String>, JpaSpecificationExecutor<MessageManagementEntity> {
}
