package com.ssafy.B310.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserHashtag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USERHASHTAG_ID")
	private Long Id;
	
//	@Id
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USER_NUM")
	private User user;
	
//	@Id
	@JsonIgnore
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
