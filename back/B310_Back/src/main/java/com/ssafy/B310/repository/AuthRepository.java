package com.ssafy.B310.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.B310.entity.Auth;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Integer> {
	Optional<Auth> findByuser_userId(String userId);
}
