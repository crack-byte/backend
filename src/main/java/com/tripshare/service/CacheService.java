package com.tripshare.service;

import com.tripshare.enums.Cache;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class CacheService {
    private final HashOperations<String, String, Object> hashOperations;

    public CacheService(RedisTemplate<String, Object> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.hashOperations.keys(Cache.USER.toString())
            .forEach(key -> this.hashOperations.delete(Cache.USER.toString(), key));
    }

    public void put(String key, String hashKey, Object value) {
        hashOperations.put(key, hashKey, value);
    }

    public void putIfAbsent(Cache key, String hashKey, Object value) {
        String keyValue = key.toString();
        if (hashOperations.hasKey(keyValue, hashKey)) {
            hashOperations.put(keyValue, hashKey, value);
        } else {
            hashOperations.putIfAbsent(keyValue, hashKey, value);
        }
    }

    public Object get(Cache key, String hashKey) {
        return hashOperations.get(key.toString(), hashKey);
    }

    public boolean hasKey(Cache key, String hashKey) {
        return hashOperations.hasKey(key.toString(), hashKey);
    }

}
