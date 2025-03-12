package com.grocery.billing.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.grocery.billing.service.ExchangeRateService;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{
	
	@Value("${currency-exchange-rate.api-key}")
    private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";

	@Override
	public BigDecimal getConvertedAmount(String baseCurrency, String targetCurrency, String amount) {
        try {
            String url = API_BASE_URL + apiKey + "/pair/" + baseCurrency + "/" + targetCurrency + "/" + amount;

            // Call API using RestTemplate
            String response = restTemplate.getForObject(url, String.class);

            // Parse JSON response using Gson
            JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();

            // Check if response contains conversion_rate
            if (jsonResponse.has("conversion_result")) {
                return jsonResponse.get("conversion_result").getAsBigDecimal();
            } else {
                throw new RuntimeException("Invalid response: " + response);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching exchange rate", e);
        }
    }
}



