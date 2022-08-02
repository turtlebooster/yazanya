package com.ssafy.B310.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.ColumnDefault;

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
	
	@Column
	private String roomThumbnail;
	
	@Column
	private String roomDescription;
	
	@Column(nullable = false, columnDefinition = "TINYINT", length=1)
	private boolean roomVideo;
	
	@Column(nullable = false, columnDefinition = "TINYINT", length=1)
	private boolean roomSound;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date roomEndTime;
	
	@Column
	private int roomStudyTime;
	
	@Column
	private int roomRestTime;
	
	@Column
	private int roomCapacity;
	
	@Column
	private int roomParticipationCount;
	
	@Column
	private LocalDateTime roomStartTime;
	
	@Column
	private int roomPw;
	
	@Column(columnDefinition = "TINYINT", length=1)
	@ColumnDefault("true")
	private boolean roomActive;
	
	@PrePersist
    public void roomStartTime() {
        this.roomStartTime = LocalDateTime.now();
    }
	
	public Room() {
		
	}
	
	@OneToOne
	@JoinColumn(name="user_num")
	@JsonIgnore
	private User manager;
	
	@OneToMany(mappedBy = "room")
	@JsonIgnore
	private Set<Participation> participationList;
	
	@OneToMany(mappedBy = "room")
	@JsonIgnore
	private Set<RoomHashtag> roomHashtag;

	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", roomName=" + roomName + ", roomVideo=" + roomVideo + ", roomSound="
				+ roomSound + ", roomEndTime=" + roomEndTime + ", roomStudyTime=" + roomStudyTime + ", roomRestTime="
				+ roomRestTime + ", roomCapacity=" + roomCapacity + ", roomParticipationCount=" + roomParticipationCount
				+ ", roomStartTime=" + roomStartTime + ", roomPw=" + roomPw + ", roomActive=" + roomActive
				+ ", manager=" + manager + ", participationList=" + participationList + ", roomHashtag=" + roomHashtag
				+ "]";
	}

	public Room(int roomNum, String roomName, boolean roomVideo, boolean roomSound, Date roomEndTime, int roomStudyTime,
			int roomRestTime, int roomCapacity, int roomParticipationCount, LocalDateTime roomStartTime, int roomPw,
			boolean roomActive, User manager, Set<Participation> participationList, Set<RoomHashtag> roomHashtag) {
		super();
		this.roomNum = roomNum;
		this.roomName = roomName;
		this.roomVideo = roomVideo;
		this.roomSound = roomSound;
		this.roomEndTime = roomEndTime;
		this.roomStudyTime = roomStudyTime;
		this.roomRestTime = roomRestTime;
		this.roomCapacity = roomCapacity;
		this.roomParticipationCount = roomParticipationCount;
		this.roomStartTime = roomStartTime;
		this.roomPw = roomPw;
		this.roomActive = roomActive;
		this.manager = manager;
		this.participationList = participationList;
		this.roomHashtag = roomHashtag;
	}
}
