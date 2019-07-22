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
-- Table structure for table `Reservation`
--

DROP TABLE IF EXISTS `Reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Reservation` (
  `Reservation_id` int(11) NOT NULL AUTO_INCREMENT,
  `Start` datetime NOT NULL,
  `Ends` datetime NOT NULL,
  `ParkingLocation` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `User_email` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`Reservation_id`),
  KEY `ParkingLocation` (`ParkingLocation`),
  KEY `User_email` (`User_email`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`ParkingLocation`) REFERENCES `parkinglot` (`Location`),
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`User_email`) REFERENCES `user` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
INSERT INTO `Reservation` VALUES (112,'2019-06-20 10:23:02','2019-06-20 11:23:02','Bulevardul 21 Decembrie 1989','katona_norby@yahoo.com'),(113,'2019-06-20 06:38:04','2019-06-20 08:38:04','Bulevardul 21 Decembrie 1989','admin@yahoo.com'),(114,'2019-06-20 07:39:00','2019-06-20 09:39:00','Bulevardul 21 Decembrie 1989','admin@yahoo.com'),(115,'2019-06-20 06:40:19','2019-06-20 10:40:19','Bulevardul 21 Decembrie 1989','admin@yahoo.com'),(116,'2019-06-21 07:41:00','2019-06-21 10:41:00','Bulevardul 21 Decembrie 1989','admin@yahoo.com'),(117,'2019-06-21 07:45:09','2019-06-21 08:45:09','Bulevardul 21 Decembrie 1989','admin@yahoo.com'),(118,'2019-06-22 09:45:57','2019-06-22 11:45:57','Bulevardul 21 Decembrie 1989','admin@yahoo.com');
/*!40000 ALTER TABLE `Reservation` ENABLE KEYS */;
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
