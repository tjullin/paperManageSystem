-- MySQL dump 10.13  Distrib 5.6.22, for osx10.8 (x86_64)
--
-- Host: localhost    Database: llin
-- ------------------------------------------------------
-- Server version	5.6.25-enterprise-commercial-advanced

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `content` text CHARACTER SET utf8 NOT NULL,
  `curtime` datetime NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,6,1,'???','2016-01-13 03:46:14'),(2,6,1,'???','2016-01-13 03:46:20'),(3,6,1,'???','2016-01-13 03:46:29'),(4,6,1,'不错！','2016-01-13 03:54:50'),(5,6,1,'很好！','2016-01-13 03:55:01'),(6,6,1,'很好！','2016-01-13 04:02:16'),(7,5,5,'评论','2016-01-14 20:11:52'),(8,5,5,'评论测试','2016-01-14 20:11:56'),(9,7,5,'评论！','2016-01-14 20:51:22');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `download`
--

DROP TABLE IF EXISTS `download`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `download` (
  `did` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `fuid` int(11) NOT NULL,
  `curtime` datetime NOT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `download`
--

LOCK TABLES `download` WRITE;
/*!40000 ALTER TABLE `download` DISABLE KEYS */;
INSERT INTO `download` VALUES (1,6,1,1,'2016-01-13 04:02:56'),(2,6,1,1,'2016-01-13 04:05:37'),(3,6,1,1,'2016-01-13 04:07:33'),(4,6,1,1,'2016-01-13 22:41:32'),(5,6,3,1,'2016-01-13 22:43:23'),(6,6,3,1,'2016-01-13 22:45:40'),(7,3,3,1,'2016-01-13 22:45:52'),(8,5,5,1,'2016-01-14 20:11:30'),(9,6,5,1,'2016-01-14 20:35:53'),(10,6,5,1,'2016-01-14 20:35:56'),(11,6,5,1,'2016-01-14 20:36:02'),(12,6,5,1,'2016-01-14 20:36:41');
/*!40000 ALTER TABLE `download` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 NOT NULL,
  `keyword` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `description` text CHARACTER SET utf8,
  `score` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  `type` varchar(5) NOT NULL,
  `filename` varchar(50) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
INSERT INTO `paper` VALUES (3,'测试','测试','测试',5,1,'docx','1452625408043'),(4,'测试2','测试2','测试2',2,1,'docx','1452625660660'),(5,'测试3','测试3','哈哈哈',1,1,'pages','1452625721495'),(6,'分析','试卷；操作系统；分析','操作系统试卷分析',1,1,'pages','1452625808542'),(7,'邀请函','邀请函','测试文件2',20,5,'pdf','1452773446325');
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `type` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '10',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1','1',1,19),(4,'1','1',0,10),(5,'test','test',0,8),(6,'haha','haha',0,10),(7,'tt','tt',0,10);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-14 21:00:17
