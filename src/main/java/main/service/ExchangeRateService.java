package main.service;


import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;

@Component
public class ExchangeRateService {
    private final static String CENTRAL_BANK_URL = "https://www.cbr-xml-daily.ru/daily_json.js";

    public BigDecimal getExchangeRate(String currency) {
        try {
            InputStream response = new URL(CENTRAL_BANK_URL).openStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(response));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            JSONObject jsonResponse = new JSONObject(responseStrBuilder.toString());
            return (BigDecimal) jsonResponse.getJSONObject("Valute").getJSONObject(currency).get("Value");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
