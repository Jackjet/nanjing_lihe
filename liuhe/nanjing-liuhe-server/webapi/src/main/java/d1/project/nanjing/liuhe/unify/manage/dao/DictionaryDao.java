package d1.project.nanjing.liuhe.unify.manage.dao;

import d1.project.nanjing.liuhe.unify.manage.entity.DictionaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DictionaryDao extends JpaRepository<DictionaryEntity, String>, JpaSpecificationExecutor<DictionaryEntity> {
    List<DictionaryEntity> findAllByType(String type);
}
