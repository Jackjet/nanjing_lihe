package d1.project.nanjing.liuhe.government.enterprise.interact.dao;

import d1.project.nanjing.liuhe.government.enterprise.interact.entity.ZqhdWebUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ZqhdWebUserDao extends JpaRepository<ZqhdWebUserEntity, String>, JpaSpecificationExecutor<ZqhdWebUserEntity> {

    Optional<ZqhdWebUserEntity> findByPhone(String phone);

    Boolean existsByPhoneAndPassword(String phone, String password);

    Boolean existsByPhone(String phone);
}
