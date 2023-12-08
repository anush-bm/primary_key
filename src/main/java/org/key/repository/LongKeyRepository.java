package org.key.repository;

import org.key.domain.LongKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LongKeyRepository extends CrudRepository<LongKey, Long> {

}
