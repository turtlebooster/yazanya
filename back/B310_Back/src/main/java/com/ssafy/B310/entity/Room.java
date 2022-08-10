package com.ssafy.B310.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomNum;

	@Column(nullable = false)
	private String roomName;

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
	private String roomThumbnail;

	@Column
	private String roomPw;
	
	@Column(columnDefinition = "TINYINT", length=1)
	private boolean roomHasPw;

	@Column(columnDefinition = "TINYINT", length=1)
	@ColumnDefault("true")
	private boolean roomActive;

	@Column
	private int userNum;

	@PrePersist
	public void roomStartTime() {
		this.roomStartTime = LocalDateTime.now();
	}

	public Room() {

	}

	@OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Set<Participation> participationList;

	@OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Set<ParticipationHistory> participationHistoryList;

	@OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Set<RoomHashtag> roomHashtag;

	@OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Set<RoomForcedExit> roomForcedExits;
}
