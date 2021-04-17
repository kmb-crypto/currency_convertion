package main.controller;

import main.api.request.ExchangeRequest;
import main.api.response.ExchangeResponse;
import main.service.ExchangeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

    private final ExchangeService exchangeService;


    public ExchangeController(final ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @PostMapping("/api/exchange")
    public ResponseEntity<ExchangeResponse> exchange(@RequestBody final ExchangeRequest exchangeRequest) {
        return exchangeService.getExchangeResponse(exchangeRequest);
    }
}
