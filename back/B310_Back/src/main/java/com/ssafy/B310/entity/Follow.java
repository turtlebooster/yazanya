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

    @JoinColumn(name = "from_user")
    @ManyToOne
    User fromUser;

    @JoinColumn(name = "to_user")
    @ManyToOne
    User toUser;

    Timestamp followDate;

}
