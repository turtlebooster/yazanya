package com.ssafy.B310.repository;

import static com.ssafy.B310.entity.QTodo.todo;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.B310.entity.Todo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class TodoQueryRepository {
	private final JPAQueryFactory queryFactory;
	
	public List<Todo> findTodoByDateRange(String userId, Date startDate, Date endDate) {
		return queryFactory
				.selectFrom(todo)
				.where(todo.todoEndTime.goe(startDate), todo.todoStartTime.loe(endDate))
				.fetch();
	}
}
