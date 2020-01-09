package net.tospay.transaction.repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.tospay.transaction.entities.Source;

@Repository
public interface SourceRepository extends BaseRepositoryInterface<Source, UUID>
{
    Optional<Source> findById(UUID uuid);

    @Query(value = "select * from sources where payload ->'account'->>'user_id' = :userId", nativeQuery = true)
    ArrayList<Source> findByUserId(String userId, Pageable p);

    @Query(value = "select * from sources where payload ->'account'->>'user_id' = :userId and date_created>=:dateCreatedFrom and date_created<:dateCreatedTo", nativeQuery = true)
    ArrayList<Source> findByUserId(String userId, LocalDateTime dateCreatedFrom, LocalDateTime dateCreatedTo);
}
