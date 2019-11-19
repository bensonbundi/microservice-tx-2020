package net.tospay.transaction.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.tospay.transaction.entities.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, UUID>
{
    Optional<Transaction> findById(UUID uuid);

    Optional<Transaction> findByTransactionId(String transactionId);
}
