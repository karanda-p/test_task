package com.karandap.springboot.test_task.client;

import com.karandap.springboot.test_task.entity.giphy.Giphy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphy-client", url="${giphy-api.endpoint}")
public interface GiphyFeignClient {

    @GetMapping("/random")
    Giphy getRandomGiphy(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);

}