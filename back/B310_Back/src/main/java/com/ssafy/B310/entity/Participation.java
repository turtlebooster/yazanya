package com.ssafy.B310.entity;

import java.time.LocalDateTime;
import java.util.Date;

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
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Participation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int participationNum;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "room_num")
	private Room room;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_num")
	private User user;
	
	@Column
	private LocalDateTime participationEnterTime;

	@PrePersist
	public void participationEnterTime() {
		this.participationEnterTime = LocalDateTime.now();
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date participationExitTime;

	public Participation(){

	}
	public Participation(Room room, User user) {
		this.room = room;
		this.user = user;
	}
}
