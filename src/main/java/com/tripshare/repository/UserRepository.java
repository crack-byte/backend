package com.tripshare.repository;

import com.tripshare.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Account, Long> {
    @Query("select u from Account u where u.email = ?1 or u.username = ?1")
    Optional<Account> findByUsername(@NonNull String email);

}