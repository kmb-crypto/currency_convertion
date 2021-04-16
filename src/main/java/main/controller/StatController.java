package main.controller;

import main.api.response.StatByTransactionResponse;
import main.api.response.StatTransactionsRankResponse;
import main.api.response.StatUsersWithSumResponse;
import main.service.StatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stat")
public class StatController {

    private final StatService statService;

    public StatController(final StatService statService) {
        this.statService = statService;
    }

    @GetMapping("/by_transaction")
    public ResponseEntity<StatByTransactionResponse> getStatByTransactionResponse
            (@RequestParam(value = "currency", defaultValue = "USD") final String currency,
             @RequestParam(value = "amount", defaultValue = "10000") final String amount) {
        return statService.getStatByTransaction(currency, amount);
    }

    @GetMapping("/users_sum")
    public ResponseEntity<StatUsersWithSumResponse> getStatUsersWithSumResponse
            (@RequestParam(value = "currency", defaultValue = "USD") final String currency,
             @RequestParam(value = "sum", defaultValue = "100000") final String sum) {
        return statService.getStatUsersWithSum(currency, sum);
    }

    @GetMapping("/transactions_rank")
    public ResponseEntity<StatTransactionsRankResponse> getTransactionsRank() {
        return statService.getTransactionsRank();
    }
}
