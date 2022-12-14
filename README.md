# B310 야자냐

![title_logo_green.png](image/title_logo_green.png)

# 프로젝트 소개

---

- 다양한 공부 보조기능을 탑재한 온라인 독서실 홈페이지
- Kurento 라이브러리를 활용한 실시간 화상 채팅 기능 지원

# 기술 스택

---

| 구분 | 기술스택 | 버전 |
| --- | --- | --- |
| 공통 | Gitlab | - |
|  | Jara | - |
| Backend | Java  | Zulu 8.33.0.1 |
|  | Spring Boot | 2.7.1 |
|  | Kurento | 6.15.0 |
|  | Swagger2 | 3.0.0 |
|  | MySQL | 8.0.30 |
|  | JPA | - |
|  | QueryDSL | 5.0.0 |
|  | Gradle | 7.5 |
|  | Maven | 3.8.6 |
| Frontend | Vue | 3.2.13 |
|  | Bootstrap-Vue-3 | 0.1.13 |
|  | Bootstrap | 5.1.3 |
|  | Kurento-utils | 6.16.0 |
| Server | AWS EC2 | - |
|  | Ubuntu | 20.04.3 LTS |
|  | Docker | 20.10.17 |

# 시스템 구조도

---

![Untitled](image/Untitled.png)

# 구현 기능

---

- UI/UX
    - 라이트 모드 / 다크 모드
    
    ![3.png](image/3.png)
    
    - 사이드 바 확장 / 고정
    
    ![11.png](image/11.png)
    
- 회원 관리
    - 로그인 / 로그아웃
    
    ![Untitled](image/Untitled%201.png)
    
    - 회원가입 / 탈퇴
    
    SMTP를 사용한 이메일 인증기능을 활용한 회원가입 기능 구현
    
    ![Untitled](image/Untitled%202.png)
    
    ![Untitled](image/Untitled%203.png)
    
    - 정보 조회 및 수정
    
    ![Untitled](image/Untitled%204.png)
    
- 스터디 플래너
    - 드래그 앤 드롭이 가능한 컴포넌트로 구현
    - 확장성을 고려하여, 다양한 요소들을 추가할 수 있음
    - 지금까지의 공부 시간, 시간에 따른 티어 확인 가능
    - 공부에 도움이 되는 달력 및 TODO 기능 지원
    
    ![Untitled](image/Untitled%205.png)
    
    ![Untitled](image/Untitled%206.png)
    
- 공부방
    - 방 검색 기능
    
    키워드 검색 및 해쉬 태그로 검색 가능
    사용자 프로필에서 지정한 태그로 방을 추천
    
    ![Untitled](image/Untitled%207.png)
    
    ![Untitled](image/Untitled%208.png)
    
    - 공부방 생성 및 참여
        - 비밀번호 / 해쉬태그 / 마이크&카메라 OnOff / 정원 / 쉬는 시간&공부시간
        
        ![Untitled](image/Untitled%209.png)
        
    - 쿠렌토 데이터 채널을 이용한 실시간 채팅 기능 구현
    
    ![Untitled](image/Untitled%2010.png)
    
    - 깨우기 기능
    
    ![Untitled](image/Untitled%2011.png)
    
    - 사생활 보호를 위한 쿠렌토 필터 기능 지원
    
    ![Untitled](image/Untitled%2012.png)
    
    - 공부 시간 / 쉬는 시간 알람 기능지원
    - 왼쪽 사이드 바에서 스터디 도우미 기능 활용 가능
    
    ![KakaoTalk_20220819_113334844.gif](image/KakaoTalk_20220819_113334844.gif)
    

# 개발 멤버 소개

---

| 박서은 | [박성현](https://github.com/giftanchovy) | [박준혁](https://github.com/inte99ral) | 👑 [우상욱](https://github.com/YeoUlFox) | [양지호](https://github.com/jihoyangKR) | [최진호](https://github.com/turtlebooster) |
| --- | --- | --- | --- | --- | --- |
| Back-End | Front-End | Front-End | Front-End | Back-End | Back-End |
| REST API  Database | Vue | UI/UX  Vue | Vue  Kurento client  REST API | REST API  Database | REST API  WebRTC  Infra |

# 프로젝트 기간

---

**22.06.30 ~ 22.08.19**

- 기획 및 설계 : 22.06.30 ~ 22.07.08
- 프로젝트 구현 : 22.07.11 ~ 22.08.12
- 버그 수정 및 산출물 정리 : 22.08.16 ~ 19

# UCC 링크

https://www.youtube.com/watch?v=WvYvYS_AqiE
