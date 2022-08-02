package com.ssafy.B310.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileNum;

    // 자기소개
    @Column
    private String profileSelfIntroduce;

    // 프로필 사진(링크)
    @Column
    private String profilePictureLink;

    // 총 공부 시간(분 기준)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date profileTotalStudyTime;

    @Column
    private int profileTotalStudyTime;

    // 랭크
    @Column
    private int profileRank;

    // 소속
    @Column
    private String profileBelongTo;

    @OneToOne
    @JoinColumn(name="user_num")
    private User profileUser;

    public Profile() {
    }

    public Profile(int profileNum, String profileSelfIntroduce, String profilePictureLink, int profileTotalStudyTime, int profileRank, String profileBelongTo, User user) {
        this.profileNum = profileNum;
        this.profileSelfIntroduce = profileSelfIntroduce;
        this.profilePictureLink = profilePictureLink;
        this.profileTotalStudyTime = profileTotalStudyTime;
        this.profileRank = profileRank;
        this.profileBelongTo = profileBelongTo;
        this.profileUser = user;
    }
}
