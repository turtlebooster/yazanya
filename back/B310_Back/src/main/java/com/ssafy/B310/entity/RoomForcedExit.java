package com.ssafy.B310.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class RoomForcedExit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomForcedExitNum;

    @Column
    private String userId;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_num")
    private Room room;

    public RoomForcedExit() {
    }

    public RoomForcedExit(int roomForcedExitNum, String userId, Room room) {
        this.roomForcedExitNum = roomForcedExitNum;
        this.userId = userId;
        this.room = room;
    }

    public RoomForcedExit(String userId, Room room) {
        this.userId = userId;
        this.room = room;
    }
}
