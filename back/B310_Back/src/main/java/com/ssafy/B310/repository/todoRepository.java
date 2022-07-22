package com.ssafy.B310.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.B310.entity.Todo;
import com.ssafy.B310.entity.User;

public interface todoRepository extends JpaRepository<Todo, Integer> {
	List<Todo> findByUser(User user);
}
