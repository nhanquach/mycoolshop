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
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` varchar(45) NOT NULL,
  `product_id` varchar(45) NOT NULL,
  `product_name` varchar(500) NOT NULL,
  `product_price` int(10) NOT NULL,
  `product_quantity` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_id_UNIQUE` (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Order_products`
--

LOCK TABLES `Order_products` WRITE;
/*!40000 ALTER TABLE `Order_products` DISABLE KEYS */;
INSERT INTO `Order_products` VALUES (1,'982713476','2','Chuột có dây',100000,1),(2,'982713476','963615725','Razer BlackWidow X',2999,1),(3,'982713476','1','Chuột không dây',50000,1),(4,'073149573','1','Chuột không dây',50000,1),(5,'073149573','2','Chuột có dây',100000,8),(6,'073149573','963615725','Razer BlackWidow X',2999,1),(7,'118493502','2','Chuột có dây',100000,8),(8,'118493502','963615725','Razer BlackWidow X',2999,1),(9,'118493502','1','Chuột không dây',50000,1),(10,'771429082','1','Chuột không dây',50000,1),(11,'771429082','963615725','Razer BlackWidow X',2999,1),(12,'771429082','2','Chuột có dây',100000,8),(13,'292982027','963615725','Razer BlackWidow X',2999,1),(14,'292982027','1','Chuột không dây',50000,1),(15,'292982027','2','Chuột có dây',100000,8),(16,'382061297','963615725','Razer BlackWidow X',2999,1),(17,'382061297','1','Chuột không dây',50000,1),(18,'382061297','2','Chuột có dây',100000,8),(19,'369708069','2','Chuột có dây',100000,8),(20,'369708069','963615725','Razer BlackWidow X',2999,1),(21,'369708069','1','Chuột không dây',50000,1),(22,'769411627','963615725','Razer BlackWidow X',2999,1),(23,'769411627','2','Chuột có dây',100000,1),(24,'837920050','1','Chuột không dây',50000,5),(25,'746493855','1','Chuột không dây',50000,5),(26,'650510525','963615725','Razer BlackWidow X',2999,1),(27,'949950648','2','Chuột có dây',100000,1),(28,'949950648','1','Chuột không dây',50000,2),(29,'949950648','963615725','Razer BlackWidow X',2999,1),(30,'523238967','2','Chuột có dây',100000,1),(31,'866171325','2','Chuột có dây',100000,1),(32,'878334140','963615725','Razer BlackWidow X',2999,23),(33,'362389100','1','Chuột không dây',50000,1),(34,'362389100','2','Chuột có dây',100000,1),(35,'455299119','2','Chuột có dây',100000,1),(36,'304487179','1','Logitech M510 Wireless Mouse',13,7),(37,'304487179','2','HP x500 Optical Wired USB Mouse',13,2),(38,'304487179','963615725','Razer BlackWidow X',299,4);
/*!40000 ALTER TABLE `Order_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `id_order_products` varchar(45) NOT NULL,
  `id_user` varchar(45) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(1000) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'PROGRESSING',
  `delivery_note` varchar(1000) DEFAULT 'None',
  `user_name` varchar(255) NOT NULL,
  `price` int(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `_id_UNIQUE` (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (4,'746493855','4','2017-12-13 22:40:50','Someplace in the Earth.','CANCELLED','none','Quách Trọng Nhân',250000,'0938217232','nhan@nhan.com'),(18,'282573951','895684988','2017-12-13 15:15:45','9123791379127aksjdhkajsdh','CANCELLED','This is a note.','Nhan',152999,'0938217232','nhan@nhan.com'),(19,'379719204','075917095','2017-12-13 15:26:45','12309','DONE','Note this.','Nhan',152999,'0938217232','nhan@nhan.com'),(20,'207034721','738257899','2017-12-13 15:28:12','nhan','DONE','nhan','Nhan',152999,'0938217232','nhan@nhan.com'),(21,'982713476','686445075','2017-12-13 15:29:34','nhan','PROGRESSING','nhan','Nhan',152999,'0938217232','nhan@nhan.com'),(22,'073149573','481001522','2017-12-13 15:30:05','nhan','PROGRESSING','nhan','Nhan',852999,'0938217232','nhan@nhan.com'),(23,'118493502','235562885','2017-12-13 15:40:11','askjdh','PROGRESSING','jsadh0192381','Nhan Qusch',852999,'0938217232',''),(24,'771429082','537540842','2017-12-13 15:44:55','Googlex','PROGRESSING','GOoasdasd','NhanQussch',852999,'1293','nhan@nhan.com'),(25,'292982027','408212922','2017-12-13 15:47:29','ajshd','DONE','Nothng','Nhan',852999,'123131498','219kda@gmail.com'),(26,'382061297','152208569','2017-12-13 15:48:45','jasdh','DONE','none','Nhan',852999,'12387','asajsd@askdjh.com'),(27,'650510525','4','2017-12-13 22:48:21','Someplace in the Earth.','CANCELLED','none','Quách Trọng Nhân',2999,'0938217232','nhan@nhan.com'),(28,'949950648','4','2017-12-14 22:38:31','Someplace in the Earth.','DONE','Take a very good care of this.','Quách Trọng Nhân',202999,'0938217232','nhan@nhan.com'),(29,'523238967','4','2017-12-14 22:40:00','Someplace in the Earth.','PROGRESSING','none','Quách Trọng Nhân',100000,'0938217232','nhan@nhan.com'),(30,'866171325','616155630','2017-12-14 23:15:11','Someplace in the Earth.','PROGRESSING','none','Quách Trọng Nhân',100000,'0938217232','nhan@nhan.com'),(31,'878334140','327880109','2017-12-15 21:17:06','480/58 Bình Quới, phường 28, Quận Bình Thạnh','DONE','Hi, be nice with my Stuffs!!!!','Nhân Trọng Quách',68977,'+84938217232','xomix1802@gmail.com'),(32,'362389100','4','2017-12-19 13:11:29','Someplace in the Earth.','PROGRESSING','none','Quách Trọng Nhân',150000,'0938217232','nhan@nhan.com'),(33,'455299119','4','2017-12-19 13:18:07','Someplace in the Earth.','PROGRESSING','none','Quách Trọng Nhân',100000,'0938217232','nhan@nhan.com'),(34,'304487179','239130298','2018-01-03 21:09:49','480/58 Bình Quới, phường 28, Quận Bình Thạnh','PROGRESSING','Please delivery with care!','Nhân Trọng Quách',1313,'+84938217232','xomix1802@gmail.com');
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
  `price` float NOT NULL,
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
INSERT INTO `Product` VALUES (1,'Logitech M510 Wireless Mouse','Logitech Wireless Mouse M510\nExtra comfort and control to get more done\nGet the mouse that gives you more control to get things done—and feels good doing it. With extra controls at your fingertips, M510 lets you browse the web, flip through photo albums and navigate documents faster and easier. Point-and-click precision, long battery life, and a hand-friendly shape combine to help you get more done, more comfortably every day.\n\n* Requires Logitech Options software available for download at www.logitech.com/downloads\n\n** Battery life may vary based on user and computing conditions.',13,'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Ftpx81a9j24i?alt=media&token=3bd504d1-cfdb-43bf-bc48-6639a611756e','2017-12-08 14:16:13','2018-12-08 14:16:13','mycoolshop','2016-12-01 14:38:46'),(2,'HP x500 Optical Wired USB Mouse','HP X500 Wired Mouse Stylish enhancement to a classic.\nEnjoy the comfortable HP Wired Mouse X500 with simple USB connection.\nThe optical tracking provides smooth tracking and programmable 3 button controls that can work with either hand.\nSupport through the subtle form: Elongated arch and contoured design provide relaxed control for either hand.\n1 step USB: By simply inserting the USB cord, you power your mouse and avoid ever replacing batteries.\nNo software or downloads. Just start scrolling and clicking away.\nFamiliar controls: Navigate Windows easily with 3 button control and scroll wheel. \nOptimal light: The optical tracking technology provides ideal tracking on most surfaces for accurate and smooth control.\nCompatibility Windows 8 Windows 7 Windows Vista Windows XP Mac OSX',13,'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fn7emi0gv6j?alt=media&token=4c54ec4b-ec43-4832-b64b-b1ca820a6d8e','2017-12-08 14:16:13','2018-12-08 14:16:13','mycoolshop','2017-11-01 14:38:46'),(57619580,'Obins Anne Pro Mechanical Keyboard Ergonomic RGB Backlight Full Key No Conflict','Condition:	\nNew: A brand-new, unused, unopened, undamaged item in its original packaging (where packaging is ... Read more\nKeyboard/Keypad:	Keyboard\nBrand:	\nObins\nMPN:	\nDoes Not Apply\nCountry/Region of Manufacture:	China',62,'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2F4tdfzfu32tx?alt=media&token=51e91bb0-9081-43ab-9de9-5fc610501416',NULL,NULL,'mycoolshop','2018-01-04 11:26:36'),(156410961,'Wireless Bluetooth Slim Keyboard + Mouse For Apple iMac For Macbook iPhone USA','Condition:	\nNew: A brand-new, unused, unopened, undamaged item in its original packaging (where packaging is ... Read more\nBrand:	\nUnbranded/Generic\nKeyboard size:	284x134x18mm	MPN:	\nDoes Not Apply\nInterface:	USB 2.0	UPC:	\nDoes not apply\nOperating Range:	10 meters',25,'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fpbc5gl5y39?alt=media&token=ae1acf28-30d4-4f0d-a1ab-2681f2dc7b04',NULL,NULL,'mycoolshop','2018-01-04 11:32:43'),(665589510,'OMEN by HP Wired USB Gaming Mouse 600 (Black/Red)','Brand new and authentic HP products ship from and are sold by Amazon.com (check above)\nDPI levels from 800–12,000: Fine-tune your accuracy with a full range of DPI levels and an optical sensor designed for eSports\nEasily adjustable settings: Buttons on the mouse let you adjust settings without interrupting your gameplay or opening additional software\nRemovable weights: Customize the center of gravity using three removable weights\nMechanical switches: Optimized so you get fast response times while withstanding the pressure of up to 50 million clicks',41,'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2F5kuufts9z8h?alt=media&token=10c02c09-0c29-47c8-bc3b-eea76fd504c2',NULL,NULL,'mycoolshop','2018-01-03 22:38:50'),(776001255,'Sony MDRZX100 ZX Series Stereo Headphones','Connectivity Technology: Wired\n30mm Multi-layer film diaphragms create a powerful sound\nHigh energy drivers deliver powerful bass & clear treble\nLightweight & adjust. ABS housing for rugged durability\nPressure-relieving earpads for long-wear comfort\nA rugged Y-type design makes for a tangle-free experience',15,'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fqbx05dqi0bn?alt=media&token=4e0cf8e5-7b66-4eb4-959a-4b27294cd555',NULL,NULL,'mycoolshop','2018-01-03 21:53:05'),(815783248,'Sony H.ear on Wireless Noise Cancelling Headphone, Viridian Blue (MDR100ABN/L)','Hear music at its best with Hi-Res Audio compatibility, Connect and stream music easily with Bluetooth, Focus on the music with Digital Noise Cancelling. Connectivity Technology: Wireless\nListen smarter with Noise Cancelling that adapts to your surroundings, Let the beats flow with Beat Response Control\nEnjoy high-quality, hands-free calls, Long-listen comfort\nCompatible devices - iPhone, iPad, or iPod touch (iOS 5.0 or later) supporting Hands-free Profile (HFP) iOS 7.X / 8.X / 9.X ; Smartphones - NFC-compatible smartphones installed with Android 4.1 or later. Refer the user manual under technical specification',395,'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fgvh8x67dppk?alt=media&token=b608c557-e315-4977-b4a4-eee62fe01314',NULL,NULL,'mycoolshop','2018-01-03 22:09:05'),(963615725,'Razer BlackWidow X','The Razer BlackWidow X gives you the same stellar performance as the Razer BlackWidow, deconstructed into an impressive piece of true craftsmanship. The exposed military grade metal construction is sure to turn heads while giving you durability that will withstand the test of time.',299,'https://firebasestorage.googleapis.com/v0/b/dazzling-torch-5860.appspot.com/o/product_images%2Fv2e5ecw952i?alt=media&token=6d75262f-2665-4046-8e93-fb86ae86440a','2017-12-08 14:16:13','2018-12-08 14:16:13','mycoolshop','2017-12-08 14:16:13');
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
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product_Extra`
--

LOCK TABLES `Product_Extra` WRITE;
/*!40000 ALTER TABLE `Product_Extra` DISABLE KEYS */;
INSERT INTO `Product_Extra` VALUES (3,1,'Chuot','C1'),(4,2,'Chuot','C2'),(5,2,'Chuot','C3'),(6,57619580,'Banphim','Banphimcoday'),(7,29962118,'Tainghe','T1'),(8,776001255,'Tainghe','T1'),(9,926270760,'Tainghe','T2'),(57,963615725,'Banphim','Banphimcoday'),(59,724453199,'Banphim','C1'),(60,724453199,'Chuot','C1'),(61,724453199,'Chuot','Banphimbluetooth'),(62,724453199,'Banphim','Banphimbluetooth'),(63,156410961,'Banphim','Banphimbluetooth'),(64,156410961,'Chuot','Banphimbluetooth'),(65,156410961,'Chuot','C1'),(66,156410961,'Banphim','C1'),(67,665589510,'Chuot','GB'),(68,665589510,'Chuot','C2'),(69,815783248,'Tainghe','T2');
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SubCategory`
--

LOCK TABLES `SubCategory` WRITE;
/*!40000 ALTER TABLE `SubCategory` DISABLE KEYS */;
INSERT INTO `SubCategory` VALUES (3,'C1','Chuột không dây',NULL),(4,'C2','Chuột có dây',NULL),(5,'C3','Chuột biến hình',NULL),(6,'T1','Tai nghe có dây',NULL),(7,'T2','Tai nghe không dây',NULL),(8,'T3','Tai nghe Bluetooth',NULL),(9,'GM','Gaming Mouse',NULL),(10,'GB','Gaming Keyboard',NULL),(11,'Banphimcoday','Bàn phím có dây',NULL),(12,'Banphimbluetooth','Bàn phím Bluetooth',NULL);
/*!40000 ALTER TABLE `SubCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Timebase`
--

DROP TABLE IF EXISTS `Timebase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Timebase` (
  `_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `show_1` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `show_2` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `days` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time_1` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time_2` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Timebase`
--

LOCK TABLES `Timebase` WRITE;
/*!40000 ALTER TABLE `Timebase` DISABLE KEYS */;
/*!40000 ALTER TABLE `Timebase` ENABLE KEYS */;
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
  `username` varchar(255) CHARACTER SET utf8 NOT NULL,
  `password` text NOT NULL,
  `userType` varchar(10) DEFAULT 'user',
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'xomix1802@gmail.com','admin','admin','Admin',NULL,NULL),(4,'nhan@nhan.com','Quách Trọng Nhân','user','user','0938217232','Someplace in the Earth.');
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

-- Dump completed on 2018-01-06 20:07:03
