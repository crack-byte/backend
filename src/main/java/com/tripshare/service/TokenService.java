package com.tripshare.service;

import com.tripshare.entity.Account;
import com.tripshare.entity.CustomUserDetails;
import com.tripshare.util.Util;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    private final ValueOperations<String, Object> valueOperations;

    public TokenService(RedisTemplate<String, Object> redisTemplate) {
        valueOperations = redisTemplate.opsForValue();
    }

    public void saveToken(String token, Account account, long expiration) {
        valueOperations.set(token, account, expiration, TimeUnit.MILLISECONDS);
    }

    public CustomUserDetails validateToken(String token) {
        // Check if the token exists in Redis
        Object fromValue = valueOperations.get(token);
        if (fromValue == null) {
            return null;
        }
        return new CustomUserDetails(Util.MAPPER.convertValue(fromValue, Account.class));
    }

}
