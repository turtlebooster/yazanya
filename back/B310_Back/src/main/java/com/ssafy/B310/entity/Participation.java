package com.ssafy.B310.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Participation {
	@Id
	@GeneratedValue
	@Column(name = "PARTICIPATION_ID")
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name = "room_num")
	private Room room;
	
	@ManyToOne
	@JoinColumn(name = "user_num")
	private User user;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date enterTime;

	@Column
	private LocalDateTime enterTime;

	@PrePersist
	public void enterTime() {
		this.enterTime = LocalDateTime.now();
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date exitTime;

	public Participation(Room room, User user) {
		this.room = room;
		this.user = user;
	}
	public Participation(){

	}
}
