package com.ssafy.B310.entity;

import java.io.Serializable;

public class ParticipantonId implements Serializable{
	
	private int room;
	private int user;
	
	public ParticipantonId() {
	}
	
	public ParticipantonId(int room, int user) {
		this.room = room;
		this.user = user;
	}

}
