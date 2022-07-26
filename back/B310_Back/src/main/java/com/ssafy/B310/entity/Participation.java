package com.ssafy.B310.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
//@Data
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
