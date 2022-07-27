package com.ssafy.B310.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserHashtag {
	@Id
	@GeneratedValue
	@Column(name = "USERHASHTAG_ID")
	private Long Id;
	
//	@Id
	@ManyToOne
	@JoinColumn(name = "USER_NUM")
	private User user;
	
//	@Id
	@ManyToOne
	@JoinColumn(name = "HASHTAG_NUM")
	private Hashtag hashtag;
	
	public UserHashtag(User user, Hashtag hashtag) {
		this.user = user;
		this.hashtag = hashtag;
	}
	
	public UserHashtag() {
		
	}

}
