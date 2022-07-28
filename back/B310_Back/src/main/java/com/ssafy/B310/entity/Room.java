package com.ssafy.B310.entity;

import java.time.LocalDateTime;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
	
	@Column
	private int roomPw;
	
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
	@JsonIgnore
	private User manager;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "room")
	@JsonIgnore
	private Set<Participation> participationList;
//	private Set<Participation> participationList = new ArrayList<>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "room")
	@JsonIgnore
	private Set<RoomHashtag> roomHashtag;
//	private Set<RoomHashtag> roomHashtag = new ArrayList<RoomHashtag>();
	
	

	@Override
	public String toString() {
		return "Room{" +
				"roomNum=" + roomNum +
				", roomName='" + roomName + '\'' +
				", video=" + video +
				", sound=" + sound +
				", endTime=" + endTime +
				", roomStudyTime=" + roomStudyTime +
				", roomRestTime=" + roomRestTime +
				", capacity=" + capacity +
				", participationCount=" + participationCount +
				", startTime=" + startTime +
				", manager=" + manager +
				", participationList=" + participationList +
				", roomHashtag=" + roomHashtag +
				'}';
	}
}
