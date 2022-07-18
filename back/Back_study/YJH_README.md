# JPA

JPA란 Java Persistence API의 약자이며 자바의 ORM을 위한 표준 기술로 Hibernate, Spring JPA, EcliplseLink 등 과 같은 구현체가 있고 이것의 표준 인터페이스가 JPA 이다.

ORM(Object-Relational Mappin)이란 자바의 객체와 관계형 DB를 맵핑하는 것으로 DB의 특정 테이블이 자바의 객체로 맵핑되어 SQL문을 일일히 작성하지 않고 객체로 구현할 수 있도록 하는 프레임 워크이다.

JPA의 장점으로, SQL위주의 MyBatis 프로젝트와 비교하여 쿼리를 하나하나 작성 할 필요가 없어 코드량이 엄청나게 줄어든다.또한 객체 위주로 코드가 작성되다보니 가독성도 좋고, 여러 가지 요구사항으로 기능 수정이 발생해도 DB부터 더 간편하게 수정이 가능하다. 또한 Oracle, MySQL 등 DB 벤더에 따라 조금씩 다른 SQL 문법 때문에 애플리케이션이 DB에 종속될 수 밖에 없었는데, JPA는 직접 쿼리를 작성하는 것이 아니라서 DB 벤더에 독립적으로 개발이 가능하다.



## Spring, MySQL 연동하기

프로젝트 시작 시 MySQL driver 라이브러리 포함하여 시작

```java
dependencies {
       implementation 'org.springframework.boot:spring-boot-starter'
       runtimeOnly 'mysql:mysql-connector-java'
       testImplementation 'org.springframework.boot:spring-boot-starter-test'
    }
```

빌드 실행 - MySQL을 사용한다면 `application.properties` 파일에서 커넥션 정보 작성하여 설정

```
//src/main/resources/application.properties
    
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/{DB 명}
serverTimezone=UTC&characterEncoding=UTF-8
    spring.datasource.username={id}
    spring.datasource.password={password}
```



## Entity

데이터베이스에 쓰일 필드와 여러 엔티티 간 연간관계를 정의한다. 데이터베이스는 엑셀처럼 2차원 테이블이라고 생각하면 되는데, 이 테이블에 서비스에서 필요한 정보를 다 저장하고 활용하게 된다.

그림과 같이 세로의 열 부분이 `Column`이고, 가로의 행 부분이 엔티티 객체가 된다. 이 테이블 전체가 엔티티 이고, 각 1개의 행들이 엔티티 객체가 되는 것이라고 생각하면 된다.

![img](https://raw.githubusercontent.com/jihoyangKR/img/master/%20img.png?token=AXI75U2ZI3UTE6UGSDDSUA3C2UD5I)

엔티티를 사용하기 위해서는 해당 클래서가 Entity라는 것을 Spring에 알려주어야 하는데 이 역할을 `@Entity`가 한다.

1. DB 테이블 이름과 Entity 클래스 이름이 같은가?

   꼭 같아야 하는 것은 아니다. 만약 다르다면 `@Entity(name="테이블명")` 으로 사용 가능하다.

2. Primary Key가 존재하는가?

   Primary Key가 존재한다면 해당 필드에 `@Id` 어노테이션과 `@GeneratedValue(strategy = GenerationType.IDENTITY)` 어노테이션을 추가해 MySQL 전략을 사용할 수 있다.

3. DB 테이블의 필드 명과 Entity 클래스의 필드명이 다를 수 있다.

   만약 다르다면 `@column(name = DB 필드 명)` 어노테이션을 사용하여 맞추자.

---

### @어노테이션

**@Entity** : 클래스 위에 선언하여 이 클래스가 엔티티임을 알려준다. 이렇게 되면 JPA에서 정의된 필드들을 바탕으로 데이터베이스에 테이블을 만들어 준다.

**@Builder**: 해당 클래스에 해당하는 엔티티 객체를 만들 때 빌더 패턴을 이용해서 만들 수 있도록 지정해주는 어노테이션이다. 이렇게 선언해놓으면 나중에 다른 곳에서 Board.builder().{여러가지 필드의 초기값 선언}.build() 형태로 객체를 만들 수 있다.

**@AllArgsConstructor** : 선언된 모든 필드를 파라미터로 갖는 생성자를 자동으로 만들어준다.

**@NoArgsConstructor** : 파라미터가 아예 없는 기본 생성자를 자동으로 만들어 준다.

**@Getter** : 각 필드값을 조회할 수 있는 getter를 자동으로 생성해 준다. 예를 들어 다른 파일에서 Board 객체의 title 값을 얻고 싶다면 getTitle()메서드를 정의해서 해당 객체의 title값을 얻어오게 되는데, 해당 메서드를 굳이 작성하지 않아도 자동으로 생성해 준다.

변수는 보통 직접 접근 및 변경이 안되도록 private 선언자를 통해 지정한다. title 이라는 변수를 조회하기 위해서 getTitle() 이라는 메서드를 선언하고, title을 리턴하도록 해준다. 이 메서드를 **getter**라고 부른다.

**@ToString** : 해당 클래스에 선언된 필드들을 모두 출력할 수 있는 toString 메서드를 자동으로 생성할 수 있도록 해준다.

**@Id, @GeneratedValue** : 해당 엔티티의 주요 키(Primary Key, PK)가 될 값을 지정해주는 것이 `@Id` 이다. `@GeneratedValue`는 이 PK가 자동으로 1씩 증가하는 형태로 생성될지 등을 결정해주는 어노테이션이다.

**@ManyToOne** : 해당 엔티티와 다른 엔티티를 관계짓고 싶을 때 쓰는 어노테이션이다.  ManyToOne이라고 부르는 이유는 Writer 입장에서 Board는 여러 개가 될 수 있기 때문에 Writer : Board = 1 : N 관계가 되기 때문이다.

## Repository

Entity에 의해 생성된 DB에 접근하는 메서드(ex) findAll()) 들을 사용하기 위한 인터페이스이다.

엔티티를 선언함으로써 데이터베이스 구조를 만들었다면, 여기에 어떤 값을 넣거나, 넣어진 값을 조회하는 등의 CRUD(Create, Read, Update, Delete)를 해야 쓸모가 있는데, 이것을 어떻게 할 것인지 정의해주는 계층이라고 생각하면 된다.

**JpaRepository**를 상속받는다.

```java
public interface UserRepository extends JpaRepository<대상으로 지정할 엔티티, 해당 엔티티의 PK타입>
```

이렇게 extends를 통해 상속을 받고 나면, 해당 레포지토리의 객체를 이용해서 기본적으로 제공되는 메서드 `save()`, `findAll()`,`get()` 등을 사용할 수 있게 된다.

