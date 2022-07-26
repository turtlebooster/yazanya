package com.ssafy.B310.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Data
@Getter
@Setter
public class Todo {
	@Id
	@GeneratedValue
	private int todoNum;
	
	@Column
	private String todoContent;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date todoStartTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date todoEndTime;
	
	@Column
	private String todoName;
	
	@ManyToOne
	@JoinColumn(name = "user_num")
	private User user;
	
	public Todo() {}
	public Todo(int todoNum, String todoContent, Date todoStartTime, Date todoEndTime, String todoName, User user) {
		super();
		this.todoNum = todoNum;
		this.todoContent = todoContent;
		this.todoStartTime = todoStartTime;
		this.todoEndTime = todoEndTime;
		this.todoName = todoName;
		this.user = user;
	}
	
	
}
