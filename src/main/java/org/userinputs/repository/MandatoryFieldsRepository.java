package org.userinputs.repository;

import org.userinputs.entity.MandatoryFieldsEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.List;


public interface MandatoryFieldsRepository extends CrudRepository<MandatoryFieldsEntity, Long> {
    List<MandatoryFieldsEntity> findByTablename(String tableName);
}
