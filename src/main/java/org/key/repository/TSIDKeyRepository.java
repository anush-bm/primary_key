package org.key.repository;

import org.key.domain.TSIDKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TSIDKeyRepository extends CrudRepository<TSIDKey, Long> {

}
