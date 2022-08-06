package com.ssafy.B310.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Hashtag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hashtagNum;
	
	@Column
	private String hashtagName;
	
	public Hashtag(int hashtagNum, String hashtagName) {
		this.hashtagNum = hashtagNum;
		this.hashtagName = hashtagName;
	}
	
	public Hashtag() {
		
	}
	@JsonIgnore
	@OneToMany(mappedBy = "hashtag", cascade = CascadeType.REMOVE)
	private List<UserHashtag> userHashtag = new ArrayList<UserHashtag>();
	@JsonIgnore
	@OneToMany(mappedBy = "hashtag", cascade = CascadeType.REMOVE)
	private List<RoomHashtag> roomHashtag = new ArrayList<RoomHashtag>();
}
