package org.userinputs.repository;

import org.userinputs.entity.ExternalServiceEntity;
import org.springframework.data.repository.CrudRepository;


public interface ExternalServiceRepository extends CrudRepository<ExternalServiceEntity, Long> {

    ExternalServiceEntity findByServicename(String serviceID);
}
