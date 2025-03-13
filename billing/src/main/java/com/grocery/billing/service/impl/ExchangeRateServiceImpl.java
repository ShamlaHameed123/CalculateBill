package com.grocery.billing.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.grocery.billing.exception.ExchangeRateApiError;
import com.grocery.billing.service.ExchangeRateService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{
	
	private static final Logger logger = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);
	
	@Value("${currency-exchange-rate.api-key}")
    private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";

	@Override
	public BigDecimal getConvertedAmount(String baseCurrency, String targetCurrency, String amount) throws ExchangeRateApiError {
        try {
            String url = API_BASE_URL + apiKey + "/pair/" + baseCurrency + "/" + targetCurrency + "/" + amount;
            String response = restTemplate.getForObject(url, String.class);
            
            // Parse JSON response using Gson
            JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();       
            if (jsonResponse.has("conversion_result")) {
                return jsonResponse.get("conversion_result").getAsBigDecimal();
            } else {
            	logger.error("Response is not valid, returned " + response);
                throw new RuntimeException("Invalid response: " + response);
            }
        } catch (Exception e) {
        	logger.error("Exception caught " , e.getLocalizedMessage(), e);
            throw new ExchangeRateApiError("Error fetching exchange rate");
        }
    }
}



