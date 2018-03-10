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
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription` (
  `Sid` int(11) NOT NULL AUTO_INCREMENT,
  `Uid` int(11) NOT NULL,
  `Cid` int(11) NOT NULL,
  `date_start` date DEFAULT NULL,
  `date_stop` date DEFAULT NULL,
  `avg_mark` double DEFAULT '0',
  `number_completed` varchar(20) DEFAULT '0/0',
  `status` varchar(20) NOT NULL DEFAULT 'active',
  PRIMARY KEY (`Sid`),
  KEY `subscription_course_Cid_fk` (`Cid`),
  KEY `subscription_users_id_user_fk` (`Uid`),
  CONSTRAINT `subscription_course_Cid_fk` FOREIGN KEY (`Cid`) REFERENCES `course` (`Cid`),
  CONSTRAINT `subscription_users_id_user_fk` FOREIGN KEY (`Uid`) REFERENCES `users` (`Uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
INSERT INTO `subscription` VALUES (2,30,1,'2007-02-20',NULL,2.34,'0/0','active'),(5,31,3,'2007-02-20',NULL,0,'0/2','active'),(22,30,4,'2008-02-20',NULL,3,'4/6','active'),(23,31,4,'2009-02-20',NULL,0,'0/0','active');
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-14  5:37:10
