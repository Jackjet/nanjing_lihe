package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.enterprise.EnterpriseInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface EnterpriseInfoDao extends JpaRepository<EnterpriseInfoEntity, String>, JpaSpecificationExecutor<EnterpriseInfoEntity> {
    Optional<EnterpriseInfoEntity> findFirstByWebUserId(String userId);

    List<EnterpriseInfoEntity> findAllByIdIn(List<String> ids);

    boolean existsByEnterpriseName(String enterpriseName);
}
