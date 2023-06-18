package com.tripshare.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisProducer {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void sendMessage(String queue, Object body) {
        redisTemplate.convertAndSend(queue, body);
    }

}
