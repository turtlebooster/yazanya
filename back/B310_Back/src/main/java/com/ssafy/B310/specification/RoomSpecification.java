package com.ssafy.B310.specification;

import org.springframework.data.jpa.domain.Specification;

import com.ssafy.B310.entity.Room;

public class RoomSpecification {
	
	//0 - ���� ��. 1 - ���� ��. 2 - ��� ����
	public static Specification<Room> videoSetting(int status) {
		if(status != 2) { 
			return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("video"), status);
		} else {
			return (root, query, criteriaBuilder) -> null;
		}
	}
	
	//0 - ����� ��. 1 - ����� ��. 2 - ��� ����
	public static Specification<Room> soundeSetting(int status) {
		if(status != 2) {
			return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("sound"), status);
		} else {
			return (root, query, criteriaBuilder) -> null;
		}
		
	}
	
	//0 - ��� ���� . 1 - ������ �� ���� ���� �游 ������
	public static Specification<Room> isFull(int status) {	
		if(status == 0) {
			return (root, query, criteriaBuilder) -> null;
		} else {
			return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("capacity"), root.get("participationCount"));
			
		}
	}
}
