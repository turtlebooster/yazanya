package com.ssafy.B310.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int followNum;

    String followUserMemo;

    @JoinColumn(name = "from_user")
    @ManyToOne
    User followFromUser;

    @JoinColumn(name = "to_user")
    @ManyToOne
    User followToUser;

    Timestamp followDate;

}
