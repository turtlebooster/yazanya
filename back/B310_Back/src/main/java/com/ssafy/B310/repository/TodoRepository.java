package com.ssafy.B310.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.B310.entity.Todo;
import com.ssafy.B310.entity.User;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
	List<Todo> findByUser(User user);
}
