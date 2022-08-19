package com.ssafy.B310.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoomThumbnail {
	
	@Id
	private String thumbnailId;
	
	@Column(nullable = false)
	private String thumbnailName;
	
	@Column(nullable = false)
	private String thumnailPath;
}
