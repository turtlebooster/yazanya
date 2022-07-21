package com.ssafy.B310.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.B310.entity.Hashtag;


@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Integer>{

}
