package main.service;

import main.api.request.ExchangeRequest;
import main.api.response.ExchangeResponse;
import main.model.Currency;
import main.model.Transaction;
import main.model.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class ExchangeService {
    private final UserRepository userRepository;
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeService(
            final UserRepository userRepository, final ExchangeRateService exchangeRateService) {
        this.userRepository = userRepository;

        this.exchangeRateService = exchangeRateService;
    }

    public ResponseEntity<ExchangeResponse> getExchangeResponse(final ExchangeRequest exchangeRequest) {
        String from = exchangeRequest.getFromCurrency();
        String to = exchangeRequest.getToCurrency();
        long userId = exchangeRequest.getUserId();
        boolean buyCurrency = from.equals(Currency.RUB.toString());
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(exchangeRequest.getAmount()));
        BigDecimal resultAmount = buyCurrency ?
                amount.divide(exchangeRateService.getExchangeRate(to), 2, RoundingMode.HALF_UP)
                : amount.multiply(exchangeRateService.getExchangeRate(from)).setScale(2, RoundingMode.HALF_UP);

        long longResultInCents = resultAmount.multiply(BigDecimal.TEN).multiply(BigDecimal.TEN).longValue();

        Optional<User> optionalUser = userRepository.findById(exchangeRequest.getUserId());

        User user = optionalUser.isEmpty() ? new User() : optionalUser.get();

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setCurrencyFrom(Currency.valueOf(from));
        transaction.setCurrencyTo(Currency.valueOf(to));
        transaction.setAmountInCents(amount.multiply(BigDecimal.TEN).multiply(BigDecimal.TEN).longValue());
        transaction.setResultExchangeAmountInCents(longResultInCents);
        transaction.setTime(new Timestamp(System.currentTimeMillis()));

        user.addTransaction(transaction);

        userRepository.save(user);

        return new ResponseEntity<>(new ExchangeResponse(), HttpStatus.OK);
    }
}
