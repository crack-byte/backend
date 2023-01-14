package com.tripshare.service;

import com.tripshare.dto.PaginationDTO;
import com.tripshare.dto.UserDTO;
import com.tripshare.entity.User;
import com.tripshare.enums.Cache;
import com.tripshare.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CacheService cacheService;

    /**
     * @param userDTO
     * @return
     */
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User(userDTO);
        return new UserDTO(userRepository.save(user));
    }

    /**
     * @param limit
     * @param page
     * @return
     */
    public PaginationDTO<UserDTO> findAll(int limit, int page) {
        Page<User> usersPage = userRepository.findAll(Pageable.ofSize(limit).withPage(page));
        return new PaginationDTO<>(usersPage.stream().map(UserDTO::new).collect(Collectors.toList()), page + 1, (int) usersPage.getTotalElements());
    }

    public User findByUsername(String username) {
        if (cacheService.hasKey(Cache.USER, username)) {
            return (User) cacheService.get(Cache.USER, username);
        } else {
            User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            cacheService.putIfAbsent(Cache.USER, username, user);
            return user;
        }
    }

    public List<User> findAllById(List<Long> ids) {
        return userRepository.findAllById(ids);
    }
}
