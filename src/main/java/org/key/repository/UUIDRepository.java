package org.key.repository;

import java.util.UUID;

import org.key.domain.UUIDKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UUIDRepository extends CrudRepository<UUIDKey, UUID> {

}
