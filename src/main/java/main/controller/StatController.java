package main.controller;

import main.api.response.StatByTransactionResponse;
import main.api.response.StatUsersWithSumResponse;
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
    private ResponseEntity<StatByTransactionResponse> getStatByTransactionResponse
            (@RequestParam(value = "currency", defaultValue = "USD") final String currency,
             @RequestParam(value = "amount", defaultValue = "10000") final String amount) {
        return statService.getStatByTransaction(currency, amount);
    }

    @GetMapping("/api/stat/users_sum")
    private ResponseEntity<StatUsersWithSumResponse> getStatUsersWithSumResponse
            (@RequestParam(value = "currency", defaultValue = "USD") final String currency,
             @RequestParam(value = "sum", defaultValue = "100000") final String sum) {
        return statService.getStatUsersWithSum(currency, sum);
    }
}
