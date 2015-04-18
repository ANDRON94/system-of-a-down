CREATE DATABASE  IF NOT EXISTS `calendar` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `calendar`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: calendar
-- ------------------------------------------------------
-- Server version	5.5.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `computer`
--

DROP TABLE IF EXISTS `computer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` int(11) NOT NULL,
  `power` int(11) NOT NULL,
  `quality` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer`
--

LOCK TABLES `computer` WRITE;
/*!40000 ALTER TABLE `computer` DISABLE KEYS */;
INSERT INTO `computer` VALUES (1,5000,5,5),(2,3200,4,3),(3,4300,4,5),(4,2500,3,4),(5,1500,2,3);
/*!40000 ALTER TABLE `computer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `computer_detail`
--

DROP TABLE IF EXISTS `computer_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computer_detail` (
  `id` int(11) NOT NULL,
  `detail_id` int(11) NOT NULL,
  `computer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_detail_has_computer_computer1_idx` (`computer_id`),
  KEY `fk_detail_has_computer_detail1_idx` (`detail_id`),
  CONSTRAINT `fk_detail_has_computer_computer1` FOREIGN KEY (`computer_id`) REFERENCES `computer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_detail_has_computer_detail1` FOREIGN KEY (`detail_id`) REFERENCES `detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computer_detail`
--

LOCK TABLES `computer_detail` WRITE;
/*!40000 ALTER TABLE `computer_detail` DISABLE KEYS */;
INSERT INTO `computer_detail` VALUES (1,1,1),(2,6,1),(3,11,1),(4,16,1),(5,21,1),(6,2,2),(7,7,2),(8,12,2),(9,17,2),(10,22,2),(11,3,3),(12,8,3),(13,13,3),(14,18,3),(15,23,3),(16,4,4),(17,9,4),(18,14,4),(19,19,4),(20,24,4),(21,5,5),(22,10,5),(23,15,5),(24,20,5),(25,25,5);
/*!40000 ALTER TABLE `computer_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `worker_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `detail_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_worker_has_order_order1_idx` (`order_id`),
  KEY `fk_worker_has_order_worker1_idx` (`worker_id`),
  KEY `fk_contract_detail1_idx` (`detail_id`),
  CONSTRAINT `fk_worker_has_order_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_worker_has_order_worker1` FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contract_detail1` FOREIGN KEY (`detail_id`) REFERENCES `detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,1,1,'2015-05-18 14:00:00','2015-05-19 14:00:00',3),(2,1,2,'2015-05-17 14:00:00','2015-05-18 14:00:00',8),(3,1,5,'2015-05-06 14:00:00','2015-05-07 14:00:00',11),(4,2,3,'2015-05-13 14:00:00','2015-05-14 14:00:00',9),(5,2,2,'2015-05-15 14:00:00','2015-05-16 14:00:00',12),(6,2,1,'2015-05-18 14:00:00','2015-05-19 14:00:00',19),(7,2,2,'2015-05-15 14:00:00','2015-05-16 14:00:00',24),(8,2,3,'2015-05-13 14:00:00','2015-05-14 14:00:00',23),(9,2,4,'2015-05-09 14:00:00','2015-05-10 14:00:00',15),(10,3,5,'2015-05-06 14:00:00','2015-05-07 14:00:00',5),(11,3,2,'2015-05-15 14:00:00','2015-05-16 14:00:00',6),(12,3,3,'2015-05-15 14:00:00','2015-05-16 14:00:00',7),(13,3,4,'2015-05-09 14:00:00','2015-05-10 14:00:00',18),(14,3,1,'2015-05-19 14:00:00','2015-05-20 14:00:00',17),(15,3,2,'2015-05-14 14:00:00','2015-05-15 14:00:00',16),(16,4,3,'2015-05-10 14:00:00','2015-05-11 14:00:00',1),(17,4,4,'2015-05-10 14:00:00','2015-05-11 14:00:00',3),(18,4,2,'2015-05-14 14:00:00','2015-05-15 14:00:00',4),(19,4,1,'2015-05-17 14:00:00','2015-05-18 14:00:00',22),(20,4,3,'2015-05-15 14:00:00','2015-05-16 14:00:00',21);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail`
--

DROP TABLE IF EXISTS `detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detail_type_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `quality` varchar(45) NOT NULL,
  `power` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `detail_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_detail_detail_type1_idx` (`detail_type_id`),
  CONSTRAINT `fk_detail_detail_type1` FOREIGN KEY (`detail_type_id`) REFERENCES `detail_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail`
--

LOCK TABLES `detail` WRITE;
/*!40000 ALTER TABLE `detail` DISABLE KEYS */;
INSERT INTO `detail` VALUES (1,1,500,'3',1,'AMD Athlon'),(2,1,700,'4',2,'AMD Duron'),(3,1,1500,'4',4,'Intel Core i3'),(4,1,1000,'2',4,'Intel Core i5'),(5,1,2000,'5',5,'Intel Core i7'),(6,2,700,'3',2,'AMD Radeon 7990'),(7,2,900,'3',3,'AMD Radeon HD 9900'),(8,2,1100,'5',3,'nVidia geForce 340m'),(9,2,2300,'4',5,'nVidia geForce 940'),(10,2,1500,'2',4,'nVidia geForce 720'),(11,3,400,'5',2,'QC5000-ITX'),(12,3,700,'4',4,'C70M1'),(13,3,600,'3',4,'K7S41GX2'),(14,3,500,'4',3,'AM1B-ITX'),(15,3,1000,'4',4,'970M Pro3'),(16,4,400,'5',3,'G.Skill Ripjaws 4'),(17,4,500,'2',4,'Mushkin Enhanced Redline'),(18,4,1200,'5',5,'Corsair Vengeance Pro'),(19,4,600,'3',4,'Crucial Ballistix'),(20,4,200,'3',1,'G.Skill Sniper'),(21,5,200,'4',3,'WD Blue 1TB'),(22,5,150,'3',2,'Seagate Barracuda 500GB'),(23,5,220,'4',4,'WD Black 1TB'),(24,5,250,'2',4,'Seagate Desktop SSHD 2TB'),(25,5,300,'3',5,'WD Green 3TB (2011)');
/*!40000 ALTER TABLE `detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_type`
--

DROP TABLE IF EXISTS `detail_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `produce_time` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_type`
--

LOCK TABLES `detail_type` WRITE;
/*!40000 ALTER TABLE `detail_type` DISABLE KEYS */;
INSERT INTO `detail_type` VALUES (1,'CPU',4),(2,'GPU',3),(3,'MOTHERBOARD',4),(4,'RAM',2),(5,'HDD',1);
/*!40000 ALTER TABLE `detail_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deadilne` datetime NOT NULL,
  `price` int(11) NOT NULL,
  `user_iduser` int(11) NOT NULL,
  `computer_id` int(11) NOT NULL,
  `status_idstatus` int(11) NOT NULL,
  `count_computers` int(11) NOT NULL,
  `propouse` varchar(45) DEFAULT 'null',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_order_user1_idx` (`user_iduser`),
  KEY `fk_order_computer1_idx` (`computer_id`),
  KEY `fk_order_status1_idx` (`status_idstatus`),
  CONSTRAINT `fk_order_computer1` FOREIGN KEY (`computer_id`) REFERENCES `computer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_status1` FOREIGN KEY (`status_idstatus`) REFERENCES `status` (`idstatus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'2015-05-20 14:00:00',65000,1,1,1,5,'null'),(2,'2015-05-17 14:00:00',24700,2,2,2,6,'null'),(3,'2015-05-15 14:00:00',58000,1,3,2,4,'null'),(4,'2015-05-11 14:00:00',51400,2,4,2,7,'null'),(5,'2015-05-08 14:00:00',30000,1,5,3,3,'null');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`ROLE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (2,'ROLE_MANAGER'),(3,'ROLE_PLANNER'),(1,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialization`
--

DROP TABLE IF EXISTS `specialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialization` (
  `detail_type_id` int(11) NOT NULL,
  `worker_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`,`detail_type_id`,`worker_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_detail_type_has_worker_worker1_idx` (`worker_id`),
  KEY `fk_detail_type_has_worker_detail_type1_idx` (`detail_type_id`),
  CONSTRAINT `fk_detail_type_has_worker_detail_type1` FOREIGN KEY (`detail_type_id`) REFERENCES `detail_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_detail_type_has_worker_worker1` FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialization`
--

LOCK TABLES `specialization` WRITE;
/*!40000 ALTER TABLE `specialization` DISABLE KEYS */;
INSERT INTO `specialization` VALUES (1,1,1),(2,1,2),(3,1,3),(3,2,4),(4,2,5),(5,2,6),(2,2,7),(4,3,8),(2,3,9),(1,4,10),(3,4,11),(5,4,12);
/*!40000 ALTER TABLE `specialization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `idstatus` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'NEW_ORDER'),(2,'IN_QUEUE'),(3,'IN_PROSESS'),(4,'DONE'),(5,'SYSTEM_CANCEL'),(6,'USER_CANCEL');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `key_activate` varchar(100) DEFAULT NULL,
  `role_user_role_id` int(11) NOT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `iduser_UNIQUE` (`iduser`),
  KEY `fk_user_role1_idx` (`role_user_role_id`),
  CONSTRAINT `fk_user_role1` FOREIGN KEY (`role_user_role_id`) REFERENCES `role` (`user_role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1111',1,'user1','userov2','bbb@mail.com',NULL,1),(2,'2222',1,'user2','userov2','aaa@mail.com',NULL,1),(3,'3333',1,'manager','managerich','ccc@mail.com',NULL,2),(4,'4444',1,'planner','planich','ddd@mail.com',NULL,3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `sename` varchar(45) NOT NULL,
  `cash` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES (1,'worker1','worker1',1200),(2,'worker2','worker2',1000),(3,'worker3','worker3',700),(4,'worker4','worker4',1500);
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-18 23:07:55
