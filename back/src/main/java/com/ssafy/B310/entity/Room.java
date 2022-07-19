package com.ssafy.B310.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomNum;
	
	@Column(nullable = false, unique = true)
	private String roomName;
	
	@Column(nullable = false, columnDefinition = "TINYINT", length=1)
	private int video;
	
	@Column(nullable = false, columnDefinition = "TINYINT", length=1)
	private int sound;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@Column
	private int roomStudyTime;
	
	@Column
	private int roomRestTime;
	
	public Room(String roomName, int video, int sound, Date startTime, Date endTime,
			int roomStudyTime, int roomRestTime) {
		this.roomName = roomName;
		this.video = video;
		this.sound = sound;
		this.startTime = startTime;
		this.endTime = endTime;
		this.roomStudyTime = roomStudyTime;
		this.roomRestTime = roomRestTime;
	}
	public Room() {
		
	}
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User userId;
	
	@OneToMany(mappedBy = "room")
	private List<RoomParticipant> participationList = new ArrayList<>();
	
	@OneToMany(mappedBy = "room")
	private List<RoomHashtag> roomHashtag = new ArrayList<RoomHashtag>();

}
