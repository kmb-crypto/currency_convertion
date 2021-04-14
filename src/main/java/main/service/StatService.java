package main.service;

import main.api.response.StatByTransactionResponse;
import main.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatService {
    private final TransactionRepository transactionRepository;
    private static final Long CENTS_IN_UNIT = 100L;

    public StatService(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ResponseEntity<StatByTransactionResponse> getStatByTransaction(final String currency, final String amount) {
        List<Integer> userId = transactionRepository.findUserIdByAmountAndCurrency(currency,
                Long.parseLong(amount) * CENTS_IN_UNIT);

        return new ResponseEntity<StatByTransactionResponse>(
                new StatByTransactionResponse(Integer.parseInt(amount), userId), HttpStatus.OK);
    }
}
