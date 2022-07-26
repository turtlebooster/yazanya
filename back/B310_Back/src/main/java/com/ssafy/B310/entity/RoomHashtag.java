package com.ssafy.B310.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class RoomHashtag {
	@Id
	@GeneratedValue
	@Column(name = "USERHASHTAG_ID")
	private Long Id;
	
//	@Id
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "ROOM_NUM")
	private Room room;
	
//	@Id
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "HASHTAG_NUM")
	private Hashtag hashtag;
	

}
