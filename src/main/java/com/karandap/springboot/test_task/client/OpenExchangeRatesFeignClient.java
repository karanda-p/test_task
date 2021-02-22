package com.karandap.springboot.test_task.client;

import com.karandap.springboot.test_task.entity.oer.OpenExchangeRates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "oer-client", url = "https://openexchangerates.org/api")
public interface OpenExchangeRatesFeignClient {

    @GetMapping("/latest.json")
    OpenExchangeRates getLatestOpenExchangeRates(@RequestParam("app_id") String apiKey,
                                                 @RequestParam("base") String base);

    @GetMapping("/historical/{date}.json")
    OpenExchangeRates getYesterdayOpenExchangeRates(@PathVariable String date,
                                                    @RequestParam("app_id") String apiKey,
                                                    @RequestParam("base") String base);

}
