-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: ecommercedb
-- ------------------------------------------------------
-- Server version	5.7.17

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
-- Table structure for table `Cart`
--

DROP TABLE IF EXISTS `Cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cart` (
  `_id` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `totalAmount` int(11) NOT NULL,
  `totalMoney` int(11) NOT NULL,
  `createdTime` date NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cart`
--

LOCK TABLES `Cart` WRITE;
/*!40000 ALTER TABLE `Cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Category`
--

DROP TABLE IF EXISTS `Category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Category` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(45) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(45) NOT NULL DEFAULT 'PK',
  PRIMARY KEY (`_id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Category`
--

LOCK TABLES `Category` WRITE;
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
INSERT INTO `Category` VALUES (2,'Chuot','Chuột','Phụ kiện'),(11,'Banphim','Bàn phím','PK'),(12,'Tainghe','Tai nghe','PK'),(13,'Danmanhinh','Miếng dán màn hình','PK');
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Log`
--

DROP TABLE IF EXISTS `Log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Log` (
  `_id` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `updatedTime` date NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Log`
--

LOCK TABLES `Log` WRITE;
/*!40000 ALTER TABLE `Log` DISABLE KEYS */;
/*!40000 ALTER TABLE `Log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Order_products`
--

DROP TABLE IF EXISTS `Order_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Order_products` (
  `_id` int(11) NOT NULL,
  `order_id` varchar(45) NOT NULL,
  `product_id` varchar(45) NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `product_price` varchar(45) NOT NULL,
  `product_quantity` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_id_UNIQUE` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Order_products`
--

LOCK TABLES `Order_products` WRITE;
/*!40000 ALTER TABLE `Order_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `Order_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `_id` int(11) NOT NULL,
  `created_at` varchar(45) NOT NULL,
  `id_order_products` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `id_user` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'PROGRESSING',
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_id_UNIQUE` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(2555) NOT NULL,
  `price` int(11) NOT NULL,
  `image` varchar(400) DEFAULT NULL,
  `available_start` varchar(25) CHARACTER SET latin1 DEFAULT NULL,
  `available_end` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `seller` varchar(45) NOT NULL DEFAULT 'mycoolshop',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=963615726 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (1,'Chuột không dây','Đây là loại chuột không giây nhạy nhất hành tinh.',50000,'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/placeholder.jpg?alt=media&token=83de6aab-f964-405c-9322-c8edd4ef3d7c',NULL,NULL,'mycoolshop','2016-12-01 14:38:46'),(2,'Chuột có dây','Đây là loại cuột có dây nhanh nhất hành tinh và có khả năng biến hình thành chuột ngoài không gian.',100000,'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/placeholder.jpg?alt=media&token=83de6aab-f964-405c-9322-c8edd4ef3d7c',NULL,NULL,'mycoolshop','2017-11-01 14:38:46'),(963615725,'Razer BlackWidow X','The Razer BlackWidow X gives you the same stellar performance as the Razer BlackWidow, deconstructed into an impressive piece of true craftsmanship. The exposed military grade metal construction is sure to turn heads while giving you durability that will withstand the test of time.',2999,'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2F4mp6r1wucg3?alt=media&token=aba3f643-a2b3-4d12-8d9c-7ea4911017c1',NULL,NULL,'mycoolshop','2017-12-08 14:16:13');
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product_Extra`
--

DROP TABLE IF EXISTS `Product_Extra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product_Extra` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `category_id` varchar(45) NOT NULL,
  `subcategory_id` varchar(45) NOT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `id_UNIQUE` (`_id`),
  KEY `product_id_idx` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_Extra`
--

LOCK TABLES `Product_Extra` WRITE;
/*!40000 ALTER TABLE `Product_Extra` DISABLE KEYS */;
INSERT INTO `Product_Extra` VALUES (3,1,'Chuot','C1'),(4,2,'Chuot','C2'),(5,2,'Chuot','C3'),(6,963615725,'Banphim','GB');
/*!40000 ALTER TABLE `Product_Extra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SubCategory`
--

DROP TABLE IF EXISTS `SubCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SubCategory` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(45) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `id_subcategory_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SubCategory`
--

LOCK TABLES `SubCategory` WRITE;
/*!40000 ALTER TABLE `SubCategory` DISABLE KEYS */;
INSERT INTO `SubCategory` VALUES (3,'C1','Chuột không dây',NULL),(4,'C2','Chuột có dây',NULL),(5,'C3','Chuột biến hình',NULL),(6,'T1','Tai nghe có dây',NULL),(7,'T2','Tai nghe không dây',NULL),(8,'T3','Tai nghe Bluetooth',NULL),(9,'GM','Gaming Mouse',NULL),(10,'GB','Gaming Keyboard',NULL);
/*!40000 ALTER TABLE `SubCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` text NOT NULL,
  `userType` varchar(10) DEFAULT 'user',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'xomix1802@gmail.com','admin','admin','Admin'),(2,'nhan@nhan.com','nhanquach','user','user'),(3,'nhan@gmail.com','nhan@gmail.com','1234','user');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ecommercedb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-10 11:59:29
