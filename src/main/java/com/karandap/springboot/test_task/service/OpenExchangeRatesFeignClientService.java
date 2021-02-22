package com.karandap.springboot.test_task.service;

import com.karandap.springboot.test_task.client.OpenExchangeRatesFeignClient;
import com.karandap.springboot.test_task.entity.oer.OpenExchangeRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenExchangeRatesFeignClientService {

    @Autowired
    OpenExchangeRatesFeignClient openExchangeRatesFeignClient;

    public OpenExchangeRates getLatestOpenExchangeRates(String apiKey, String base){
        return openExchangeRatesFeignClient.getLatestOpenExchangeRates(apiKey, base);
    }

    public OpenExchangeRates getYesterdayOpenExchangeRates(String date, String apiKey, String base){
        return openExchangeRatesFeignClient.getYesterdayOpenExchangeRates(date, apiKey, base);
    }
}
