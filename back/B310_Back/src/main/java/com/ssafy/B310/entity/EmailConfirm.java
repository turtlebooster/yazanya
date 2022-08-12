package com.ssafy.B310.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class EmailConfirm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int confirmNum;

    @Column
    String confirmCode;

    @Column
    Boolean confirmStatus;

    @Column
    String email;

}
