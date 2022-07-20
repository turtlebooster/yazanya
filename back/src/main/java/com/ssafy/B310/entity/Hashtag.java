package com.ssafy.B310.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Hashtag {
	@Id
	@GeneratedValue
	private int hashtagNum;
	
	@Column
	private String hashtagName;
	
	public Hashtag(int hashtagNum, String hashtagName) {
		this.hashtagNum = hashtagNum;
		this.hashtagName = hashtagName;
	}
	
	public Hashtag() {
		
	}

	@OneToMany(mappedBy = "hashtag")
	private List<UserHashtag> userHashtag = new ArrayList<UserHashtag>();
	
	@OneToMany(mappedBy = "hashtag")
	private List<RoomHashtag> roomHashtag = new ArrayList<RoomHashtag>();
}
