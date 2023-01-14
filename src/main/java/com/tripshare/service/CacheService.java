package com.tripshare.service;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class CacheService {
    private final HashOperations<String, String, Object> hashOperations;

    public CacheService(RedisTemplate<String, Object> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void put(String key, String hashKey, Object value) {
        hashOperations.put(key, hashKey, value);
    }

    public void putIfAbsent(String key, String hashKey, Object value) {
        if (hashOperations.hasKey(key, hashKey)) {
            hashOperations.put(key, hashKey, value);
        } else {
            hashOperations.putIfAbsent(key, hashKey, value);
        }
    }

    public Object get(String key, String hashKey) {
        return hashOperations.get(key, hashKey);
    }

}
