-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: localhost    Database: CA3Database
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `CA3Database`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `CA3Database` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `CA3Database`;

--
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Customer` (
  `Customer_ID` int NOT NULL AUTO_INCREMENT,
  `Customer_Name` varchar(30) DEFAULT NULL,
  `Customer_Contact_Name` varchar(30) DEFAULT NULL,
  `Address_Line_1` varchar(50) DEFAULT NULL,
  `Address_Street` varchar(50) DEFAULT NULL,
  `Address_Town` varchar(50) DEFAULT NULL,
  `Address_County` varchar(50) DEFAULT NULL,
  `Address_Country` varchar(50) DEFAULT NULL,
  `Customer_Phonenumber` varchar(50) DEFAULT NULL,
  `Customer_Email` varchar(50) DEFAULT NULL,
  `Customer_VatNo` varchar(20) DEFAULT NULL,
  `Customer_Username` varchar(30) DEFAULT NULL,
  `Customer_Password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Customer_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'A1 Design Services','Jim Thomas','No 30','Stanley Street','Southend','Surrey','United Kingdom','004453637383838','info@a1design.co.uk','None','jthomas','pass'),(2,'Graham Electronics','Clint Peddie','Building 101','Atton Place','Mahon','Cork','Ireland','0563338282','accounts@gelectronics.ie','IE863837S','graham','pass'),(5,'Maher Industries Ltd','Bill Maher','Unit 56','Fawn Street','Aghee','Carlow','Ireland','0574984848','info@maherind.ie','IE7438939B','bmaher','pass'),(6,'M Kelleher Electrical','Matt Porter','12A Business Park','Planar Road','Naas','Kildare','Ireland','056 787 2233','kelectrical@gmail.com','None','mporter','pass'),(7,'Hausser GMBH','Alistair Leadbetter','10 Enfield St','Highstreet','Enfield','Meath','Ireland','054 726 2211','hausser@gmbh.ie','IE78672872','hauser','pass'),(8,'Brunson Ltd','Paul Murphy','Building 10','Bridge Street','Belturbet','Cavan','Ireland','0547282920','accounts@brunsonltd.ie','IE673839B','brunson','pass'),(9,'Compton Air','Sean Morris','Row 5','Crewmore Rd','Tandragee','Armagh','United Kingdom',' 00441219876','info@comptonair.co.uk','None','compton','pass');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Inventory`
--

DROP TABLE IF EXISTS `Inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Inventory` (
  `Product_ID` int NOT NULL AUTO_INCREMENT,
  `Product_Name` varchar(100) DEFAULT NULL,
  `Product_Description` varchar(255) DEFAULT NULL,
  `Qty_in_Stock` int DEFAULT NULL,
  `Reorder_Level` int DEFAULT NULL,
  `Max_Stock_Level` int DEFAULT NULL,
  `Cost_Price` decimal(20,2) DEFAULT NULL,
  `Retail_Price` decimal(20,2) DEFAULT NULL,
  `Qty_on_Order` int DEFAULT NULL,
  `Supplier_ID` int DEFAULT NULL,
  PRIMARY KEY (`Product_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Inventory`
--

LOCK TABLES `Inventory` WRITE;
/*!40000 ALTER TABLE `Inventory` DISABLE KEYS */;
INSERT INTO `Inventory` VALUES (1,'200x200 Double Deflection Vents','200mm square Double Deflection Vents in Stainless Steel',0,5,30,19.99,29.99,57,1),(3,'PozyDry Compact Pro','150W PIV Unit with Double Filters',2,3,6,1200.00,1500.00,0,3),(6,'Panel Heater 1000W','1000W Panel Heater',0,10,2,80.00,120.00,11,4),(7,'Panel Heater 2000W','2000W Panel Heater',0,1,10,120.00,150.00,7,4),(8,'Sentinal Advance S','MHVR Unit',1,1,5,1800.00,2511.79,0,6),(9,'AirCon Heat Save P','1000W Heat Saver Unit',11,5,20,350.00,450.00,0,3),(10,'N Flex Pipe 10M','NI Flex Pipe 10M Roll',16,10,50,40.00,90.00,0,2),(11,'NI Flex Pipe 30M','NI Flex Pipe 30M Roll',9,10,30,100.00,150.00,0,2);
/*!40000 ALTER TABLE `Inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Invoice_Order_Item`
--

DROP TABLE IF EXISTS `Invoice_Order_Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Invoice_Order_Item` (
  `Invoice_ID` int DEFAULT NULL,
  `Product_ID` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Current_Price` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Invoice_Order_Item`
--

LOCK TABLES `Invoice_Order_Item` WRITE;
/*!40000 ALTER TABLE `Invoice_Order_Item` DISABLE KEYS */;
INSERT INTO `Invoice_Order_Item` VALUES (1,3,2,1500.00),(2,6,5,120.00),(3,1,10,29.00),(3,6,8,120.00),(3,7,5,150.00),(4,8,1,2511.00),(4,10,2,90.00),(5,1,7,29.00),(5,3,1,1500.00);
/*!40000 ALTER TABLE `Invoice_Order_Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Order_Item`
--

DROP TABLE IF EXISTS `Order_Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Order_Item` (
  `Order_ID` int NOT NULL,
  `Product_ID` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  `Current_Price` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Order_Item`
--

LOCK TABLES `Order_Item` WRITE;
/*!40000 ALTER TABLE `Order_Item` DISABLE KEYS */;
INSERT INTO `Order_Item` VALUES (1,3,2,1500.00),(1,1,1,29.99),(2,3,1,1500.00),(3,1,10,29.99),(4,3,3,1500.00),(4,6,10,120.00),(3,6,8,120.00),(5,1,7,29.99),(5,3,1,1500.00),(3,7,5,150.00),(6,8,1,2511.79),(6,10,2,90.00),(7,1,10,29.99),(7,9,2,450.00),(7,7,1,150.00),(8,6,5,120.00),(8,3,2,1500.00),(8,1,9,29.99),(9,6,2,120.00),(9,10,10,90.00),(10,9,3,450.00),(10,11,10,150.00),(11,1,8,29.99),(11,7,2,150.00),(12,7,2,150.00),(12,9,3,450.00),(12,11,9,150.00);
/*!40000 ALTER TABLE `Order_Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SalesInvoice`
--

DROP TABLE IF EXISTS `SalesInvoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SalesInvoice` (
  `Invoice_ID` int NOT NULL AUTO_INCREMENT,
  `Order_ID` int DEFAULT NULL,
  `Invoice_Date` date DEFAULT NULL,
  `Shipping_Date` date DEFAULT NULL,
  `Delivery_Date` date DEFAULT NULL,
  `Payment_Date` date DEFAULT NULL,
  PRIMARY KEY (`Invoice_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SalesInvoice`
--

LOCK TABLES `SalesInvoice` WRITE;
/*!40000 ALTER TABLE `SalesInvoice` DISABLE KEYS */;
INSERT INTO `SalesInvoice` VALUES (1,1,'2022-02-20','2022-02-15','2022-02-15','2022-03-24'),(2,8,'2022-04-04','2022-04-03','2022-04-03',NULL),(3,3,'2022-04-06','2022-03-22','2022-03-22',NULL),(4,6,'2022-04-06','2022-04-04','2022-04-04',NULL),(5,5,'2022-04-06','2022-03-16','2022-03-16',NULL);
/*!40000 ALTER TABLE `SalesInvoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SalesOrder`
--

DROP TABLE IF EXISTS `SalesOrder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SalesOrder` (
  `Order_ID` int NOT NULL AUTO_INCREMENT,
  `Order_Date` date DEFAULT NULL,
  `Customer_ID` int DEFAULT NULL,
  `Complete` tinyint DEFAULT '0',
  PRIMARY KEY (`Order_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SalesOrder`
--

LOCK TABLES `SalesOrder` WRITE;
/*!40000 ALTER TABLE `SalesOrder` DISABLE KEYS */;
INSERT INTO `SalesOrder` VALUES (1,'2022-02-20',5,1),(2,'2022-02-20',1,0),(3,'2022-02-20',1,1),(4,'2022-02-28',5,0),(5,'2022-03-10',6,1),(6,'2022-04-01',2,1),(7,'2022-03-31',8,0),(8,'2022-03-24',9,1),(9,'2022-04-04',5,0),(10,'2022-04-04',9,0),(11,'2022-04-02',2,0),(12,'2022-04-03',5,0);
/*!40000 ALTER TABLE `SalesOrder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Staff` (
  `Staff_ID` int NOT NULL AUTO_INCREMENT,
  `Staff_Surname` varchar(30) DEFAULT NULL,
  `Staff_Firstname` varchar(30) DEFAULT NULL,
  `Address_Line_1` varchar(50) DEFAULT NULL,
  `Address_Street` varchar(50) DEFAULT NULL,
  `Address_Town` varchar(30) DEFAULT NULL,
  `Address_County` varchar(30) DEFAULT NULL,
  `Address_Country` varchar(30) DEFAULT NULL,
  `Staff_Phonenumber` varchar(20) DEFAULT NULL,
  `Staff_Email` varchar(20) DEFAULT NULL,
  `Staff_Job_Title` varchar(20) DEFAULT NULL,
  `Staff_Username` varchar(20) DEFAULT NULL,
  `Staff_Password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Staff_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
INSERT INTO `Staff` VALUES (1,'Hader','William','12 Woodview Heights','Riverside','Carlow','Carlow','Ireland','087 1112244','bhader@ventair.ie','General Manager','bhader','pass'),(2,'Account','Admin','Not defined','Not defined','Not defined','Not defined','Not defined','Not defined','Not defined','Not defined','','');
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Supplier`
--

DROP TABLE IF EXISTS `Supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Supplier` (
  `Supplier_ID` int NOT NULL AUTO_INCREMENT,
  `Supplier_Name` varchar(30) DEFAULT NULL,
  `Address_Line_1` varchar(50) DEFAULT NULL,
  `Address_Street` varchar(50) DEFAULT NULL,
  `Address_Town` varchar(50) DEFAULT NULL,
  `Address_County` varchar(50) DEFAULT NULL,
  `Address_Country` varchar(50) DEFAULT NULL,
  `Supplier_Phonenumber` varchar(20) DEFAULT NULL,
  `Supplier_Email` varchar(20) DEFAULT NULL,
  `Supplier_IBAN` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Supplier_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Supplier`
--

LOCK TABLES `Supplier` WRITE;
/*!40000 ALTER TABLE `Supplier` DISABLE KEYS */;
INSERT INTO `Supplier` VALUES (1,'EcoAir Ireland','10 Bridgeview Business Park','Moycullen','Naas','Kildare','Ireland','045 1567700','ecoaireland@sales.ie','IE29AIBK93115212345678'),(2,'Concept Supplies','94 Chester Rd','Wilton','Carrigaline','Cork','Ireland','059 1762938','sales@concept.ie','IE88BOFI9000017112233'),(3,'Mear Hardware','No 12','Athy Rd','Castlecomer','Kilkenny','Ireland','054 1893038','info@mearsware.ie','IE88BOFI9000017112233'),(4,'Quality Air','1204 Place','Townsend St','Birr','Offaly','Ireland','053 30123458','info@qacon.ie','IE88BOFI916718671681'),(5,'Wiseman Products','64','South Main St','Bandon','Cork','Ireland','054 37364383','accounts@wiseprod.ie','IE32BOFI126838787447'),(6,'Wiseman Products','64','South Main St','Bandon','Cork','Ireland','054 37364383','accounts@wiseprod.ie','IE32BOFI126838787447');
/*!40000 ALTER TABLE `Supplier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-06 20:42:50
