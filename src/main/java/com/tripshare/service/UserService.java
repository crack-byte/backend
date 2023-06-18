package com.tripshare.service;

import com.tripshare.dto.PaginationDTO;
import com.tripshare.dto.UserDTO;
import com.tripshare.entity.Account;
import com.tripshare.enums.Cache;
import com.tripshare.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final HashOperations<String, String, Object> hashOperations;

    public UserService(UserRepository userRepository, RedisTemplate<String, Object> redisTemplate) {
        this.userRepository = userRepository;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void put(String key, String hashKey, Object value) {
        hashOperations.put(key, hashKey, value);
    }

    public void putIfAbsent(String hashKey, Object value) {
        String keyValue = Cache.USER.toString();
        if (hashOperations.hasKey(keyValue, hashKey)) {
            hashOperations.put(keyValue, hashKey, value);
        } else {
            hashOperations.putIfAbsent(keyValue, hashKey, value);
        }
    }

    public Object get(String hashKey) {
        return hashOperations.get(Cache.USER.toString(), hashKey);
    }

    public boolean hasKey(String hashKey) {
        return hashOperations.hasKey(Cache.USER.toString(), hashKey);
    }

    /**
     * @param userDTO
     * @return
     */
    public UserDTO createUser(UserDTO userDTO) {
        Account account = new Account(userDTO);
        return new UserDTO(userRepository.save(account));
    }

    /**
     * @param limit
     * @param page
     * @return
     */
    public PaginationDTO<UserDTO> findAll(int limit, int page) {
        Page<Account> usersPage = userRepository.findAll(Pageable.ofSize(limit).withPage(page));
        return new PaginationDTO<>(usersPage.stream().map(UserDTO::new).collect(Collectors.toList()), page + 1, usersPage.getTotalElements(), usersPage.getTotalPages());
    }

    public Account findByUsername(String username) {
        if (hasKey(username)) {
            return (Account) get(username);
        } else {
            Account account = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            putIfAbsent(username, account);
            return account;
        }
    }

    public List<Account> findAllById(List<Long> ids) {
        return userRepository.findAllById(ids);
    }
}
