CREATE DATABASE  IF NOT EXISTS `stat` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `stat`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: stat
-- ------------------------------------------------------
-- Server version	5.7.14-log

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
-- Table structure for table `feed`
--

DROP TABLE IF EXISTS `feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feed` (
  `idfeed` int(11) NOT NULL AUTO_INCREMENT,
  `avgSat` double DEFAULT NULL,
  `nbSat` double DEFAULT NULL,
  `nbAcc` double DEFAULT NULL,
  `nbunAcc` double DEFAULT NULL,
  `waitAvgTime` double DEFAULT NULL,
  `ordersTotalNb` int(11) DEFAULT NULL,
  `purchasesTotalNb` int(11) DEFAULT NULL,
  `turnoverProbe` double DEFAULT NULL,
  `refundProbe` double DEFAULT NULL,
  `idInstance` int(11) NOT NULL,
  PRIMARY KEY (`idfeed`),
  KEY `fk_Feed_Instance1_idx` (`idInstance`),
  CONSTRAINT `fk_Feed_Instance1` FOREIGN KEY (`idInstance`) REFERENCES `instance` (`idInstance`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=274 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed`
--
ALTER TABLE stat.feed AUTO_INCREMENT = 1;


LOCK TABLES `feed` WRITE;
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
INSERT INTO `feed` VALUES (1,0,0,0,0,0,0,0,0,0,1),(2,0,0,0,0,0,0,0,0,0,1),(3,0,0,0,0,0,0,0,0,0,1),(4,0,0,0,0,0,0,0,0,0,1),(5,0,0,0,0,0,0,0,0,0,1),(6,0,0,0,0,0,0,0,0,0,1),(7,0,0,0,0,0,0,0,0,0,1),(8,0.5,0,0,0,0,0,0,0,0,1),(9,0.5,0,0,0,0,0,0,0,0,1),(10,0.5,0,0,0,0,0,0,0,0,1),(11,0.5,0,0,1,0,0,0,0,0,1),(12,1,0,0,1,0,0,0,0,0,1),(13,1.5,0,0,1,0,0,0,0,0,1),(14,1.5,0,0,1,0,0,0,0,0,1),(15,2,0,0,1,0,0,0,0,0,1),(16,2.5,0,0,1,0,0,0,0,0,1),(17,3.5,0,0,1,0,0,0,0,0,1),(18,4,0,0,2,0,0,0,0,0,1),(19,4,0,0,4,0,0,0,0,0,1),(20,4.5,0,0,7,0,0,0,0,0,1),(21,5.5,0,0,7,0,0,0,0,0,1),(22,6,0,0,7,0,0,0,0,0,1),(23,7,0,0,7,0,0,0,0,0,1),(24,8,0,0,8,0,0,0,0,0,1),(25,8,0,0,10,0,0,0,0,0,1),(26,9,0,0,14,0,0,0,0,0,1),(27,9.5,0,0,16,0,0,0,0,0,1),(28,10,1,0,17,0,1,0,0,0,1),(29,11,2,0,17,0,2,1,148,-296,1),(30,11.5,2,0,17,0,2,1,296,-1776,1),(31,12.5,2,0,17,0,2,2,296,-3256,1),(32,13.5,2,0,17,0,2,2,296,-4736,1),(33,15,3,0,17,0,3,2,296,-6216,1),(34,16,3,0,19,0,3,3,541,-7696,1),(35,16.5,3,0,21,0,3,3,541,-9176,1),(36,16.5,3,0,21,0,3,3,541,-10656,1),(37,16.5,3,4,22,0.3333333333333333,5,3,541,-12136,1),(38,17,3,4,23,0.3333333333333333,5,4,689,-13616,1),(39,17.5,3,4,23,0.3333333333333333,5,5,837,-15096,1),(40,18.5,3,4,23,0.3333333333333333,5,5,837,-16576,1),(41,19,3,4,23,0.3333333333333333,5,5,837,-18056,1),(42,19.5,3,4,25,0.3333333333333333,5,5,837,-19536,1),(43,20.5,3,5,27,0.4166666666666667,6,5,837,-21016,1),(44,21,3,5,27,0.4166666666666667,6,6,985,-22496,1),(45,21.5,3,5,27,0.4166666666666667,6,6,985,-23976,1),(46,22.5,3,5,28,0.4166666666666667,7,6,985,-25456,1),(47,22.5,3,5,28,0.4166666666666667,7,6,985,-26936,1),(48,23,3,5,28,0.4166666666666667,7,6,985,-28416,1),(49,23.5,3,5,30,0.4166666666666667,7,6,985,-29896,1),(50,23.5,3,5,31,0.4166666666666667,7,6,985,-31376,1),(51,24,3,5,33,0.4166666666666667,7,6,985,-32856,1),(52,0,0,0,0,0,0,0,0,0,2),(53,0,0,0,0,0,0,0,0,0,2),(54,0,0,0,0,0,0,0,0,0,2),(55,0,0,0,0,0,0,0,0,0,2),(56,0,0,0,0,0,0,0,0,0,2),(57,0,0,0,0,0,0,0,0,0,2),(58,0.5,0,0,0,0,0,0,0,0,2),(59,0.5,0,0,0,0,0,0,0,0,2),(60,0.5,0,0,0,0,0,0,0,0,2),(61,1,0,0,1,0,0,0,0,0,2),(62,1.5,0,0,1,0,0,0,0,0,2),(63,2,0,0,1,0,0,0,0,0,2),(64,2,0,0,1,0,0,0,0,0,2),(65,2.5,0,0,1,0,0,0,0,0,2),(66,3,0,0,2,0,0,0,0,0,2),(67,4,0,0,3,0,0,0,0,0,2),(68,4.5,0,0,3,0,0,0,0,0,2),(69,5.5,0,0,5,0,0,0,0,0,2),(70,6,0,0,6,0,0,0,0,0,2),(71,6.5,0,0,6,0,0,0,0,0,2),(72,6.5,0,0,6,0,0,0,0,0,2),(73,7,0,0,9,0,0,0,0,0,2),(74,7.5,0,0,10,0,0,0,0,0,2),(75,8.5,0,0,10,0,0,0,0,0,2),(76,9.5,0,0,14,0,0,0,0,0,2),(77,10.5,0,0,14,0,0,0,0,0,2),(78,11.5,0,0,17,0,0,0,0,0,2),(79,12.5,0,0,22,0,0,0,0,0,2),(80,13,0,0,22,0,0,0,0,0,2),(81,13.5,0,0,26,0,0,0,0,0,2),(82,15.5,0,0,26,0,1,0,0,0,2),(83,16.5,0,0,26,0,1,1,148,0,2),(84,17.5,0,0,28,0,1,1,148,-1036,2),(85,18,0,0,30,0,1,1,148,-2516,2),(86,19,0,0,30,0,1,1,148,-3996,2),(87,20,0,0,30,0,1,1,148,-5476,2),(88,21.5,1,0,30,0,2,1,148,-6956,2),(89,21.5,1,0,30,0,2,2,393,-8436,2),(90,21.5,1,0,32,0,2,2,393,-9916,2),(91,22,1,0,37,0,2,2,393,-11396,2),(92,22.5,1,0,38,0,2,2,393,-12876,2),(93,23,1,1,40,0.08333333333333333,3,2,393,-14356,2),(94,24,1,1,40,0.08333333333333333,3,3,541,-15836,2),(95,24.5,1,1,40,0.08333333333333333,3,3,541,-17316,2),(96,25,1,1,40,0.08333333333333333,3,3,541,-18796,2),(97,25.5,1,1,40,0.08333333333333333,3,3,541,-20276,2),(98,26,1,1,40,0.08333333333333333,3,3,541,-21756,2),(99,27,1,1,42,0.08333333333333333,3,3,541,-23236,2),(100,28,1,1,42,0.08333333333333333,3,3,541,-24716,2),(101,28.5,1,1,45,0.08333333333333333,3,3,541,-26196,2),(102,29.5,1,1,47,0.08333333333333333,3,3,541,-27676,2),(103,0,0,0,0,0,0,0,0,0,3),(104,0,0,0,0,0,0,0,0,0,3),(105,0,0,0,0,0,0,0,0,0,3),(106,0,0,0,0,0,0,0,0,0,3),(107,0,0,0,0,0,0,0,0,0,3),(108,0,0,0,0,0,0,0,0,0,3),(109,0,0,0,0,0,0,0,0,0,3),(110,0,0,0,0,0,0,0,0,0,3),(111,0,0,0,0,0,0,0,0,0,3),(112,1,0,0,0,0,0,0,0,0,3),(113,1.5,0,0,1,0,0,0,0,0,3),(114,1.5,0,0,1,0,0,0,0,0,3),(115,2,0,0,1,0,0,0,0,0,3),(116,2.5,0,0,1,0,0,0,0,0,3),(117,2.5,0,0,2,0,0,0,0,0,3),(118,3.5,0,0,3,0,0,0,0,0,3),(119,3.5,0,0,3,0,0,0,0,0,3),(120,4.5,1,0,3,0,0,0,0,0,3),(121,6,1,0,3,0,1,1,245,0,3),(122,6,1,0,5,0,1,1,245,-735,3),(123,6.5,1,0,8,0,1,1,245,-3185,3),(124,6.5,1,0,8,0,1,1,245,-5635,3),(125,7,1,0,8,0,1,1,245,-8085,3),(126,7.5,1,0,9,0,2,1,245,-10535,3),(127,8,1,0,9,0,2,2,393,-12985,3),(128,9,1,0,9,0,2,2,393,-15435,3),(129,9,1,0,11,0,2,2,393,-17885,3),(130,9,1,0,12,0,2,2,393,-20335,3),(131,9,1,0,13,0,2,2,393,-22785,3),(132,9,1,0,14,0,2,2,393,-25235,3),(133,10,1,0,14,0,2,2,393,-27685,3),(134,13.5,2,0,14,0,3,2,393,-30135,3),(135,14,2,0,14,0,3,3,541,-32585,3),(136,15,2,0,15,0,3,3,541,-35035,3),(137,0,0,0,0,0,0,0,0,0,4),(138,0,0,0,0,0,0,0,0,0,4),(139,0,0,0,0,0,0,0,0,0,4),(140,0,0,0,0,0,0,0,0,0,4),(141,0,0,0,0,0,0,0,0,0,4),(142,0,0,0,0,0,0,0,0,0,4),(143,0,0,0,0,0,0,0,0,0,4),(144,0.5,0,0,0,0,0,0,0,0,4),(145,0.5,0,0,0,0,0,0,0,0,4),(146,0.5,0,0,0,0,0,0,0,0,4),(147,0.5,0,0,1,0,0,0,0,0,4),(148,0.5,0,0,2,0,0,0,0,0,4),(149,2,0,0,2,0,0,0,0,0,4),(150,2.5,0,0,2,0,0,0,0,0,4),(151,3,0,0,3,0,0,0,0,0,4),(152,3.5,0,0,3,0,0,0,0,0,4),(153,4.5,0,0,3,0,0,0,0,0,4),(154,5.5,0,0,3,0,0,0,0,0,4),(155,5.5,0,0,6,0,0,0,0,0,4),(156,6,0,0,7,0,0,0,0,0,4),(157,6,0,0,7,0,0,0,0,0,4),(158,6.5,0,1,7,0.08333333333333333,1,0,0,0,4),(159,7,0,1,8,0.08333333333333333,1,1,148,0,4),(160,8.5,0,1,8,0.08333333333333333,1,1,148,-1036,4),(161,9.5,0,1,8,0.08333333333333333,1,1,148,-2516,4),(162,10.5,0,1,10,0.08333333333333333,1,1,148,-3996,4),(163,11.5,0,1,16,0.08333333333333333,2,1,148,-5476,4),(164,11.5,1,1,18,0.08333333333333333,2,2,296,-6956,4),(165,12,1,1,18,0.08333333333333333,3,3,444,-8436,4),(166,12,1,1,18,0.08333333333333333,3,3,444,-9916,4),(167,12.5,1,1,20,0.08333333333333333,3,3,444,-11396,4),(168,13,1,1,20,0.08333333333333333,3,3,444,-12876,4),(169,13.5,1,1,20,0.08333333333333333,3,3,444,-14356,4),(170,13.5,1,1,21,0.08333333333333333,3,3,444,-15836,4),(171,14,1,1,21,0.08333333333333333,3,3,444,-17316,4),(172,14,1,1,22,0.08333333333333333,3,3,444,-18796,4),(173,14,1,1,22,0.08333333333333333,3,3,444,-20276,4),(174,14,1,1,22,0.08333333333333333,3,3,444,-21756,4),(175,14.5,1,1,23,0.08333333333333333,3,3,444,-23236,4),(176,15.5,1,1,24,0.08333333333333333,3,3,444,-24716,4),(177,16.5,1,1,26,0.08333333333333333,3,3,444,-26196,4),(178,17.5,1,1,28,0.08333333333333333,3,3,444,-27676,4),(179,18,1,1,32,0.08333333333333333,3,3,444,-29156,4),(180,19,1,1,32,0.08333333333333333,3,3,444,-30636,4),(181,20,1,1,32,0.08333333333333333,3,3,444,-32116,4),(182,20.5,1,1,32,0.08333333333333333,3,3,444,-33596,4),(183,21,1,1,32,0.08333333333333333,3,3,444,-35076,4),(184,21,1,1,32,0.08333333333333333,3,3,444,-36556,4),(185,22,1,1,36,0.08333333333333333,4,3,444,-38036,4),(186,22,1,1,37,0.08333333333333333,4,4,689,-39516,4),(187,22.5,1,1,37,0.08333333333333333,4,4,689,-40996,4),(188,0,0,0,0,0,0,0,0,0,5),(189,0,0,0,0,0,0,0,0,0,5),(190,0,0,0,0,0,0,0,0,0,5),(191,0,0,0,0,0,0,0,0,0,5),(192,0,0,0,0,0,0,0,0,0,5),(193,0,0,0,0,0,0,0,0,0,5),(194,0,0,0,0,0,0,0,0,0,5),(195,0,0,0,0,0,0,0,0,0,5),(196,0,0,0,0,0,0,0,0,0,5),(197,0,0,0,1,0,0,0,0,0,5),(198,1.5,0,0,1,0,0,0,0,0,5),(199,1.5,0,0,1,0,0,0,0,0,5),(200,1.5,0,0,1,0,0,0,0,0,5),(201,2.5,0,0,1,0,0,0,0,0,5),(202,3,0,0,2,0,0,0,0,0,5),(203,3.5,0,0,3,0,0,0,0,0,5),(204,3.5,0,0,3,0,0,0,0,0,5),(205,4,1,0,3,0,1,0,0,0,5),(206,5,1,0,3,0,1,1,245,0,5),(207,5.5,1,0,3,0,1,1,245,-2450,5),(208,5.5,1,0,5,0,1,1,245,-4900,5),(209,5.5,1,1,5,0.08333333333333333,2,1,245,-7350,5),(210,6.5,1,1,5,0.08333333333333333,2,2,393,-9800,5),(211,7.5,1,1,5,0.08333333333333333,2,2,393,-12250,5),(212,8.5,1,1,5,0.08333333333333333,2,2,393,-14700,5),(213,8.5,1,2,7,0.16666666666666666,3,2,393,-17150,5),(214,9,1,2,10,0.16666666666666666,4,3,541,-19600,5),(215,9,1,2,12,0.16666666666666666,4,3,541,-22050,5),(216,9.5,1,2,12,0.16666666666666666,4,3,541,-24500,5),(217,9.5,1,2,13,0.16666666666666666,4,3,541,-26950,5),(218,10,2,2,13,0.16666666666666666,5,3,689,-29400,5),(219,11.5,2,2,13,0.16666666666666666,5,4,689,-31850,5),(220,12,2,2,13,0.16666666666666666,5,4,689,-34300,5),(221,13,2,2,13,0.16666666666666666,5,4,689,-36750,5),(222,13,2,2,13,0.16666666666666666,5,4,689,-39200,5),(223,13.5,2,2,17,0.16666666666666666,5,4,689,-41650,5),(224,14.5,2,2,17,0.16666666666666666,5,4,689,-44100,5),(225,15.5,2,2,19,0.16666666666666666,5,4,689,-46550,5),(226,16,2,2,22,0.16666666666666666,5,4,689,-49000,5),(227,17,2,2,22,0.16666666666666666,5,4,689,-51450,5),(228,18,2,2,23,0.16666666666666666,5,4,689,-53900,5),(229,19,2,2,27,0.16666666666666666,5,4,689,-56350,5),(230,20,2,2,27,0.16666666666666666,5,4,689,-58800,5),(231,21,2,2,29,0.16666666666666666,5,4,689,-61250,5),(232,21.5,2,2,29,0.16666666666666666,5,4,689,-63700,5),(233,22.5,2,2,31,0.16666666666666666,5,4,689,-66150,5),(234,23,2,2,31,0.16666666666666666,5,4,689,-68600,5),(235,24,2,2,31,0.16666666666666666,5,4,689,-71050,5),(236,25,2,2,35,0.16666666666666666,5,4,689,-73500,5),(237,26,2,2,35,0.16666666666666666,5,4,689,-75950,5),(238,26.5,2,2,38,0.16666666666666666,5,4,689,-78400,5),(239,0,0,0,0,0,0,0,0,0,6),(240,0,0,0,0,0,0,0,0,0,6),(241,0,0,0,0,0,0,0,0,0,6),(242,0,0,0,0,0,0,0,0,0,6),(243,0,0,0,0,0,0,0,0,0,6),(244,0,0,0,0,0,0,0,0,0,6),(245,0,0,0,0,0,0,0,0,0,6),(246,0,0,0,0,0,0,0,0,0,6),(247,0,0,0,0,0,0,0,0,0,6),(248,0,0,0,0,0,0,0,0,0,6),(249,0,1,0,0,0,1,0,0,0,6),(250,0,1,0,0,0,1,1,245,0,6),(251,1.5,1,0,0,0,1,1,245,-2450,6),(252,1.5,1,0,0,0,1,1,245,-4900,6),(253,2.5,1,0,0,0,1,1,245,-7350,6),(254,3,1,0,1,0,1,1,245,-9800,6),(255,4,1,0,1,0,1,1,245,-12250,6),(256,5,1,0,1,0,1,1,245,-14700,6),(257,5,1,0,1,0,1,1,245,-17150,6),(258,5,1,0,2,0,1,1,245,-19600,6),(259,5,1,0,3,0,1,1,245,-22050,6),(260,5,1,0,4,0,1,1,245,-24500,6),(261,5,1,0,5,0,1,1,245,-26950,6),(262,6.5,1,0,5,0,1,1,245,-29400,6),(263,7.5,1,0,5,0,1,1,245,-31850,6),(264,8.5,1,0,5,0,1,1,245,-34300,6),(265,9.5,2,0,9,0,2,1,245,-36750,6),(266,10.5,3,0,14,0,3,2,393,-39200,6),(267,11,3,0,14,0,3,2,393,-41650,6),(268,12,3,0,14,0,3,2,393,-44100,6),(269,12,3,0,14,0,3,2,393,-46550,6),(270,12,3,0,14,0,3,2,393,-49000,6),(271,13,3,0,16,0,3,2,393,-51450,6),(272,14,3,0,16,0,3,2,393,-53900,6),(273,0,0,0,0,0,0,0,0,0,7);
/*!40000 ALTER TABLE `feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instance`
--

DROP TABLE IF EXISTS `instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instance` (
  `idInstance` int(11) NOT NULL AUTO_INCREMENT,
  `ord` int(11) NOT NULL,
  `idSimulation` int(11) NOT NULL,
  PRIMARY KEY (`idInstance`),
  KEY `fk_Instance_Simulation_idx` (`idSimulation`),
  CONSTRAINT `fk_Instance_Simulation` FOREIGN KEY (`idSimulation`) REFERENCES `simulation` (`idSimulation`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instance`
--

ALTER TABLE stat.instance AUTO_INCREMENT = 1;

LOCK TABLES `instance` WRITE;
/*!40000 ALTER TABLE `instance` DISABLE KEYS */;
INSERT INTO `instance` VALUES (1,1,1),(2,2,1),(3,3,1),(4,1,2),(5,2,2),(6,3,2),(7,1,3),(8,2,3);
/*!40000 ALTER TABLE `instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `simulation`
--

DROP TABLE IF EXISTS `simulation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `simulation` (
  `idSimulation` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idSimulation`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `simulation`
--
ALTER TABLE stat.simulation AUTO_INCREMENT = 1;

LOCK TABLES `simulation` WRITE;
/*!40000 ALTER TABLE `simulation` DISABLE KEYS */;
INSERT INTO `simulation` VALUES (3,'kjfs'),(1,'sim1'),(2,'SIM44');
/*!40000 ALTER TABLE `simulation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-07 19:44:51
