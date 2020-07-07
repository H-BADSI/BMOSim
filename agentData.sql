CREATE DATABASE  IF NOT EXISTS `agentdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `agentdb`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: agentdb
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
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent` (
  `idAgent` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `agClass` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAgent`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

ALTER TABLE agentdb.agent AUTO_INCREMENT = 1;



LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
INSERT INTO `agent` VALUES (1,'COM','COM'),(2,'CUS','CUS'),(3,'SUP','SUP'),(4,'MAN','MAN'),(5,'ANA','ANA'),(60,'agent007','ANA');
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agentattribute`
--

DROP TABLE IF EXISTS `agentattribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agentattribute` (
  `idAgent` int(11) NOT NULL,
  `idAttribute` int(11) NOT NULL,
  PRIMARY KEY (`idAgent`,`idAttribute`),
  KEY `fk_Agent_has_Attribute_Attribute1_idx` (`idAttribute`),
  KEY `fk_Agent_has_Attribute_Agent_idx` (`idAgent`),
  CONSTRAINT `fk_Agent_has_Attribute_Agent` FOREIGN KEY (`idAgent`) REFERENCES `agent` (`idAgent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Agent_has_Attribute_Attribute1` FOREIGN KEY (`idAttribute`) REFERENCES `attribute` (`idAttribute`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agentattribute`
--


LOCK TABLES `agentattribute` WRITE;
/*!40000 ALTER TABLE `agentattribute` DISABLE KEYS */;
INSERT INTO `agentattribute` VALUES (2,1),(2,2),(2,3),(2,4),(2,5),(1,6),(2,6),(3,6),(1,7),(3,7),(4,7),(5,7),(1,8),(3,8),(1,9),(3,9),(1,10),(3,10),(1,11),(3,11),(3,12),(3,13),(3,19),(3,20),(3,21),(5,22),(60,37),(60,38);
/*!40000 ALTER TABLE `agentattribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attribute`
--

DROP TABLE IF EXISTS `attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attribute` (
  `idAttribute` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `vals` varchar(45) DEFAULT NULL,
  `idAttType` int(11) NOT NULL,
  PRIMARY KEY (`idAttribute`,`idAttType`),
  KEY `fk_Attribute_AttType1_idx` (`idAttType`),
  CONSTRAINT `fk_Attribute_AttType1` FOREIGN KEY (`idAttType`) REFERENCES `atttype` (`idAttType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute`
--
ALTER TABLE agentdb.attribute AUTO_INCREMENT = 1;

LOCK TABLES `attribute` WRITE;
/*!40000 ALTER TABLE `attribute` DISABLE KEYS */;
INSERT INTO `attribute` VALUES (1,'budgetAllowed',NULL,3),(2,'customerAge',NULL,3),(3,'qualityNeeded',NULL,4),(4,'timeToDelivery',NULL,3),(5,'objectTypes','\"food machine other\"',1),(6,'channelsUsed','\"net journal tv\"',1),(7,'ontologyUse',NULL,5),(8,'aSpeed',NULL,4),(9,'versatility',NULL,3),(10,'errorRate',NULL,4),(11,'roles','\"a b c\"',1),(12,'analysisTypes','\"a b c\"',1),(13,'requestTypes','\"a b c\"',1),(19,'aaa',NULL,1),(20,'bbb',NULL,2),(21,'ccc',NULL,3),(22,'speed',NULL,4),(23,'versality',NULL,4),(24,'nom',NULL,5),(25,'erzr',NULL,2),(27,'br1','val1 val2 val3',1),(28,'br2','',2),(29,'br3','',4),(37,'007',NULL,2),(38,'008',NULL,4);
/*!40000 ALTER TABLE `attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atttype`
--

DROP TABLE IF EXISTS `atttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `atttype` (
  `idAttType` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`idAttType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atttype`
--
ALTER TABLE agentdb.atttype AUTO_INCREMENT = 1;


LOCK TABLES `atttype` WRITE;
/*!40000 ALTER TABLE `atttype` DISABLE KEYS */;
INSERT INTO `atttype` VALUES (1,'table'),(2,'text'),(3,'number'),(4,'scale'),(5,'boolean');
/*!40000 ALTER TABLE `atttype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-07 19:44:20
