package d1.project.nanjing.liuhe.quality.small.station.dao;

import d1.project.nanjing.liuhe.quality.small.station.entity.WebUserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface WebUserInfoDao extends JpaRepository<WebUserInfoEntity, String>, JpaSpecificationExecutor<WebUserInfoEntity> {

    Optional<WebUserInfoEntity> findFirstByWebUserId(String webUserId);

    Optional<WebUserInfoEntity> findByPhone(String phone);

}
