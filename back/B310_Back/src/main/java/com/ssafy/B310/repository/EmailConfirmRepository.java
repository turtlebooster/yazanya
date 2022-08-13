package com.ssafy.B310.repository;

import com.ssafy.B310.entity.EmailConfirm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailConfirmRepository extends JpaRepository<EmailConfirm, Integer> {

    Optional<EmailConfirm> findByConfirmCode(String confirmCode);

    Optional<EmailConfirm> findByEmail(String email);
}
