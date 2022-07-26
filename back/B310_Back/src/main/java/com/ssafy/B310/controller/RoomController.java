package com.ssafy.B310.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.B310.entity.Participation;
import com.ssafy.B310.entity.User;
import com.ssafy.B310.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.B310.entity.Room;
import com.ssafy.B310.service.RoomService;

@RestController
@RequestMapping("/room")
@CrossOrigin("*")
public class RoomController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    RoomService roomservice;

    @Autowired
    ParticipationService participationservice;

    // 방 생성
    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@RequestBody Room room) throws SQLException{

        int cnt = roomservice.createRoom(room);

        if(cnt!=0) return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 방 조회
    @GetMapping("/filter")
    public ResponseEntity<?> filterRoom(@RequestBody Map<String, Integer> params) throws SQLException{
        return new ResponseEntity<List<Room>>(roomservice.filterRoom(params), HttpStatus.OK);
    }

    // 방 삭제
    @GetMapping("/remove/{roomNum}")
    public ResponseEntity<?> removeRoom(@PathVariable("roomNum") int roomNum) throws SQLException{

        int cnt = roomservice.removeRoom(roomNum);

        if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 방 정보 업데이트
    @PutMapping("/update")
    public ResponseEntity<?> updateRoom(@RequestBody Room room) throws SQLException{

        int cnt = roomservice.updateRoom(room);

        if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 유저 입장
    @PostMapping("/join/{roomNum}")
    public ResponseEntity<?> joinRoom(@RequestBody  User user , @PathVariable("roomNum") int roomNum) throws SQLException {
        int cnt = participationservice.joinRoom(user, roomNum);
        System.out.println(roomNum);
        System.out.println(user);

        if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // 유저 퇴장
    @DeleteMapping("/exit/{roomNum}")
    public ResponseEntity<?> exitRoom(@RequestBody Map<String, Integer> params , @PathVariable int roomNum ) throws SQLException {
        int userNum = params.get("userNum");
        int cnt = participationservice.exitRoom(userNum, roomNum);


        if(cnt==1) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 입장 유저 리스트 반환
    @GetMapping("/join/{roomNum}")
    public ResponseEntity<?> joinedUser(@PathVariable int roomNum) throws SQLException {

        List joinedUserList = participationservice.joinedUser(roomNum);

//        return new ResponseEntity<List<Participation>>(joinedUserList, HttpStatus.OK);
        return new ResponseEntity<List<User>>(joinedUserList, HttpStatus.OK);

    }

}
