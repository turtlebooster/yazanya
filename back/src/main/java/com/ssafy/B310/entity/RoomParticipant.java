package com.ssafy.B310.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ParticipantonId.class)
public class RoomParticipant {
	@Id
	@ManyToOne
	@JoinColumn(name = "room_num")
	private Room room;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_num")
	private User user;

}
