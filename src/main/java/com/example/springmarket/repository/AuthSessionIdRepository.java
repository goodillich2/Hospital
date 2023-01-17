package com.example.springmarket.repository;

import com.example.springmarket.model.user.AuthSessionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthSessionIdRepository extends JpaRepository<AuthSessionId, Integer> {
    AuthSessionId findBySessionId(String SessionId);
}
