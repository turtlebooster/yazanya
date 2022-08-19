-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: ssafy_b310
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auth`
--

DROP TABLE IF EXISTS `auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth` (
  `id` int NOT NULL AUTO_INCREMENT,
  `access_token` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK71o3g4vv7a893ax9k7mrh63cd` (`user_id`),
  CONSTRAINT `FK71o3g4vv7a893ax9k7mrh63cd` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_num`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth`
--

LOCK TABLES `auth` WRITE;
/*!40000 ALTER TABLE `auth` DISABLE KEYS */;
INSERT INTO `auth` VALUES (8,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJtdXNhbjcyNSIsImlhdCI6MTY2MDY1MzkyMiwiZXhwIjoxNjYxMjU4NzIyfQ.5xqO35XmVr-OG8G1dfpr1St48ZzJ04t_HMObKdX4R4M',5),(9,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJtcmRid25zIiwiaWF0IjoxNjYwNjU0MDA5LCJleHAiOjE2NjEyNTg4MDl9.TF7fePBO757mHqmNzs6CiTIWA2F9dJuK50wcF-_Wbkw',6),(68,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJ5YXphbnlhIiwiaWF0IjoxNjYwNzI0OTA5LCJleHAiOjE2NjEzMjk3MDl9.e8AUWR5U2RZDIE1D-AhN33SS2FJS277KlviZORgy4o0',9),(74,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJzc2FmeSIsImlhdCI6MTY2MDc0NDYzMywiZXhwIjoxNjYxMzQ5NDMzfQ.tXfcx0qlTIG3W6p42vqw7SKhi7sOVn0R12ThkSN8RKc',10),(88,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJtYW4iLCJpYXQiOjE2NjA3ODQ4OTEsImV4cCI6MTY2MTM4OTY5MX0.FQmZh_mRuJ2CpnxQmgn18FxmElHyrzRY8p5LTCIx560',4),(90,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJzZW9ldW4iLCJpYXQiOjE2NjA3ODY0MjEsImV4cCI6MTY2MTM5MTIyMX0.dbX6QhY0Pc-fZO63mhN9sdd6ZG2V-sW-MCKhLcFPzt4',3),(106,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJ0a2R3bHM4NDEyIiwiaWF0IjoxNjYwODAwNjA4LCJleHAiOjE2NjE0MDU0MDh9.b_jw5qw7PAzfCUJjPuQZtTmIsQv8z7kjekcPo9P-5xk',11),(107,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJ6eGN2IiwiaWF0IjoxNjYwODAzNzcyLCJleHAiOjE2NjE0MDg1NzJ9.qKXRWQVF987tSv6QYt4yGd9J8vkfc4Fapuopu94VEvs',7),(114,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJ0dXJ0bGVib29zdGVyIiwiaWF0IjoxNjYwODQyNjM4LCJleHAiOjE2NjE0NDc0Mzh9.JQnJyA7xD0PbFyBBAMBLhvoqLeLiBIw4PWL3yFN4X60',1),(115,NULL,'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJzdGFyc2hhZG93IiwiaWF0IjoxNjYwODQyNzQ4LCJleHAiOjE2NjE0NDc1NDh9.DwnK7fLuUMqnbFfNaw8m2QVfGdU8j-k0P_-Qo0i4H2U',2);
/*!40000 ALTER TABLE `auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_confirm`
--

DROP TABLE IF EXISTS `email_confirm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email_confirm` (
  `confirm_num` int NOT NULL AUTO_INCREMENT,
  `confirm_code` varchar(255) DEFAULT NULL,
  `confirm_status` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`confirm_num`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_confirm`
--

LOCK TABLES `email_confirm` WRITE;
/*!40000 ALTER TABLE `email_confirm` DISABLE KEYS */;
INSERT INTO `email_confirm` VALUES (1,'117869',NULL,'ho'),(2,'754197',NULL,'ho6778@naver.com'),(3,'404656',NULL,'qqq5901@naver.com'),(4,'806069',NULL,'vcv0174@naver.com'),(5,'125146',NULL,'24ph00@naver.com'),(6,'625109',NULL,'msuan725@naver.com'),(7,'412286',NULL,'mrdbwns@naver.com'),(8,'657787',NULL,'wsu223@gmail.com'),(9,'960191',NULL,''),(11,'924979',NULL,'yazanya310@gmail.com'),(12,'172176',NULL,'rkarud1324@naver.com'),(13,'302351',NULL,'tkdwls8412@naver.com');
/*!40000 ALTER TABLE `email_confirm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `follow_num` int NOT NULL AUTO_INCREMENT,
  `follow_date` datetime(6) DEFAULT NULL,
  `follow_user_memo` varchar(255) DEFAULT NULL,
  `from_user` int DEFAULT NULL,
  `to_user` int DEFAULT NULL,
  PRIMARY KEY (`follow_num`),
  KEY `FKj1m5bb3kvf6cg52vwensya94s` (`from_user`),
  KEY `FKm6w8pih4a957f3v76efvhov3t` (`to_user`),
  CONSTRAINT `FKj1m5bb3kvf6cg52vwensya94s` FOREIGN KEY (`from_user`) REFERENCES `user` (`user_num`),
  CONSTRAINT `FKm6w8pih4a957f3v76efvhov3t` FOREIGN KEY (`to_user`) REFERENCES `user` (`user_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hashtag` (
  `hashtag_num` int NOT NULL AUTO_INCREMENT,
  `hashtag_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hashtag_num`),
  UNIQUE KEY `UK_t7kc0n92g7ui8m5kln70o2tu1` (`hashtag_name`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtag`
--

LOCK TABLES `hashtag` WRITE;
/*!40000 ALTER TABLE `hashtag` DISABLE KEYS */;
INSERT INTO `hashtag` VALUES (30,'1:1레슨'),(37,'3학년2반'),(85,'7기'),(63,'900'),(13,'900점'),(41,'cpa'),(38,'GSAT'),(53,'LEET'),(19,'NCS'),(50,'ssafy'),(62,'toeic'),(76,'간호'),(75,'간호사'),(65,'고1'),(20,'공기업'),(80,'공무원'),(81,'공부'),(33,'기계과'),(56,'기타'),(52,'로스쿨'),(51,'면접'),(82,'ㅂㅈㄷㄱ'),(86,'발표'),(73,'백엔드'),(2,'백준'),(58,'벚꽃연금'),(25,'변리사'),(54,'변호사'),(39,'삼성'),(47,'수능'),(66,'수학'),(49,'싸피'),(36,'싸피고등학교'),(32,'싸피대'),(67,'쎈'),(1,'알고리즘'),(10,'야자냐'),(43,'은행'),(15,'의대'),(48,'의사'),(68,'임용고시'),(9,'자유'),(55,'자유공부'),(57,'장범준'),(71,'재수'),(77,'전자과'),(78,'중간'),(34,'중간고사'),(69,'초등'),(60,'컴공'),(46,'컴활'),(45,'컴활1급'),(59,'코딩'),(35,'코딩테스트'),(83,'테르스'),(84,'테스트'),(74,'토이프로젝트'),(64,'토익'),(3,'프로그래머스'),(72,'프로그래밍'),(40,'필기'),(70,'필기시험'),(44,'하반기'),(79,'행시'),(61,'회계'),(42,'회계사');
/*!40000 ALTER TABLE `hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participation`
--

DROP TABLE IF EXISTS `participation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participation` (
  `participation_num` int NOT NULL AUTO_INCREMENT,
  `participation_enter_time` datetime(6) DEFAULT NULL,
  `participation_exit_time` datetime(6) DEFAULT NULL,
  `room_num` int DEFAULT NULL,
  `user_num` int DEFAULT NULL,
  PRIMARY KEY (`participation_num`),
  KEY `FK3mhtg4fcbral9gcjfoc4m8rxv` (`room_num`),
  KEY `FKe6obegs7nkgwqi551c01wy9hy` (`user_num`),
  CONSTRAINT `FK3mhtg4fcbral9gcjfoc4m8rxv` FOREIGN KEY (`room_num`) REFERENCES `room` (`room_num`),
  CONSTRAINT `FKe6obegs7nkgwqi551c01wy9hy` FOREIGN KEY (`user_num`) REFERENCES `user` (`user_num`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participation`
--

LOCK TABLES `participation` WRITE;
/*!40000 ALTER TABLE `participation` DISABLE KEYS */;
INSERT INTO `participation` VALUES (178,'2022-08-18 17:13:28.937000',NULL,36,2);
/*!40000 ALTER TABLE `participation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participation_history`
--

DROP TABLE IF EXISTS `participation_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participation_history` (
  `participation_history_num` int NOT NULL AUTO_INCREMENT,
  `participation_history_update_time` datetime(6) DEFAULT NULL,
  `room_num` int DEFAULT NULL,
  `user_num` int DEFAULT NULL,
  PRIMARY KEY (`participation_history_num`),
  KEY `FKf9isjcofbx1leg0k6agjmixtv` (`room_num`),
  KEY `FK8q57s71tbs45cvd8gpcisa5vl` (`user_num`),
  CONSTRAINT `FK8q57s71tbs45cvd8gpcisa5vl` FOREIGN KEY (`user_num`) REFERENCES `user` (`user_num`),
  CONSTRAINT `FKf9isjcofbx1leg0k6agjmixtv` FOREIGN KEY (`room_num`) REFERENCES `room` (`room_num`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participation_history`
--

LOCK TABLES `participation_history` WRITE;
/*!40000 ALTER TABLE `participation_history` DISABLE KEYS */;
INSERT INTO `participation_history` VALUES (1,'2022-08-16 07:06:28.289000',1,1),(5,'2022-08-16 07:14:03.720000',5,1),(10,'2022-08-16 07:19:06.212000',10,1),(13,'2022-08-16 07:21:26.347000',13,1),(17,'2022-08-16 07:29:41.682000',17,1),(18,'2022-08-17 05:46:45.865000',18,2),(19,'2022-08-16 07:35:24.212000',19,3),(20,'2022-08-16 07:36:08.209000',20,2),(21,'2022-08-16 07:36:18.805000',21,3),(22,'2022-08-16 07:38:27.576000',22,2),(23,'2022-08-16 07:38:08.489000',23,3),(24,'2022-08-16 07:38:58.293000',24,2),(25,'2022-08-16 07:39:07.748000',25,3),(26,'2022-08-17 04:39:47.622000',26,2),(27,'2022-08-16 07:40:03.391000',27,3),(28,'2022-08-18 08:13:25.523000',28,2),(29,'2022-08-16 07:42:13.887000',29,3),(31,'2022-08-16 07:44:23.498000',31,3),(32,'2022-08-16 07:45:46.884000',32,3),(33,'2022-08-16 07:49:19.984000',33,1),(34,'2022-08-16 07:50:20.178000',34,3),(35,'2022-08-18 14:46:11.822000',35,2),(36,'2022-08-18 17:13:28.939000',36,2),(37,'2022-08-16 07:52:58.202000',37,2),(38,'2022-08-16 07:53:07.949000',38,3),(39,'2022-08-18 10:14:29.493000',39,2),(40,'2022-08-16 07:55:38.877000',40,1),(41,'2022-08-16 07:59:02.604000',41,1),(42,'2022-08-16 08:02:22.528000',42,1),(43,'2022-08-18 17:11:29.333000',43,1),(44,'2022-08-16 12:48:15.340000',44,5),(47,'2022-08-16 12:49:55.365000',21,6),(48,'2022-08-17 08:48:09.089000',29,7),(49,'2022-08-17 04:31:42.475000',29,1),(50,'2022-08-17 04:31:52.996000',29,2),(70,'2022-08-17 06:52:06.639000',36,1),(71,'2022-08-18 00:45:46.949000',36,3),(72,'2022-08-17 07:10:00.917000',36,4),(78,'2022-08-17 13:58:59.213000',53,10),(85,'2022-08-18 05:31:25.348000',56,11);
/*!40000 ALTER TABLE `participation_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_num` int NOT NULL AUTO_INCREMENT,
  `room_active` tinyint DEFAULT '1',
  `room_capacity` int DEFAULT NULL,
  `room_description` varchar(255) DEFAULT NULL,
  `room_end_time` datetime(6) DEFAULT NULL,
  `room_has_pw` tinyint DEFAULT NULL,
  `room_name` varchar(255) NOT NULL,
  `room_participation_count` int DEFAULT NULL,
  `room_pw` varchar(255) DEFAULT NULL,
  `room_rest_time` int DEFAULT NULL,
  `room_sound` tinyint NOT NULL,
  `room_start_time` datetime(6) DEFAULT NULL,
  `room_study_time` int DEFAULT NULL,
  `room_thumbnail` varchar(255) DEFAULT NULL,
  `room_video` tinyint NOT NULL,
  `user_num` int DEFAULT NULL,
  PRIMARY KEY (`room_num`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,0,15,'알고리즘 문제풀이방',NULL,0,'알고리즘풀이방(백준, 프로그래머스)',0,'$2a$10$Yf8zpdYpVzOXHgOgyhntLOH8b5FUMWQ.JAPueMyxgG67FlGejZrRe',10,1,'2022-08-16 07:06:27.195000',50,'dceffeed-2f2e-4505-b0ac-66a87d3ba936.png',1,1),(5,0,15,'원하는 공부 하시면 됩니다\n조는 사람한테 야자냐! 시전해주기!',NULL,0,'자유공부 조는사람 깨워주기 방',0,'$2a$10$5ToDvtI/.en3voWijTMSIuo9vJ5WEVsak0U8VQBJwy1mqwRbqrR/2',15,1,'2022-08-16 07:14:02.845000',60,'c60c033f-9e8d-4ec7-bb9d-e692e4400163.jpeg',1,1),(10,0,15,'NCS 대비 공부방',NULL,1,'공기업 NCS 대비 공부방',0,'$2a$10$8yj8fRZJlM9yZcEeMyVY/OSi.77Y0.62YSfMpYpWivJ6l5VPzmYG6',0,1,'2022-08-16 07:19:02.900000',0,'5da36896-03a1-40a1-9646-4c077f770a73.jpeg',1,1),(13,0,15,'변리사 대비 매일 출첵 스터디',NULL,1,'변리사 준비 방',0,'$2a$10$Du1C4EqibczQUMJvnOIOBu.XCaqtZvgNmAxrAMH4c6/xmuHwCDI1G',0,1,'2022-08-16 07:21:22.520000',0,NULL,1,1),(17,0,15,'중간고사 대비 같이하자 혼자하기 힘들다',NULL,1,'싸피대 기계과 1학년 중간고사 대비',0,'$2a$10$K7GL3y8v3UBc4wuzAz1P8.mSx1sp1sUSHxaFv.WTOJMRZ/LDMZYEG',0,1,'2022-08-16 07:29:38.263000',0,'8ddba147-1b9f-40cc-9507-a651a8564cb6.jpeg',1,1),(18,0,6,'프로그래머스로 코딩테스트 준비하는 방입니다.',NULL,0,'코딩테스트 준비합시다.',0,'$2a$10$bK.sWVLaIDuHUq.zPPFJP.IGABmQ0CmhM8yL.BQ/jpdCb8dArdgQC',20,1,'2022-08-16 07:34:41.965000',120,'4606226e-0b4d-4a14-aab9-35f45745f1d0.jpeg',1,2),(19,0,5,'싸피고등학교 3학년 2반 공부방 입니다.',NULL,1,'싸피고등학교 3학년 2반 공부방',0,'$2a$10$YhkHDm4CgmDLDcaS2719deKqgiaOnuXFFT8sPlv8EgAJ1MwbIqWx.',10,1,'2022-08-16 07:35:21.945000',60,NULL,1,3),(20,0,6,'삼성갑시다! GSAT 고수 모십니다!',NULL,0,'GSAT대비 공부방',0,'$2a$10$192vo4a.1GqhPrWdi4Za6uIzq5lBYtcE7MdAFKlC8u98UEEkhTlSW',0,1,'2022-08-16 07:36:07.842000',0,'b5190769-9778-4f39-bdc5-d5ff13e781a2.jpeg',0,2),(21,0,10,'cpa 대비 공부방입니다',NULL,0,'CPA 올해는 합격한다',1,'$2a$10$ZoOj.qZyD1aT6A4IDc7oQ.XTg2fUgUz9A1PCwT/xtqVGw4PS2eOwS',10,1,'2022-08-16 07:36:18.567000',100,NULL,0,3),(22,0,6,'은행권 필기 시험 대비 경제, 재무관리, 회계 공부방입니다.',NULL,0,'은행필기방. 경제, 재무, 회계',0,'$2a$10$4zMXLcooHu68X8k.XCT2yuYnbxnJ5BmSe./U/dotTas3zxhZmM4/C',15,1,'2022-08-16 07:38:01.637000',120,'f998b3cc-4158-4b3f-b675-ab9ffedf8ec3.jpeg',1,2),(23,0,5,'너무 어려워요ㅠㅠ알려주세요',NULL,0,'컴활 1급 알려주세요',0,'$2a$10$WsNlOdqqJ9AAjgHIwgkipuq2OUOVE1zAq8F2g4D0gcgnLWkwRt2T6',0,1,'2022-08-16 07:38:08.175000',0,'e4e36007-b9db-4616-9cda-a076e2399060.jpeg',1,3),(24,0,4,'수능 의대 목표 방, 모의고사 점수 인증 필수 백분위 97 이상 만',NULL,0,'수능 의대 목표 방',0,'$2a$10$NYyKjPWktz3rxB4LIVJWbOiPps.1iN1xONJeSmmk4I/KQcIq5wGvi',10,1,'2022-08-16 07:38:57.992000',50,'30523819-9196-4a94-9e5e-a590b0033674.png',1,2),(25,0,4,'싸피 9기 면접 대비방입니다',NULL,1,'싸피 9기 면접 대비방',0,'$2a$10$z5qJlxZ8Zo5szrX0s8AlVuVzm5OQyZaybzZPYqhw0qf87Q1IvF5sG',0,1,'2022-08-16 07:39:02.802000',0,'bf4900ce-117a-46f3-87ba-3da5679fbfc7.png',0,3),(26,0,6,'로스쿨 준비방, LEET 대비',NULL,0,'로스쿨 준비방',0,'$2a$10$J4tBV3K2iwAXMMrlp/1gLebIAf6In2mJxc3TaiqkIPUy1trNHJ2cW',0,1,'2022-08-16 07:39:33.757000',0,'353941e0-74fc-4402-9cc3-dd9b5ec6c802.jpeg',1,2),(27,0,15,'자유롭게 공부해요. 자는 사람 깨워주기!',NULL,0,'자유공부방',0,'$2a$10$1S0SgHZRiAgmwi5RxaSduuQyd98brhC1cPnUK7U7wNHcXGVTwpcNW',0,0,'2022-08-16 07:40:03.047000',0,'be117ceb-0a5b-44c0-a071-841a2638cbbd.jpeg',1,3),(28,0,2,'장범준의 1:1 기타레슨방',NULL,0,'장범준의 1:1 기타레슨방',0,'$2a$10$d/kBZNRov0UW9pMleHMOEetv4ATxM4pJh2gSvajL1vM6eIa2MVSpO',0,1,'2022-08-16 07:40:13.000000',0,'f9992dd8-f298-4717-8dbb-fa45f37262c5.jpeg',1,2),(29,0,3,'각자 원하는 문제 풉시다',NULL,0,'코딩 공부하실분',0,'$2a$10$8oYVsMPyRYhZuwGZxGDsEOXv3K1ilmMXUdZ3vgo0Sxqv.nRIkhD5e',15,0,'2022-08-16 07:42:13.563000',70,'691c0d83-fc5d-49c7-84a0-5c9137725a28.jpeg',0,3),(31,0,15,'cpa 공부방',NULL,0,'미래의 회계사들',0,'$2a$10$axR9WWENMpwpdbJmMt.goOsOgzm2OzoIRWkT2HnaEOUl5V8V3E1hm',0,0,'2022-08-16 07:44:23.251000',0,'efe3ca83-f182-4401-bc8a-cee4f7d99f08.jpeg',1,3),(32,0,5,'토익 900점을 목표로 공부합니다',NULL,1,'토익 900점 목표',0,'$2a$10$9lvuVNomlYpPKDSYnUXl7eV2Sjb0.yBooU86rhncoYTNTdXDO6/Cy',0,0,'2022-08-16 07:45:44.459000',0,'fd623abe-9d0a-4387-9282-753c07184cce.jpeg',0,3),(33,0,15,'고1 쎈수학 문제풀이방',NULL,1,'고1 수학문제 풀기 (교재:쎈)',0,'$2a$10$OqgdOklpySCMpEfaWh8Er.TB39Kq/PPMJSg5QlfPD6f2vQBizQ3Ae',0,1,'2022-08-16 07:49:17.087000',0,NULL,1,1),(34,0,10,'모르는 거 물어보고 알려주는 건 좋은데\n잡담으로 떠들진마세요 제발',NULL,0,'자유공부',0,'$2a$10$AwVQSVljnCaxam9pzKKZcu1QyqthP/EoTwpQSvEN38RqxfoIFpaE6',15,1,'2022-08-16 07:50:01.498000',80,'52c8caf6-e0b9-47f0-b12e-8397498636c3.jpeg',1,3),(35,0,4,'미래의 선생님들 모여봅시다.',NULL,0,'초등임용고시 준비합시다.',0,'$2a$10$tZS1818Bmm0jITb25DztzO0dESstPEaO1zv6at8Fl6IH9tlNXdnSG',0,1,'2022-08-16 07:50:43.200000',0,'e19bbdae-7d64-441a-8547-6b6d1c355779.png',1,2),(36,0,8,'공기업 필기 대비용 NCS공부방',NULL,0,'공기업 필기 대비방',1,'$2a$10$vdDjsZZkN2Yq0O.xoqhkU.66gzEkunVTJt4A4CEc.qgEHcDFrB8qy',20,1,'2022-08-16 07:51:40.718000',120,NULL,0,2),(37,0,15,'수능 재수생들을 위한 방입니다. 인증필수',NULL,1,'수능 재수생만 ㅠㅠ',0,'$2a$10$hCqvdg/.8qbZn01npqMnBOZYSmxWS.IrnR.1f7uKug4UGopqJ13oC',0,1,'2022-08-16 07:52:54.560000',0,'dd4073c5-de12-43e3-839a-76faf2bbadfb.jpeg',1,2),(38,0,15,'각자 문제 가져와서 풀기. 모르는 건 채팅으로!',NULL,1,'코딩 공부 각자 하기',0,'$2a$10$BQ1CvfWGjsYL8p0Wzatk2exHJ7sktIq3Jhf6WCChedOG49SoQTQhe',15,0,'2022-08-16 07:53:05.458000',110,'7e22c93d-e940-428e-b611-886d7be6826f.jpeg',0,3),(39,0,2,'토이프로젝트 같이하실 백엔드개발자 모십니다.',NULL,1,'토이프로젝트 같이하실분',0,'$2a$10$4FPdFl6ybsUTg5y7om1KiejJ4pXmZxLg1QQ2IDBI9ODBxw5Y1dByi',0,1,'2022-08-16 07:54:18.631000',0,'18778a83-a571-4876-b33b-1b016c04cfe8.jpeg',1,2),(40,0,8,'간호사 국시 매일 축첵 방',NULL,0,'간호사 국시 대비 한 달 바짝',0,'$2a$10$7UQzHba/y3i.Mcs3Iv.DFuz2d8u9AqvbedsDP1ZVD9CsOMSLkgexG',0,1,'2022-08-16 07:55:38.310000',0,'53c7ae35-b64a-4e5b-932a-c098762c8a39.jpeg',1,1),(41,0,5,'CPA 대비 방\n매일 출석',NULL,1,'싸피대 CPA 대비방',0,'$2a$10$zbBw/RLNIBTUMx9gGwHsLuYTl.p26XBkEhWLpCOO5UILLbBVBcoXe',0,1,'2022-08-16 07:59:00.043000',0,'5e3ee53d-f5b9-4919-b9de-8741adea0714.jpeg',1,1),(42,0,15,'전자과 중간고사 공부 분위기 내기\n마이크 없이 각자 공부',NULL,0,'싸피대 전자과 중간대비 NO 마이크',0,'$2a$10$UjYve9P207t5NqMjGM8gZuyH23H2NxPLIKJO4q.o6xN5NgD/B1YZm',15,0,'2022-08-16 08:02:21.856000',60,'695c99c7-e995-4541-97da-ca5c34520434.jpeg',1,1),(43,0,15,'50분 공부 10분 휴식',NULL,1,'행시 매일 여는 방 ',0,'$2a$10$83QfBLrP6HOtQMOXU.s99O93p4JN/U/DAUMHdL8XSsxqTWMmSXlL.',10,1,'2022-08-16 08:52:10.558000',50,NULL,1,1),(44,0,15,'언레일드 공부방',NULL,0,'게임방',0,'$2a$10$rk/qllq81WQy5BwOVKZLsunZU8o1nVpjkxw86vEjK5ye19MaNqYKK',0,1,'2022-08-16 12:46:16.524000',0,NULL,1,5),(53,0,15,'1234',NULL,1,'들어와라',0,'$2a$10$KHUtNWuaCDJK9Or8nlV36.xqF5DnVfDn.oJkQqh.C1QVYEzOJJnMq',0,1,'2022-08-17 13:58:57.384000',0,NULL,1,10),(56,0,15,'ㅇㅁㅈㅇㅁㅈ',NULL,0,'ㅇㅇㅈ',0,'$2a$10$m.JMsN/Xc0CvOQyLgo8OEe4oV2DbzcLjGTWQ.XNpbVgRn0.hhIDOG',0,1,'2022-08-18 05:31:07.497000',0,NULL,1,11);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_forced_exit`
--

DROP TABLE IF EXISTS `room_forced_exit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_forced_exit` (
  `room_forced_exit_num` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `room_num` int DEFAULT NULL,
  PRIMARY KEY (`room_forced_exit_num`),
  KEY `FK2bi0qlseia6lqdpdre2t32n5p` (`room_num`),
  CONSTRAINT `FK2bi0qlseia6lqdpdre2t32n5p` FOREIGN KEY (`room_num`) REFERENCES `room` (`room_num`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_forced_exit`
--

LOCK TABLES `room_forced_exit` WRITE;
/*!40000 ALTER TABLE `room_forced_exit` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_forced_exit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_hashtag`
--

DROP TABLE IF EXISTS `room_hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_hashtag` (
  `room_hashtag_num` int NOT NULL AUTO_INCREMENT,
  `hashtag_num` int DEFAULT NULL,
  `room_num` int DEFAULT NULL,
  PRIMARY KEY (`room_hashtag_num`),
  KEY `FKt70l5u69d9seg6pleaqlaeji4` (`hashtag_num`),
  KEY `FKakx29vjupsjhmbg84xil1n5pg` (`room_num`),
  CONSTRAINT `FKakx29vjupsjhmbg84xil1n5pg` FOREIGN KEY (`room_num`) REFERENCES `room` (`room_num`),
  CONSTRAINT `FKt70l5u69d9seg6pleaqlaeji4` FOREIGN KEY (`hashtag_num`) REFERENCES `hashtag` (`hashtag_num`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_hashtag`
--

LOCK TABLES `room_hashtag` WRITE;
/*!40000 ALTER TABLE `room_hashtag` DISABLE KEYS */;
INSERT INTO `room_hashtag` VALUES (1,1,1),(2,2,1),(3,3,1),(9,9,5),(10,10,5),(19,19,10),(20,20,10),(25,25,13),(33,32,17),(34,33,17),(35,34,17),(36,1,18),(37,35,18),(38,3,18),(39,36,19),(40,37,19),(41,38,20),(42,39,20),(43,40,20),(44,41,21),(45,42,21),(46,43,22),(47,40,22),(48,44,22),(49,45,23),(50,46,23),(51,47,24),(52,15,24),(53,48,24),(54,49,25),(55,50,25),(56,51,25),(57,52,26),(58,53,26),(59,54,26),(60,55,27),(61,56,28),(62,57,28),(63,58,28),(64,59,29),(65,1,29),(66,60,29),(70,61,31),(71,41,31),(72,42,31),(73,62,32),(74,63,32),(75,64,32),(76,65,33),(77,66,33),(78,67,33),(79,55,34),(80,68,35),(81,69,35),(82,19,36),(83,20,36),(84,70,36),(85,47,37),(86,71,37),(87,1,38),(88,2,38),(89,59,38),(90,72,39),(91,73,39),(92,74,39),(93,75,40),(94,76,40),(95,41,41),(96,42,41),(97,32,41),(98,32,42),(99,77,42),(100,78,42),(101,79,43),(102,80,43);
/*!40000 ALTER TABLE `room_hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_thumbnail`
--

DROP TABLE IF EXISTS `room_thumbnail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_thumbnail` (
  `thumbnail_id` varchar(255) NOT NULL,
  `thumbnail_name` varchar(255) NOT NULL,
  `thumnail_path` varchar(255) NOT NULL,
  PRIMARY KEY (`thumbnail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_thumbnail`
--

LOCK TABLES `room_thumbnail` WRITE;
/*!40000 ALTER TABLE `room_thumbnail` DISABLE KEYS */;
INSERT INTO `room_thumbnail` VALUES ('0d967e9a-6c4c-4901-8f97-188339b5a6c3','17da72639834cc05e.png','0d967e9a-6c4c-4901-8f97-188339b5a6c3.png'),('0de78b77-3420-4a38-a65b-bb12e36e5b50','다운로드.jpg','0de78b77-3420-4a38-a65b-bb12e36e5b50.jpeg'),('1862d3c9-41eb-4c2a-b034-2f15406d6cdc','다운로드.png','1862d3c9-41eb-4c2a-b034-2f15406d6cdc.png'),('18778a83-a571-4876-b33b-1b016c04cfe8','iewek-gnos-hhUx08PuYpc-unsplash.jpg','18778a83-a571-4876-b33b-1b016c04cfe8.jpeg'),('1a694aab-8c01-4043-a727-d5414d32fe42','saffy.png','1a694aab-8c01-4043-a727-d5414d32fe42.png'),('1ea914e4-5142-4a05-85d7-e095298afc50','다운로드 (2).jpg','1ea914e4-5142-4a05-85d7-e095298afc50.jpeg'),('25a1cb64-da59-4c43-9775-b7bc42638521','17da72639834cc05e.png','25a1cb64-da59-4c43-9775-b7bc42638521.png'),('30523819-9196-4a94-9e5e-a590b0033674','K-001.png','30523819-9196-4a94-9e5e-a590b0033674.png'),('353941e0-74fc-4402-9cc3-dd9b5ec6c802','SE-b5ad0071-ba33-46b1-84d6-30ce9f880024.jpg','353941e0-74fc-4402-9cc3-dd9b5ec6c802.jpeg'),('4303aeeb-ce47-4858-9af6-d15fe1cb6f01','17da72639834cc05e.png','4303aeeb-ce47-4858-9af6-d15fe1cb6f01.png'),('4606226e-0b4d-4a14-aab9-35f45745f1d0','화면 캡처 2022-08-16 163429.jpg','4606226e-0b4d-4a14-aab9-35f45745f1d0.jpeg'),('46593e35-941e-4c27-9732-52fe933ba539','17da72639834cc05e.png','46593e35-941e-4c27-9732-52fe933ba539.png'),('4ce17f0c-e831-4478-8b17-7d7352d80c03','다운로드 (1).png','4ce17f0c-e831-4478-8b17-7d7352d80c03.png'),('4e572ea2-41a8-4499-8653-5c84691e6d39','marissa-grootes-flRm0z3MEoA-unsplash.jpg','4e572ea2-41a8-4499-8653-5c84691e6d39.jpeg'),('4e643f79-677d-46d2-88ac-172d57e18653','SSAFY.png','4e643f79-677d-46d2-88ac-172d57e18653.png'),('52c8caf6-e0b9-47f0-b12e-8397498636c3','화면 캡처 2022-08-16 164936.jpg','52c8caf6-e0b9-47f0-b12e-8397498636c3.jpeg'),('53c7ae35-b64a-4e5b-932a-c098762c8a39','nurse.jpg','53c7ae35-b64a-4e5b-932a-c098762c8a39.jpeg'),('5da36896-03a1-40a1-9646-4c077f770a73','closeup-person-filling-out-questionary-form.jpg','5da36896-03a1-40a1-9646-4c077f770a73.jpeg'),('5e3ee53d-f5b9-4919-b9de-8741adea0714','calculate.jpg','5e3ee53d-f5b9-4919-b9de-8741adea0714.jpeg'),('691c0d83-fc5d-49c7-84a0-5c9137725a28','images.jpg','691c0d83-fc5d-49c7-84a0-5c9137725a28.jpeg'),('695c99c7-e995-4541-97da-ca5c34520434','breadboard.jpg','695c99c7-e995-4541-97da-ca5c34520434.jpeg'),('7a8e978e-e942-4065-a975-b1313adc02fc','K-001.png','7a8e978e-e942-4065-a975-b1313adc02fc.png'),('7e22c93d-e940-428e-b611-886d7be6826f','화면 캡처 2022-08-16 165235.jpg','7e22c93d-e940-428e-b611-886d7be6826f.jpeg'),('8bf3e8fd-c9e3-4209-ade9-4c63e97cbe98','17da72639834cc05e.png','8bf3e8fd-c9e3-4209-ade9-4c63e97cbe98.png'),('8ddba147-1b9f-40cc-9507-a651a8564cb6','mechanical-engineering.jpg','8ddba147-1b9f-40cc-9507-a651a8564cb6.jpeg'),('9b1c3459-f119-4b99-a61e-21341128799b','다운로드 (1).jpg','9b1c3459-f119-4b99-a61e-21341128799b.jpeg'),('9d63d2cd-6585-41e2-ba70-1c6df8ede7f4','17da72639834cc05e.png','9d63d2cd-6585-41e2-ba70-1c6df8ede7f4.png'),('b5190769-9778-4f39-bdc5-d5ff13e781a2','20210223518531.jpg','b5190769-9778-4f39-bdc5-d5ff13e781a2.jpeg'),('be117ceb-0a5b-44c0-a071-841a2638cbbd','다운로드 (1).jpg','be117ceb-0a5b-44c0-a071-841a2638cbbd.jpeg'),('bf4900ce-117a-46f3-87ba-3da5679fbfc7','다운로드.png','bf4900ce-117a-46f3-87ba-3da5679fbfc7.png'),('c60c033f-9e8d-4ec7-bb9d-e692e4400163','sleepyman.jpg','c60c033f-9e8d-4ec7-bb9d-e692e4400163.jpeg'),('dceffeed-2f2e-4505-b0ac-66a87d3ba936','sample2.png','dceffeed-2f2e-4505-b0ac-66a87d3ba936.png'),('dd4073c5-de12-43e3-839a-76faf2bbadfb','philippe-bout-93W0xn4961g-unsplash.jpg','dd4073c5-de12-43e3-839a-76faf2bbadfb.jpeg'),('e19bbdae-7d64-441a-8547-6b6d1c355779','1553623644.png','e19bbdae-7d64-441a-8547-6b6d1c355779.png'),('e4e36007-b9db-4616-9cda-a076e2399060','다운로드.jpg','e4e36007-b9db-4616-9cda-a076e2399060.jpeg'),('e9560a62-5c99-4d43-a182-e910bb46d0f0','0001250693_004_20210109090124740.jpg','e9560a62-5c99-4d43-a182-e910bb46d0f0.jpeg'),('efe3ca83-f182-4401-bc8a-cee4f7d99f08','다운로드 (2).jpg','efe3ca83-f182-4401-bc8a-cee4f7d99f08.jpeg'),('f998b3cc-4158-4b3f-b675-ab9ffedf8ec3','marissa-grootes-flRm0z3MEoA-unsplash.jpg','f998b3cc-4158-4b3f-b675-ab9ffedf8ec3.jpeg'),('f9992dd8-f298-4717-8dbb-fa45f37262c5','0001250693_004_20210109090124740.jpg','f9992dd8-f298-4717-8dbb-fa45f37262c5.jpeg'),('fa83f918-ee23-4213-b82d-bcced423f082','SE-b5ad0071-ba33-46b1-84d6-30ce9f880024.jpg','fa83f918-ee23-4213-b82d-bcced423f082.jpeg'),('fd623abe-9d0a-4387-9282-753c07184cce','다운로드 (3).jpg','fd623abe-9d0a-4387-9282-753c07184cce.jpeg');
/*!40000 ALTER TABLE `room_thumbnail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todo`
--

DROP TABLE IF EXISTS `todo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `todo` (
  `todo_num` int NOT NULL AUTO_INCREMENT,
  `todo_content` varchar(255) DEFAULT NULL,
  `todo_end_time` datetime(6) DEFAULT NULL,
  `todo_name` varchar(255) DEFAULT NULL,
  `todo_progress` int DEFAULT NULL,
  `todo_start_time` datetime(6) DEFAULT NULL,
  `user_num` int DEFAULT NULL,
  PRIMARY KEY (`todo_num`),
  KEY `FKc0wx09srrav28iskwa2ua2v2l` (`user_num`),
  CONSTRAINT `FKc0wx09srrav28iskwa2ua2v2l` FOREIGN KEY (`user_num`) REFERENCES `user` (`user_num`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todo`
--

LOCK TABLES `todo` WRITE;
/*!40000 ALTER TABLE `todo` DISABLE KEYS */;
INSERT INTO `todo` VALUES (5,'',NULL,'발표하기',2400,NULL,2);
/*!40000 ALTER TABLE `todo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_num` int NOT NULL AUTO_INCREMENT,
  `email_confirm` bit(1) DEFAULT b'0',
  `profile_belong_to` varchar(255) DEFAULT NULL,
  `profile_picture_link` varchar(255) DEFAULT NULL,
  `profile_planner_set` varchar(20) DEFAULT '0,1,2',
  `profile_rank` varchar(20) DEFAULT '아이언',
  `profile_self_introduce` varchar(255) DEFAULT NULL,
  `profile_total_study_time` int DEFAULT NULL,
  `user_email` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_nickname` varchar(255) NOT NULL,
  `user_pw` varchar(255) NOT NULL,
  `user_room_count` int DEFAULT '0',
  `user_status_num` int NOT NULL,
  `music_play_list` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_num`),
  UNIQUE KEY `UK_j09k2v8lxofv2vecxu2hde9so` (`user_email`),
  UNIQUE KEY `UK_a3imlf41l37utmxiquukk8ajc` (`user_id`),
  UNIQUE KEY `UK_cr59axqya8utby3j37qi341rm` (`user_nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,_binary '\0',NULL,'profile.png','0,1,2,3,4','브론즈','코딩 하고싶은 사람',11,'ho6778@naver.com','turtlebooster','최진호','빠른거북이','$2a$10$2Ta/OraIb5diepfOUlrtSuiWh8aEq00DHqSJPTN4cWZ2pltL.19xO',10,0,NULL),(2,_binary '\0',NULL,'79e2a289-dc21-43b5-b736-170f8ef843b5_starshadow.jpeg','0,1,2,3,4','골드','',297,'qqq5901@naver.com','starshadow','양지호','별그늘','$2a$10$ZvrCnE7MvrwB3ZozLCmJZegyUDC99NTITeTSi6T6HCj4XBNr183TK',7,0,NULL),(3,_binary '\0',NULL,'58c447e0-e860-4d9a-9eac-5f0d2bf46889_seoeun.jpeg','0,1,2,3,4','브론즈',NULL,15,'vcv0174@naver.com','seoeun','박서은','서은','$2a$10$kKj9Ov1MehAXJqwK764t/ecdjsSw7KMmkmdqayxAXGbSbz7FykqOa',10,0,NULL),(4,_binary '\0',NULL,'profile.png','0,1,2,3,4','브론즈',NULL,4,'24ph00@naver.com','man','남자','ㅎㅎ','$2a$10$Lk67vL7rYIe9SvF2RnbGwOE8DBa.kUvULey8vv9oZ2yUqPtOxkW8O',0,0,NULL),(5,_binary '\0',NULL,'profile.png','0,1,2,3,4','브론즈',NULL,0,'msuan725@naver.com','musan725','박심인','CTZN','$2a$10$xceBe/ZwN3rBd9NGjc3uK.oUC2IpcDG4OEWpB5JBNBnntcLLUJFF6',1,0,NULL),(6,_binary '\0',NULL,'profile.png','0,1,2,3,4','다이아몬드',NULL,2,'mrdbwns@naver.com','mrdbwns','.','아니요눈감은건데요','$2a$10$qfCMdBfTVYBcZgzn3oLQj..whbyrjGz3KbjkHwgW9lnoxcPrUc/7i',0,0,NULL),(7,_binary '\0',NULL,'ccffae2f-f54c-4019-a007-a42f4d9f7357_zxcv.png','0,1,2,3,4','브론즈',NULL,8,'wsu223@gmail.com','zxcv','zxcvzxcv','ㅇㅅㅇ','$2a$10$tuXn.up2TKj1E38oWptM2uZ8tToe/BzI.kCBN.mq4kVJo5oIOV5lm',0,0,NULL),(9,_binary '\0',NULL,'profile.png','0,1,2,3,4','브론즈',NULL,0,'yazanya310@gmail.com','yazanya','야자냐','야자냐','$2a$10$LAng21.Tv9q8y6grzxrS/eHGLdzv2jt7HfyEpaz2mga0nE8R32foO',0,0,NULL),(10,_binary '\0',NULL,'profile.png','0,1,2,3,4','브론즈','김싸피입니다',0,'rkarud1324@naver.com','ssafy','김싸피','김싸피','$2a$10$QnKzsvNelvqHJU.AIntBX.yBbLSu5A1DAMNB2WH2LS4FjWBVnX7kG',1,0,NULL),(11,_binary '\0',NULL,'profile.png','0,1,2,3,4','브론즈',NULL,0,'tkdwls8412@naver.com','tkdwls8412','송상진','반장','$2a$10$iyCuIvkIgXBicfFUa/zGjuUzAavrxnrxcHbroVD54c9NwhNxpAjyy',1,0,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_hashtag`
--

DROP TABLE IF EXISTS `user_hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_hashtag` (
  `user_hashtag_num` int NOT NULL AUTO_INCREMENT,
  `hashtag_num` int DEFAULT NULL,
  `user_num` int DEFAULT NULL,
  PRIMARY KEY (`user_hashtag_num`),
  KEY `FKqmd1u24fpv898sxecp81ero7u` (`hashtag_num`),
  KEY `FKbmy1jfhsfspqivtu5y1it7w5t` (`user_num`),
  CONSTRAINT `FKbmy1jfhsfspqivtu5y1it7w5t` FOREIGN KEY (`user_num`) REFERENCES `user` (`user_num`),
  CONSTRAINT `FKqmd1u24fpv898sxecp81ero7u` FOREIGN KEY (`hashtag_num`) REFERENCES `hashtag` (`hashtag_num`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_hashtag`
--

LOCK TABLES `user_hashtag` WRITE;
/*!40000 ALTER TABLE `user_hashtag` DISABLE KEYS */;
INSERT INTO `user_hashtag` VALUES (19,59,1),(20,9,1),(24,59,7),(29,49,10),(33,49,3),(34,1,3),(35,59,3),(38,1,2),(39,59,2);
/*!40000 ALTER TABLE `user_hashtag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19  2:13:32
