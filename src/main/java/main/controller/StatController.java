package main.controller;

import main.api.response.StatByTransactionResponse;
import main.service.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {

    private final StatService statService;

    public StatController(final StatService statService) {
        this.statService = statService;
    }

    @GetMapping("/api/stat/by_transaction")
    private ResponseEntity<StatByTransactionResponse> getStatByTransaction
            (@RequestParam(value = "currency", defaultValue = "USD") final String currency,
             @RequestParam(value = "amount", defaultValue = "10000") final String amount) {
        return statService.getStatByTransaction(currency, amount);
    }
}
