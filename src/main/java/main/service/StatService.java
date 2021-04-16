package main.service;

import main.api.response.StatByTransactionResponse;
import main.api.response.StatTransactionsRankResponse;
import main.api.response.StatUsersWithSumResponse;
import main.model.Pair;
import main.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatService {
    private final TransactionRepository transactionRepository;
    private static final Long CENTS_IN_UNIT = 100L;

    public StatService(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ResponseEntity<StatByTransactionResponse> getStatByTransaction(final String currency, final String amount) {
        List<Integer> userId = transactionRepository.findUsersIdByAmountAndCurrency(currency,
                Long.parseLong(amount) * CENTS_IN_UNIT);

        return new ResponseEntity<>(
                new StatByTransactionResponse(Integer.parseInt(amount), userId), HttpStatus.OK);
    }

    public ResponseEntity<StatUsersWithSumResponse> getStatUsersWithSum(final String currency, final String sum) {
        List<Integer> userId = transactionRepository.findUsersIdWithSum(currency,
                Long.parseLong(sum) * CENTS_IN_UNIT);

        return new ResponseEntity<>(
                new StatUsersWithSumResponse(Integer.parseInt(sum), userId), HttpStatus.OK);
    }

    public ResponseEntity<StatTransactionsRankResponse> getTransactionsRank() {
        List<Pair> transactionsRank = transactionRepository.getTransactionsRank();
        return new ResponseEntity<>(transactionRank2Response(transactionsRank), HttpStatus.OK);
    }

    private StatTransactionsRankResponse transactionRank2Response(List<Pair> pairs) {
        List<String> rankedPairs = new ArrayList<>();

        for (Pair pair : pairs) {
            rankedPairs.add(pair.getPair());
        }
        return new StatTransactionsRankResponse(rankedPairs);
    }
}
