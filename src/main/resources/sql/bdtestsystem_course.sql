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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `Cid` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(45) NOT NULL,
  `hours` smallint(6) NOT NULL,
  `description` text NOT NULL,
  `cost` int(11) DEFAULT NULL,
  `Uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`Cid`),
  KEY `course_users_fk` (`Uid`),
  CONSTRAINT `course_users_fk` FOREIGN KEY (`Uid`) REFERENCES `users` (`Uid`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='list of courses';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'JAVA WEB DEVELOPMENT',65,'Курс рассчитан на слушателей, имеющих навыки программирования и хорошее понимание принципов ООП.',150,28),(2,'TEST AUTOMATION (JAVA)',64,'   Курс расчитан для специалистов по автоматизированному тестированию с минимальным опытом работы с Java-технологиями и программистов, которые хотят улучшить знания языка Java с целью его применения в области Test Automation\r\n-научить слушателей основам языка Java\r\n-познакомить с подходами и практиками, которые применяются при ручном и автоматизированном тестировании программного обеспечения\r\n-дать знания и показать на практике принципы работы с XML, HTML/CSS\r\n-рассказать об основах баз данных и языке SQL   ',600,28),(3,'IOS DEVELOPMENT TRAINING',120,'Данный курс разработан на основе последней версии языка Swift 4. Изучение языка программирования Swift неразрывно связано с освоением обширной библиотеки iOS 11 SDK, поэтому курс построен на параллельной подаче материала по Swift и iOS.',1000,29),(4,'Web-дизайн',80,'  Программа по web-дизайну включает в себя web-разработку по созданию и проектированию сайтов, приложений и других сервисов. Главная цель web-дизайна - создание привлекательного и функционального сайта  ',400,29),(5,'TEST AUTOMATION (PYTHON)',70,' Курс расчитан:\r\n-для слушателей с техническим образованием без опыта в программировании\r\n-для слушателей с гуманитарным образованием, у которых есть познания в теории работы вычислительной техники и программного обеспечения. Технические знания – необязательный критерий, но их отсутствие потребует от студентов дополнительных усилий в процессе обучения\r\n-для специалистов, которые хотят развиваться в новом направлении и получить востребованную и перспективную профессию ',601,28),(6,'Front-EndHTML, CSS и JavaScript',100,'   Для чего этот курс\r\nЭтот курс для того, чтобы получить знания о языках программирования HTML, CSS и JavaScript, начиная от разбора синтаксиса и тегов и заканчивая созданием блоков, форм и целых страниц. Вы научитесь пользоваться инструментами верстки и самостоятельно писать код для браузеров. А в завершении подготовите выпускной проект — адаптивное веб-приложение.   ',901,29);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-14  5:36:59
