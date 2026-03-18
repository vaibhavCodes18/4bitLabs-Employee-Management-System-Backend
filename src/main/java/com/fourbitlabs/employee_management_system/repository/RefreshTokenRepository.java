package com.fourbitlabs.employee_management_system.repository;

import com.fourbitlabs.employee_management_system.entity.RefreshToken;
import com.fourbitlabs.employee_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
    void deleteByToken(String token);
}
