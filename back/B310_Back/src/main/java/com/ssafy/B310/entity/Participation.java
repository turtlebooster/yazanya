package com.ssafy.B310.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Participation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PARTICIPATION_ID")
	private Long Id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "room_num")
	private Room room;
	
	@JsonIgnore
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

	public Participation(){

	}
	public Participation(Room room, User user) {
		this.room = room;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Participation{" +
				"Id=" + Id +
				", room=" + room +
				", user=" + user +
				", enterTime=" + enterTime +
				", exitTime=" + exitTime +
				'}';
	}
}
