package com.tripshare.repository;

import com.tripshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from users u where u.email = ?1 or u.username = ?1")
    Optional<User> findByUsername(@NonNull String email);

}