package d1.project.nanjing.liuhe.quality.small.station.dao;

import d1.project.nanjing.liuhe.quality.small.station.entity.WebUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface WebUserDao extends JpaRepository<WebUserEntity, String>, JpaSpecificationExecutor<WebUserEntity> {

    Optional<WebUserEntity> findByPhone(String phone);

}
