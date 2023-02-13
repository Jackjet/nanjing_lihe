package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.SettledEnterpriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SettledEnterpriseDao extends JpaRepository<SettledEnterpriseEntity, String>, JpaSpecificationExecutor<SettledEnterpriseEntity> {
}
