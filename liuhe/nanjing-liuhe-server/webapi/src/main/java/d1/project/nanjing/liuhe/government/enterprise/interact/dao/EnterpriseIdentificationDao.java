package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoIdentificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EnterpriseIdentificationDao extends JpaRepository<EnterpriseInfoIdentificationEntity, String>, JpaSpecificationExecutor<EnterpriseInfoIdentificationEntity> {
    boolean existsByWebUserIdAndIdentification(String s, int i);
}
