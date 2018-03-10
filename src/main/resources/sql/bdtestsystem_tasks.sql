CREATE DATABASE  IF NOT EXISTS `bdtestsystem` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bdtestsystem`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bdtestsystem
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `Tid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `body` text,
  `start_time` date DEFAULT NULL,
  `end_time` varchar(30) DEFAULT NULL,
  `answer` text,
  `mark` int(11) DEFAULT '0',
  `review` varchar(200) DEFAULT NULL,
  `task_status` varchar(200) DEFAULT 'New',
  `Sid` int(11) DEFAULT NULL,
  PRIMARY KEY (`Tid`),
  KEY `tasks_subscription_Sid_fk` (`Sid`),
  CONSTRAINT `tasks_subscription_Sid_fk` FOREIGN KEY (`Sid`) REFERENCES `subscription` (`Sid`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (1,'Text Composite','Написать программу разбора текста на абзацы, предложения, слова.  В решении использовать патерн Composite.','2018-02-09','2018-02-17','',0,'','New',5),(23,'Task4','написать программу','2018-02-09','2018-02-16','',0,'','New',5),(31,'Парсер XML','Написать программу - парсер XML','2018-02-12','2018-02-15','done now2',2,'seen','Passed',22),(32,'134567890','12345667','2018-02-12','2018-02-16','123456789012345',0,'12345','New',22),(33,'task1','execute','2018-02-09','2018-02-16','answer',2,'cool-cool','Verified',22),(34,'task2','write task','2018-02-12','2018-02-18','MY ANSWER',6,'рецензия','Verified',22),(35,'task3','content-','2018-02-12','2018-02-17','',8,'privet','Verified',22),(36,'12345cool','123','2018-02-14','2018-02-14',NULL,0,NULL,'New',22);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-14  5:37:04
