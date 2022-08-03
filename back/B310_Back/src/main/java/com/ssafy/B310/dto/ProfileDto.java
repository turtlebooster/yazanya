package com.ssafy.B310.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;

@Getter
@Setter
public class ProfileDto {
    // 자기소개

    @Column
    private String profileSelfIntroduce;

    // 프로필 사진(링크)
    @Column
    private String profilePictureLink;

    // 총 공부 시간(분 기준)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date profileTotalStudyTime;
    // date로 넘기는 방법 아직 못찾아서 일단 int로 설정
    @Column
	@ColumnDefault("/img/profile_default.jpg")
    private int profileTotalStudyTime;

    // 랭크
    @Column
    private int profileRank;

    // 소속
    @Column
    private String profileBelongTo;

    @Override
    public String toString() {
        return "profileDto{" +
                "profileSelfIntroduce='" + profileSelfIntroduce + '\'' +
                ", profilePictureLink='" + profilePictureLink + '\'' +
                ", profileTotalStudyTime=" + profileTotalStudyTime +
                ", profileRank=" + profileRank +
                ", profileBelongTo='" + profileBelongTo + '\'' +
                '}';
    }
}
