package com.karandap.springboot.test_task.service;

import com.karandap.springboot.test_task.client.GiphyFeignClient;
import com.karandap.springboot.test_task.entity.giphy.Giphy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiphyFeignClientService {

    @Autowired
    GiphyFeignClient giphyFeignClient;

    public Giphy getRandomGiphy(String apiKey, String tag){
        return giphyFeignClient.getRandomGiphy(apiKey, tag);
    }


}
