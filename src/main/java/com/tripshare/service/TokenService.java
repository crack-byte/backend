package com.tripshare.service;

import com.tripshare.entity.CustomUserDetails;
import com.tripshare.entity.User;
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

    public void saveToken(String token, User user, long expiration) {
        valueOperations.set(token, user, expiration, TimeUnit.MILLISECONDS);
    }

    public CustomUserDetails validateToken(String token) {
        // Check if the token exists in Redis
        Object fromValue = valueOperations.get(token);
        if (fromValue == null) {
            return null;
        }
        return new CustomUserDetails(Util.MAPPER.convertValue(fromValue, User.class));
    }

}
