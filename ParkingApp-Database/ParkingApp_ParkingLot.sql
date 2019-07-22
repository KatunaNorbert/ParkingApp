-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: ParkingApp
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ParkingLot`
--

DROP TABLE IF EXISTS `ParkingLot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ParkingLot` (
  `Name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `Town` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `Latitude` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `Longitude` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `Spot_number` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `Location` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `Price` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`Location`),
  UNIQUE KEY `Location_UNIQUE` (`Location`),
  KEY `Location` (`Location`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ParkingLot`
--

LOCK TABLES `ParkingLot` WRITE;
/*!40000 ALTER TABLE `ParkingLot` DISABLE KEYS */;
INSERT INTO `ParkingLot` VALUES ('Nymphaea','Oradea','47.054006','21.951648','160','Aleea Ștrandului 13 B','6'),('Office','Cluj-Napoca','46.772428','23.603037','200','Bulevardul 21 Decembrie 1989','6'),('Prima','Oradea','47.058371','21.917166','50','Bulevardul Decebal 61A','3'),('Mall','Bucuresti','44.421524','26.127923','90','Calea Vitan 55-59','4'),('Unirii','Cluj-Napoca','46.770407','23.589338','93','Piața Unirii','5'),('Iulius','Cluj-Napoca','46.774805','24.232343','150','Str. Al. Vaida-Voievod, nr. 53-55','6'),('Municipal','Oradea','47.075800','21.918200','110','Strada Corneliu Coposu 12','4'),('Lotus','Oradea','47.035347','21.949083','120','Strada Nufărului 30','4'),('Scoalei','Bucuresti','46.189748','21.300863','170','Strada Scoalei','4'),('Fabricii','Cluj-Napoca','46.780440','23.614727','250','Strada Simion Musat','3'),('Universitate','Bucuresti','44.434020','26.101530','210','Strada Toma Caragiu 1','5');
/*!40000 ALTER TABLE `ParkingLot` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-21  9:34:20
