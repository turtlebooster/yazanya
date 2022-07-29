package com.ssafy.B310.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ParticipationHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int participationHistoryNum;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "room_num")
	private Room room;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_num")
	private User user;

	public ParticipationHistory(int participationHistoryNum, Room room, User user) {
		this.participationHistoryNum = participationHistoryNum;
		this.room = room;
		this.user = user;
	}

	public ParticipationHistory() {
	}
	
	
}
