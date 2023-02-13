package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.HomeSettledEnterpriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HomeSettledEnterpriseDao extends JpaRepository<HomeSettledEnterpriseEntity, String>, JpaSpecificationExecutor<HomeSettledEnterpriseEntity> {
}
