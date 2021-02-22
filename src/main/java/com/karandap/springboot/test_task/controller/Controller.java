package com.karandap.springboot.test_task.controller;


import com.karandap.springboot.test_task.entity.giphy.Giphy;
import com.karandap.springboot.test_task.entity.oer.OpenExchangeRates;
import com.karandap.springboot.test_task.service.GiphyFeignClientService;
import com.karandap.springboot.test_task.service.OpenExchangeRatesFeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/result")
    public Giphy mainAction(@RequestParam("currency") String currency) {
        Properties properties = propertiesStorage();

        String apiKeyOER = properties.getProperty("oer-api.key");
        String baseCurrency = properties.getProperty("oer-api.base-currency");
        String apiKeyGiphy = properties.getProperty("giphy-api.key");
        String successTag = properties.getProperty("giphy-api.tag.success");
        String failureTag = properties.getProperty("giphy-api.tag.failure");

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

    public static Properties propertiesStorage() {
        Properties properties = new Properties();
        try {
            FileInputStream in = new FileInputStream("src/main/resources/settings.properties");
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
