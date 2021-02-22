package com.karandap.springboot.test_task.controller;


import com.karandap.springboot.test_task.entity.giphy.Giphy;
import com.karandap.springboot.test_task.entity.oer.OpenExchangeRates;
import com.karandap.springboot.test_task.service.GiphyFeignClientService;
import com.karandap.springboot.test_task.service.OpenExchangeRatesFeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

@RestController
@EnableFeignClients
@RequestMapping("/api")
public class Controller {

    @Autowired
    private OpenExchangeRatesFeignClientService openExchangeRatesFeignClientService;

    @Autowired
    private GiphyFeignClientService giphyFeignClientService;

    @Value("${oer-api.key}")
    private String apiKeyOER;
    @Value("${oer-api.base-currency}")
    private String baseCurrency;
    @Value("${giphy-api.key}")
    private String apiKeyGiphy;
    @Value("${giphy-api.tag.success}")
    private String successTag;
    @Value("${giphy-api.tag.failure}")
    private String failureTag;

    @GetMapping("/result")
    public Giphy mainAction(@RequestParam("currency") String currency) {

        OpenExchangeRates openExchangeRates =
                openExchangeRatesFeignClientService.getLatestOpenExchangeRates(apiKeyOER, baseCurrency);
        OpenExchangeRates yesterdayOpenExchangeRates =
                openExchangeRatesFeignClientService.getYesterdayOpenExchangeRates(generateHistoricalOERName()
                        , apiKeyOER, baseCurrency);

        Giphy randomGiphy = null;

        if (openExchangeRates.getRates().get(currency.toUpperCase())
                > yesterdayOpenExchangeRates.getRates().get(currency.toUpperCase())) {

            randomGiphy = giphyFeignClientService.getRandomGiphy(apiKeyGiphy, successTag);

        } else {

            randomGiphy = giphyFeignClientService.getRandomGiphy(apiKeyGiphy, failureTag);

        }
        return randomGiphy;
    }

    public static String generateHistoricalOERName() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        return yesterday.toString();
    }

}
