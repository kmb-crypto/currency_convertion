package main.service;

import main.api.request.ExchangeRequest;
import main.api.response.ExchangeResponse;
import main.model.Currency;
import main.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExchangeService {
    private final TransactionRepository transactionRepository;
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ExchangeService(final TransactionRepository transactionRepository,
                           final ExchangeRateService exchangeRateService) {
        this.transactionRepository = transactionRepository;
        this.exchangeRateService = exchangeRateService;
    }

    public ResponseEntity<ExchangeResponse> getExchangeResponse(final ExchangeRequest exchangeRequest) {
        String from = exchangeRequest.getFromCurrency();
        String to = exchangeRequest.getToCurrency();
        boolean buyCurrency = from.equals(Currency.RUB.toString());
        BigDecimal rate = buyCurrency ? exchangeRateService.getExchangeRate(to) :
                exchangeRateService.getExchangeRate(from);

        System.out.println(rate);
        return new ResponseEntity<>(new ExchangeResponse(), HttpStatus.OK);
    }
}
