package net.tospay.transaction.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.tospay.transaction.entities.Destination;
import net.tospay.transaction.entities.Source;
import net.tospay.transaction.entities.Transaction;
import net.tospay.transaction.enums.TransactionStatus;
import net.tospay.transaction.enums.UserType;
import net.tospay.transaction.repositories.DestinationRepository;
import net.tospay.transaction.repositories.OffsetBasedPageRequest;
import net.tospay.transaction.repositories.SourceRepository;
import net.tospay.transaction.repositories.TransactionRepository;

@Service
public class CrudService extends BaseService
{
    private static final int TRANSACTION_REFUND_RETRY_LIMIT = 3;

    SourceRepository sourceRepository;

    DestinationRepository destinationRepository;

    TransactionRepository transactionRepository;

    public CrudService(SourceRepository sourceRepository, DestinationRepository destinationRepository,
            TransactionRepository transactionRepository)
    {
        this.sourceRepository = sourceRepository;

        this.destinationRepository = destinationRepository;
        this.transactionRepository = transactionRepository;
    }

    public @NotNull ArrayList<Transaction> fetchTransaction(UUID userId, Integer offset, Integer limit)
    {
        try {
            logger.info(" {}", userId);
            return transactionRepository.findByUserInfoUserId(userId,
                    new OffsetBasedPageRequest(offset, limit,
                            Sort.by(Destination.DATE_CREATED).descending()));
        } catch (Exception e) {
            logger.error(" {}", e);
            return new ArrayList<Transaction>();
        }
    }

    public @NotNull ArrayList<Source> fetchSources(UUID userId, UserType userType, Integer offset, Integer limit)
    {
        try {

            logger.info(" {} {}", userId, userType);
            //   Sort.TypedSort<Source> s=Sort.sort(Source.class);
            //   Sort sort = s.by(Source::getDateModified).descending();
            return sourceRepository.findByUserId(userId.toString(),
                    new OffsetBasedPageRequest(offset, limit,
                            Sort.by(Source.DATE_CREATED).descending()));
        } catch (Exception e) {
            logger.error(" {}", e);
            return new ArrayList<Source>();
        }
    }

    public @NotNull ArrayList<Destination> fetchDestinations(UUID userId, UserType userType, Integer offset,
            Integer limit)
    {
        try {
            logger.info(" {} {}", userId, userType);
            return destinationRepository.findByUserId(userId.toString(),
                    new OffsetBasedPageRequest(offset, limit,
                            Sort.by(Destination.DATE_CREATED).descending()));
        } catch (Exception e) {
            logger.error(" {}", e);
            return new ArrayList<Destination>();
        }
    }



    public @NotNull Optional<net.tospay.transaction.entities.Transaction> fetchTransactionByTransactionIdAndUserId(
            String transactionId,UUID userId)
    {
        try {
            logger.info(" {}", transactionId);
            return transactionRepository.findByTransactionIdAndUserId(transactionId,userId);
        } catch (Exception e) {
            logger.error(" {}", e);
            return Optional.empty();
        }
    }

    public @NotNull List<net.tospay.transaction.entities.Transaction> fetchFailedTransactions(LocalDateTime midnight)
    {
        try {
            logger.info("fetchFailedTransactions");

            return transactionRepository.findByStatusAndDateAndRefundRetryCountLimit(TransactionStatus.FAILED, midnight,
                    TRANSACTION_REFUND_RETRY_LIMIT);
        } catch (Exception e) {
            logger.error(" {}", e);
            return new ArrayList<>();
        }
    }
    public @NotNull List<net.tospay.transaction.entities.Transaction> fetchFailedSourcedTransactions(LocalDateTime midnight)
    {
        try {
            logger.info("fetchFailedSourcedTransactions");

            return transactionRepository.findByStatusAndDateAndRefundRetryCountLimitAndSourceStatus(TransactionStatus.FAILED, midnight,
                    TRANSACTION_REFUND_RETRY_LIMIT);
        } catch (Exception e) {
            logger.error(" {}", e);
            return new ArrayList<>();
        }
    }
}
