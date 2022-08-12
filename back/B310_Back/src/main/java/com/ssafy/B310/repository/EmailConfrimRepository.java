package com.ssafy.B310.repository;

import com.ssafy.B310.entity.EmailConfirm;
import com.ssafy.B310.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailConfrimRepository extends JpaRepository<EmailConfirm, Integer> {

    Optional<EmailConfirm> findByConfirmCode(String confrimCode);
}
