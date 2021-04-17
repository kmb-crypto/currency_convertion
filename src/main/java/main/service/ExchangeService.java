package main.service;

import main.api.request.ExchangeRequest;
import main.api.response.ExchangeResponse;
import main.model.Currency;
import main.model.Transaction;
import main.model.User;
import main.repository.TransactionRepository;
import main.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ExchangeService {
    private final UserRepository userRepository;
    private final ExchangeRateService exchangeRateService;
    private final TransactionRepository transactionRepository;
    private final Logger logger;
    private final Marker transactionMarker;

    @Autowired
    public ExchangeService(
            final UserRepository userRepository,
            final ExchangeRateService exchangeRateService,
            final TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.exchangeRateService = exchangeRateService;
        this.transactionRepository = transactionRepository;
        this.logger = LogManager.getLogger("Exchange");
        this.transactionMarker = MarkerManager.getMarker("TRANSACTION");
    }

    public ResponseEntity<ExchangeResponse> getExchangeResponse(final ExchangeRequest exchangeRequest) {
        String from = exchangeRequest.getFromCurrency();
        String to = exchangeRequest.getToCurrency();
        long userId = exchangeRequest.getUserId();
        boolean buyCurrency = from.equals(Currency.RUB.toString());
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(exchangeRequest.getAmount())).setScale(2);
        BigDecimal rate;
        BigDecimal resultAmount;
        if (buyCurrency) {
            rate = exchangeRateService.getExchangeRate(to);
            resultAmount = amount.divide(rate, 2, RoundingMode.HALF_UP);
        } else {
            rate = exchangeRateService.getExchangeRate(from);
            resultAmount = amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);

        }

        long longResultInCents = resultAmount.multiply(BigDecimal.TEN).multiply(BigDecimal.TEN).longValue();

        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.isEmpty() ? new User() : optionalUser.get();

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setCurrencyFrom(Currency.valueOf(from));
        transaction.setCurrencyTo(Currency.valueOf(to));
        transaction.setAmountInCents(amount.multiply(BigDecimal.TEN).
                multiply(BigDecimal.TEN).
                longValue());

        transaction.setResultExchangeAmountInCents(longResultInCents);
        transaction.setTime(new Timestamp(System.currentTimeMillis()));
        transactionRepository.save(transaction);

        user.addTransaction(transaction);

        logger.info(transactionMarker, "from " + amount + " " + from
                + " to " + resultAmount + " " + to + " | rate " + rate);

        userRepository.save(user);

        List<Transaction> transactions = user.getTransactions();
        return new ResponseEntity<>(new

                ExchangeResponse(transactions.get(transactions.size() - 1).

                getId(), resultAmount.

                toString()), HttpStatus.OK);

    }
}
