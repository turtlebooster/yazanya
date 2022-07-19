# 제목 없음

---

# 왜 WebSocket을 사용하는가

## 기존 http 통신의 채팅 기능 방식

1. client[1]이 server에게 메세지 전송
2. server가 메세지를 client[2]에게 전송
3. client[2]는 자신에게 온 메세지 확인

## 해당 방식의 문제점

- http 통신은 client가 요청을 하면 server가 응답하는 방식
- server의 입장 : client[2]에게 메세지 도착을 알리려고 해도, client[2]에게 요청이 없었기에 일방적인 응답을 보낼 수 없음
- client[2]의 입장 : 메세지가 도착했는지 아닌지를 판단할 수 없기에 server에게 메세지를 보내달라고 요청할 수 없음

---

# JPA

## JPA를 왜 써야 하는가?

- SQL을 직접 다루면 반복적인 CRUD SQL 작성과 SQL에 객체를 매핑하는 코드를 작성하는데 시간이 많이 걸림
- JPA는 객체를 전달만 하면 되므로 SQL을 작성하는 반복적인 일을 JPA가 대신 처리해주어 생산성 향상

## JPA 주요 어노테이션 종류

| 엔티티명 | 설명 |
| --- | --- |
| @Entity | 클래스를 테이블과 매핑한다고 JPA에게 알려줌 |
| @Table | Entity클래스에 매핑할 테이블 정보를 알려줌. 생략할 경우 클래스 이름을 테이블 이름으로 매핑 |
| @Id | Entity클래스의 필드를 테이블의 pk에 매핑 |
| @Column | 필드를 컬럼에 매핑 |
| @GeneratedValue | auto increment 컬럼임을 알려줌 |
| 매핑 정보가 없는 필드 | 필드명을 사용해서 컬럼명으로 매핑. ex)age라는 필드명에 매핑 정보가 없다면 age 컬럼에 매핑 |

controller ⇒ 자바 로직 수행

entity ⇒ 테이블 정보 설정

repository ⇒ 쿼리 정보 설정

- 외부에서 controller를 호출하면 repository가 entity를 가지고 처리한 후 반환
- repository의 경우, JpaRepository를 extends받으면 해당 Repository로 JPA의 대부분의 자동 쿼리 기능을 수행할 수 있다.

## JPA의 CRUD

- 저장 : jpa.persist(Student)
- 조회 : Student student = jpa.find(studentId)
- 수정 : student.setName(”홍길동”)
- 삭제 : jpa.remove(student)

## JPA 기본 사용 예시

- Entity 매니저를 애플리케이션 전체에서 한 개 생성하고 공유하여 사용

```java
EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
//이름이 jpa 인 매니저 팩토리 생성

EntityManager em = emf.createEntityManager();
//매니저 팩토리에서 Entity 매니저를 생성.
//jpa 대부분의 기능은 Entity 매니저가 제공한다
//Entity 매니저는 db 커넥션과 관계가 있다

em.persist(member); //데이터 저장

String userId = "user1";
Member member = em.find(Member.class, userId); //데이터 조회

Member member = em.find(Member.class, userId);
member.setName("변경할 이름"); //데이터 수정

em.remove(member); //데이터 삭제
```
