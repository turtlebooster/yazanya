package com.ssafy.B310.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
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
	private boolean video;
	
	@Column(nullable = false, columnDefinition = "TINYINT", length=1)
	private boolean sound;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@Column
	private int roomStudyTime;
	
	@Column
	private int roomRestTime;
	
	@Column
	private int capacity;
	
	@Column
	private int participationCount;
	
	@Column
	private LocalDateTime startTime;
	
	@PrePersist
    public void startTime() {
        this.startTime = LocalDateTime.now();
    }
	
	public Room(String roomName, boolean video, boolean sound, LocalDateTime startTime, Date endTime,
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
	@JoinColumn(name="user_num")
	private User manager;
	
	@OneToMany(mappedBy = "room")
	private Set<Participation> participationList;
//	private Set<Participation> participationList = new ArrayList<>();
	
	@OneToMany(mappedBy = "room")
	private Set<RoomHashtag> roomHashtag;
//	private Set<RoomHashtag> roomHashtag = new ArrayList<RoomHashtag>();

}
