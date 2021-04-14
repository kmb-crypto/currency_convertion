package main.repository;

import main.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    @Query(value = "SELECT DISTINCT user_id FROM transactions " +
            "WHERE (amount >= :amount AND from_currency = :currency) " +
            "OR (exchange_amount >= :amount AND to_currency = :currency)", nativeQuery = true)
    List<Integer> findUsersIdByAmountAndCurrency(@Param("currency") final String currency,
                                                 @Param("amount") final Long amount);


    @Query(value = "SELECT user_id FROM transactions " +
            "WHERE from_currency = :currency " +
            "GROUP BY user_id " +
            "HAVING sum(amount) >= :sum ", nativeQuery = true)
    List<Integer> findUsersIdWithSum(@Param("currency") final String currency,
                                     @Param("sum") final Long sum);

}
