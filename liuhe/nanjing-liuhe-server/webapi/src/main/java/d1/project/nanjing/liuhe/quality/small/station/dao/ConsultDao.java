package d1.project.nanjing.liuhe.quality.small.station.dao;

import d1.project.nanjing.liuhe.quality.small.station.entity.ConsultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsultDao extends JpaRepository<ConsultEntity, String>, JpaSpecificationExecutor<ConsultEntity> {
}
