-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id_admin` int NOT NULL AUTO_INCREMENT,
  `first_name_admin` varchar(45) NOT NULL,
  `last_name_admin` varchar(45) NOT NULL,
  `email_admin` varchar(50) NOT NULL,
  `gender_admin` varchar(10) NOT NULL,
  `age_admin` int NOT NULL,
  `telephone_admin` varchar(15) NOT NULL,
  `password_admin` varchar(45) NOT NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE KEY `id_admin_UNIQUE` (`id_admin`),
  UNIQUE KEY `email_admin_UNIQUE` (`email_admin`),
  UNIQUE KEY `telephone_admin_UNIQUE` (`telephone_admin`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','admin','admin@admin.com','ชาย',20,'1234567890','admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id_cart` int NOT NULL AUTO_INCREMENT,
  `id_product` varchar(20) NOT NULL,
  `name_product` varchar(60) NOT NULL,
  `brand_product` varchar(45) NOT NULL,
  `type_size_product` varchar(45) NOT NULL,
  `price_product` int NOT NULL,
  PRIMARY KEY (`id_cart`),
  UNIQUE KEY `id_cart_UNIQUE` (`id_cart`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dsads`
--

DROP TABLE IF EXISTS `dsads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dsads` (
  `id` mediumint(4) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `telephone` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `total_price` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dsads`
--

LOCK TABLES `dsads` WRITE;
/*!40000 ALTER TABLE `dsads` DISABLE KEYS */;
/*!40000 ALTER TABLE `dsads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gaming_gear`
--

DROP TABLE IF EXISTS `gaming_gear`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gaming_gear` (
  `id_gaminggear` varchar(10) NOT NULL,
  `name_gaminggear` varchar(45) NOT NULL,
  `brand_gaminggear` varchar(45) NOT NULL,
  `type_gaminggear` varchar(45) NOT NULL,
  `price_gaminggear` int NOT NULL,
  UNIQUE KEY `id_gaminggear_UNIQUE` (`id_gaminggear`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gaming_gear`
--

LOCK TABLES `gaming_gear` WRITE;
/*!40000 ALTER TABLE `gaming_gear` DISABLE KEYS */;
INSERT INTO `gaming_gear` VALUES ('GG0001','G102 Prodigy','Logitech','เมาส์',690),('GG0002','G302 Daedalus Prime MOBA','Logitech','เมาส์',1290),('GG0003','G304 Lightspeed','Logitech','เมาส์',1150),('GG0004','G402 Hyperion Fury','Logitech','เมาส์',990),('GG0005','G502 Proteus Spectrum RGB','Logitech','เมาส์',2690),('GG0006','G603 Wireless LIGHTSPEED','Logitech','เมาส์',2090),('GG0007','G703 Wireless LIGHTSPEED','Logitech','เมาส์',2990),('GG0008','G903 Wireless LIGHTSPEED','Logitech','เมาส์',4190),('GG0009','G Pro Gaming','Logitech','เมาส์',1590),('GG0010','G Pro X Wireless LIGHTSPEED','Logitech','เมาส์',3790),('GG0011','G Pro X Superlight Wireless LIGHTSPEED','Logitech','เมาส์',4790);
/*!40000 ALTER TABLE `gaming_gear` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `last_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telephone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `total_price` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1000,'จิรเดช','นาคำ','1234567890','jiradet.n@kkumail.com',42900);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laptop`
--

DROP TABLE IF EXISTS `laptop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laptop` (
  `id_laptop` varchar(10) NOT NULL,
  `name_laptop` varchar(45) NOT NULL,
  `brand_laptop` varchar(45) NOT NULL,
  `size_laptop` varchar(10) NOT NULL,
  `price_laptop` int NOT NULL,
  UNIQUE KEY `id_laptop_UNIQUE` (`id_laptop`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laptop`
--

LOCK TABLES `laptop` WRITE;
/*!40000 ALTER TABLE `laptop` DISABLE KEYS */;
INSERT INTO `laptop` VALUES ('LT0001','TUF Gaming F17 FX706HCB-HX144W','Asus','17.3 นิ้ว',34990),('LT0002','TUF Gaming F17 FX706HC-HX011W','Asus','17.3 นิ้ว',34990),('LT0003','TUF Gaming F15 FX506HE-HN011W','Asus','15.6 นิ้ว',34990),('LT0004','TUF Gaming F15 FX506HC-HN111W','Asus','15.6 นิ้ว',32990),('LT0005','TUF Gaming F15 FX506HM-HN016W','Asus','15.6 นิ้ว',39990),('LT0006','TUF Gaming F15 FX507ZR-HF004W','Asus','15.6 นิ้ว',67900),('LT0007','TUF Gaming F15 FX507ZM-HN016W','Asus','15.6 นิ้ว',52900),('LT0008','TUF Gaming F15 FX507ZE-HN047W','Asus','15.6 นิ้ว',42900),('LT0009','ROG Zephyrus M16 GU603ZM-K8058W','Asus','16 นิ้ว',67900),('LT0010','ROG Strix G17 GL743IM-HX046W','Asus','17.3 นิ้ว',46990),('LT0011','ROG Strix G17 GL743IE-HX037W','Asus','17.3 นิ้ว',40900),('LT0012','ROG Strix G15 GL543IM-HN135W','Asus','15.6 นิ้ว',44990),('LT0013','ROG Strix G15 GL543IE-HN062W','Asus','15.6 นิ้ว',38990),('LT0014','ROG Strix G15 GL543RC-HN112W','Asus','15.6 นิ้ว',41990),('LT0015','ROG Strix Scar 15 G543ZX-HF058W','Asus','15.6 นิ้ว',114990),('LT0016','ROG Strix G543ZW-HF160W','Asus','15.6 นิ้ว',84990),('LT0017','ROG Zephyrus M16 GU603ZW-K8017WS','Asus','16 นิ้ว',92990),('LT0018','ROG Zephyrus S17 GX703HS-KF018T','Asus','17.3 นิ้ว',129990),('LT0019','MacBook Pro 16 Space Grey-32C GPU/32GB/1TB','Apple','16.2 นิ้ว',124900),('LT0020','MacBook Pro 16 Space Grey-16C GPU/16GB/1TB','Apple','16.2 นิ้ว',96900),('LT0021','MacBook Pro 16 Space Grey-16C GPU/16GB/512GB','Apple','16.2 นิ้ว',89900),('LT0022','MacBook Pro 14 Silver-16C GPU/16GB/1TB','Apple','14.2 นิ้ว',89900);
/*!40000 ALTER TABLE `laptop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id_member` int NOT NULL AUTO_INCREMENT,
  `first_name_member` varchar(45) NOT NULL,
  `last_name_member` varchar(45) NOT NULL,
  `email_member` varchar(50) NOT NULL,
  `gender_member` varchar(10) NOT NULL,
  `age_member` int NOT NULL,
  `telephone_number_member` varchar(15) NOT NULL,
  `password_member` varchar(45) NOT NULL,
  PRIMARY KEY (`id_member`),
  UNIQUE KEY `idmember_UNIQUE` (`id_member`),
  UNIQUE KEY `email_member_UNIQUE` (`email_member`),
  UNIQUE KEY `telephone_number_member_UNIQUE` (`telephone_number_member`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'จิรเดช','นาคำ','jiradet.n@kkumail.com','ชาย',20,'1234567890','1234');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smartphone`
--

DROP TABLE IF EXISTS `smartphone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `smartphone` (
  `id_phone` varchar(10) NOT NULL,
  `name_phone` varchar(45) NOT NULL,
  `brand_phone` varchar(45) NOT NULL,
  `size_phone` varchar(10) NOT NULL,
  `price_phone` int NOT NULL,
  UNIQUE KEY `id_phone_UNIQUE` (`id_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smartphone`
--

LOCK TABLES `smartphone` WRITE;
/*!40000 ALTER TABLE `smartphone` DISABLE KEYS */;
INSERT INTO `smartphone` VALUES ('SP0001','iPhone 13 Pro Max','Apple','6.7 นิ้ว',42900),('SP0002','iPhone 13 Pro','Apple','6.1 นิ้ว',38900),('SP0003','iPhone 13','Apple','6.1 นิ้ว',29900),('SP0004','iPhone 13 mini','Apple','5.4 นิ้ว',25900),('SP0005','iPhone SE (2022)','Apple','4.7 นิ้ว',15900),('SP0006','iPhone 13','Apple','6.1 นิ้ว',29900),('SP0007','iPad Pro 12.9 (2021)','Apple','12.9 นิ้ว',37900),('SP0008','iPad Pro 11 (2021)','Apple','11 นิ้ว',27900),('SP0009','iPad 10.2 (2021)','Apple','10.2 นิ้ว',11400),('SP0010','iPad mini (2021)','Apple','8.3 นิ้ว',17900),('SP0011','iPad Air(5th generation)','Apple','10.9 นิ้ว',20900),('SP0012','Galaxy A53 5G','Samsung','6.5 นิ้ว',14499),('SP0013','Galaxy M23','Samsung','6.4 นิ้ว',8999),('SP0014','Galaxy M33','Samsung','6.4 นิ้ว',10999),('SP0015','Galaxy Tab S8 Ultra','Samsung','14.6 นิ้ว',38900),('SP0016','Galaxy Tab S8 Plus','Samsung','12.4 นิ้ว',31900),('SP0017','Galaxy Tab S8','Samsung','11 นิ้ว',23900),('SP0018','Galaxy S22 Ultra','Samsung','6.8 นิ้ว',39900),('SP0019','Galaxy S22 Plus','Samsung','6.6 นิ้ว',34900),('SP0020','Galaxy S22','Samsung','6.1 นิ้ว',29900),('SP0021','Galaxy Tab A8 (2021)','Samsung','10.5 นิ้ว',9990),('SP0022','Galaxy S21 FE 5G','Samsung','6.4 นิ้ว',22900),('SP0023','P50 Pro','Huawei','6.6 นิ้ว',33990),('SP0024','P50 Pocket','Huawei','6.9 นิ้ว',46990),('SP0025','Nova 9','Huawei','6.57 นิ้ว',16990),('SP0026','Matepad T8 Kids Edition','Huawei','8 นิ้ว',6490),('SP0027','MatePad Pro 10.8','Huawei','10.8 นิ้ว',26990),('SP0028','MatePad 11','Huawei','10.95 นิ้ว',15900),('SP0029','Nova 8i','Huawei','6.67 นิ้ว',9900),('SP0030','MatePad Pro 12.6','Huawei','12.6 นิ้ว',28900),('SP0031','MatePad (2021)','Huawei','10.4 นิ้ว',9900),('SP0032','Mate 40 Pro','Huawei','6.76 นิ้ว',34990),('SP0033','Mate Xs','Huawei','8 นิ้ว',89900),('SP0034','P50','Huawei','6.5 นิ้ว',30900);
/*!40000 ALTER TABLE `smartphone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login`
--

DROP TABLE IF EXISTS `user_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_login` (
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telephone` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login`
--

LOCK TABLES `user_login` WRITE;
/*!40000 ALTER TABLE `user_login` DISABLE KEYS */;
INSERT INTO `user_login` VALUES ('จิรเดช','นาคำ','jiradet.n@kkumail.com','1234567890');
/*!40000 ALTER TABLE `user_login` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-25 22:47:22
