package com.example.cashMarket.repo;

import com.example.cashMarket.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByCode(String code);
}
