package com.ssafy.B310.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Participation {
	@Id
	@GeneratedValue
	@Column(name = "PARTICIPATION_ID")
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name = "room_num")
	private Room room;
	
	@ManyToOne
	@JoinColumn(name = "user_num")
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date enterTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date exitTime;
}
