-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: parking_management_system
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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` enum('admin','manager') NOT NULL,
  `birthday` date DEFAULT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `parking_id` int(11) DEFAULT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user` (`user`),
  KEY `parking_id` (`parking_id`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`parking_id`) REFERENCES `parking_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'kia','nopass','Lê Văn A','manager','1990-12-01','01244500993',1,'2017-11-17 09:18:21'),(2,'long','thanglong','Nguyễn Văn Long','manager','1991-10-01','0946435633',2,'2017-11-17 09:19:48'),(3,'daitu','daitua','Lê Văn Đại','manager','1995-09-08','0945123543',3,'2017-11-17 09:20:31'),(4,'admin','123','Hoàng Văn Hùng','admin','1997-06-01','01244566778',NULL,'2017-11-17 09:21:14'),(5,'kia2','nopass','Trình Đình Minh','manager','1990-06-06','01546733991',1,'2017-11-17 09:18:21');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fares`
--

DROP TABLE IF EXISTS `fares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fares` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parking_id` int(11) NOT NULL,
  `fares_values` int(11) NOT NULL,
  `typeofvehicle` int(11) NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `typeofvehicle` (`typeofvehicle`),
  KEY `parking_id` (`parking_id`),
  CONSTRAINT `fares_ibfk_1` FOREIGN KEY (`typeofvehicle`) REFERENCES `typeofvehicle` (`id`),
  CONSTRAINT `fares_ibfk_2` FOREIGN KEY (`parking_id`) REFERENCES `parking_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fares`
--

LOCK TABLES `fares` WRITE;
/*!40000 ALTER TABLE `fares` DISABLE KEYS */;
INSERT INTO `fares` VALUES (1,1,12,3,'2017-12-05 16:41:31'),(2,1,12,5,'2017-12-05 16:41:36'),(3,1,120,6,'2017-12-05 16:41:42'),(4,1,50000,6,'2017-12-05 16:42:01'),(5,2,56,1,'2017-12-06 03:13:21'),(6,2,10000,4,'2017-12-06 03:13:29'),(7,2,15000,2,'2017-12-06 03:13:37'),(8,2,25000,1,'2017-12-06 03:13:42'),(9,2,20000,2,'2017-12-06 03:20:18'),(10,1,20000,2,'2017-12-07 02:31:04'),(11,1,5000,4,'2017-12-07 02:31:11'),(12,1,45,1,'2017-12-07 02:32:03'),(13,2,40000,3,'2017-12-07 05:32:26'),(14,3,40000,3,'2017-12-07 05:32:30');
/*!40000 ALTER TABLE `fares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_info`
--

DROP TABLE IF EXISTS `parking_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `capacity` int(11) DEFAULT '0',
  `over_night` int(11) DEFAULT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_info`
--

LOCK TABLES `parking_info` WRITE;
/*!40000 ALTER TABLE `parking_info` DISABLE KEYS */;
INSERT INTO `parking_info` VALUES (1,'Bãi đỗ xe KIA Morning','281 Hai Bà Trưng, Hà Nội',2000,50000,'2017-11-17 09:15:11'),(2,'Bãi đỗ xe Nam Thăng Long','lô 28D, khu đỗ thị Nam Thăng Long, Hà Nội',1000,NULL,'2017-11-17 09:16:34'),(3,'Bãi đỗ xe Khu Nhà Đại Từ A','112 A, Trương Định, Hà Nội',500,20000,'2017-11-17 09:17:42');
/*!40000 ALTER TABLE `parking_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_vehicle`
--

DROP TABLE IF EXISTS `parking_vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking_vehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parking_id` int(11) NOT NULL,
  `typeofvehicle` int(11) NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `parking_id` (`parking_id`),
  KEY `typeofvehicle` (`typeofvehicle`),
  CONSTRAINT `parking_vehicle_ibfk_1` FOREIGN KEY (`parking_id`) REFERENCES `parking_info` (`id`),
  CONSTRAINT `parking_vehicle_ibfk_2` FOREIGN KEY (`typeofvehicle`) REFERENCES `typeofvehicle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_vehicle`
--

LOCK TABLES `parking_vehicle` WRITE;
/*!40000 ALTER TABLE `parking_vehicle` DISABLE KEYS */;
INSERT INTO `parking_vehicle` VALUES (10,3,2,'2017-12-05 14:19:14'),(11,3,3,'2017-12-05 14:19:14'),(28,2,3,'2017-12-06 04:46:48'),(29,2,5,'2017-12-06 04:46:48'),(30,2,2,'2017-12-06 04:46:48'),(31,2,1,'2017-12-06 04:46:48'),(32,2,4,'2017-12-06 04:46:48'),(37,1,5,'2017-12-07 02:31:55'),(38,1,2,'2017-12-07 02:31:55'),(39,1,1,'2017-12-07 02:31:55'),(40,1,6,'2017-12-07 02:31:56'),(41,1,4,'2017-12-07 02:31:56');
/*!40000 ALTER TABLE `parking_vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typeofvehicle`
--

DROP TABLE IF EXISTS `typeofvehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `typeofvehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typeofvehicle`
--

LOCK TABLES `typeofvehicle` WRITE;
/*!40000 ALTER TABLE `typeofvehicle` DISABLE KEYS */;
INSERT INTO `typeofvehicle` VALUES (1,'Xe Máy','2017-11-17 09:23:20'),(2,'Xe Đạp','2017-11-17 09:23:20'),(3,'Xe Đạp Điện','2017-11-17 09:23:20'),(4,'Oto','2017-11-17 09:23:20'),(5,'Xe Gắn Máy','2017-11-17 09:23:20'),(6,'Moto','2017-11-17 09:23:20');
/*!40000 ALTER TABLE `typeofvehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_in_out`
--

DROP TABLE IF EXISTS `vehicle_in_out`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle_in_out` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parking_id` int(11) NOT NULL,
  `typeofvehicle` int(11) NOT NULL,
  `time_in` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `time_out` timestamp NULL DEFAULT NULL,
  `bks` varchar(45) DEFAULT NULL,
  `status` enum('Đang trong bãi','Đã ra') NOT NULL DEFAULT 'Đang trong bãi',
  `thanh_tien` int(11) DEFAULT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `parking_id` (`parking_id`),
  KEY `typeofvehicle` (`typeofvehicle`),
  CONSTRAINT `vehicle_in_out_ibfk_1` FOREIGN KEY (`parking_id`) REFERENCES `parking_info` (`id`),
  CONSTRAINT `vehicle_in_out_ibfk_2` FOREIGN KEY (`typeofvehicle`) REFERENCES `typeofvehicle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_in_out`
--

LOCK TABLES `vehicle_in_out` WRITE;
/*!40000 ALTER TABLE `vehicle_in_out` DISABLE KEYS */;
INSERT INTO `vehicle_in_out` VALUES (1,1,1,'2017-12-03 17:00:00','2017-12-07 02:31:34','45C -123','Đã ra',NULL,'2017-12-07 02:31:34'),(2,2,2,'2017-12-05 16:27:55',NULL,'43434','Đang trong bãi',NULL,'2017-12-05 16:27:55'),(3,2,1,'2017-12-05 16:28:02',NULL,'gfhgf','Đang trong bãi',NULL,'2017-12-05 16:28:02'),(4,1,3,'2017-12-05 16:31:04','2017-12-05 16:32:01','gfgf','Đã ra',NULL,'2017-12-05 16:32:01'),(5,2,2,'2017-12-06 03:13:52','2017-12-06 03:13:59','45C','Đã ra',NULL,'2017-12-06 03:13:59'),(6,2,2,'2017-12-06 03:17:18','2017-12-06 03:17:23','gf','Đã ra',NULL,'2017-12-06 03:17:23'),(7,2,2,'2017-12-06 03:18:34','2017-12-06 03:18:41','gfg','Đã ra',NULL,'2017-12-06 03:18:41'),(8,2,2,'2017-11-06 03:19:34','2017-11-06 03:19:38','f','Đã ra',15000,'2017-12-07 02:43:56'),(9,2,2,'2017-12-06 03:20:25','2017-12-06 03:20:29','fdfd','Đã ra',20000,'2017-12-06 03:20:29'),(10,1,5,'2017-10-07 02:31:19','2017-10-07 02:31:31','fdfgg','Đã ra',12,'2017-12-07 02:43:56'),(11,1,2,'2017-12-07 02:31:23','2017-12-07 02:31:26','fdfgg','Đã ra',20000,'2017-12-07 02:31:27'),(12,1,1,'2017-12-07 02:32:10','2017-12-07 02:32:23','fdgf','Đã ra',45,'2017-12-07 02:32:23'),(13,1,2,'2017-12-07 02:42:55','2017-12-07 02:42:59','fdfd','Đã ra',20000,'2017-12-07 02:43:00'),(14,1,1,'2017-12-07 02:43:05','2017-12-07 02:43:10','fdfd','Đã ra',45,'2017-12-07 02:43:10'),(15,1,4,'2017-12-07 02:43:16','2017-12-07 02:43:18','fdggjgh','Đã ra',5000,'2017-12-07 02:43:19'),(16,3,3,'2017-12-07 05:28:42',NULL,'fdfd','Đang trong bãi',NULL,'2017-12-07 05:28:42');
/*!40000 ALTER TABLE `vehicle_in_out` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-08 22:18:58
