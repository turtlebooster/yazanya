package com.ssafy.B310.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserHashtag {
	@Id
	@GeneratedValue
	@Column(name = "USERHASHTAG_ID")
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name = "USER_NUM")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "HASHTAG_NUM")
	private Hashtag hashtag;
	

}
