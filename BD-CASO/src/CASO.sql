-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: caso-cooperativa
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `cooperativa`
--

DROP TABLE IF EXISTS `cooperativa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cooperativa` (
  `CooCod` int NOT NULL AUTO_INCREMENT,
  `CooIden` int NOT NULL,
  `CooNom` varchar(50) NOT NULL,
  `CooSig` varchar(10) NOT NULL,
  `CooDir` varchar(30) NOT NULL,
  `CooTel` int NOT NULL,
  `CooCor` varchar(20) NOT NULL,
  `CooSlo` varchar(50) NOT NULL,
  `CooLog` varchar(10) NOT NULL,
  `CooUsu` int NOT NULL,
  PRIMARY KEY (`CooCod`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cooperativa`
--

LOCK TABLES `cooperativa` WRITE;
/*!40000 ALTER TABLE `cooperativa` DISABLE KEYS */;
INSERT INTO `cooperativa` VALUES (2,2,'prueba2','PR2','direccion pruba2',12345,'prueba2@hotmail.com','prueba2.jpg','prueba2',2);
/*!40000 ALTER TABLE `cooperativa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `PerCod` int NOT NULL AUTO_INCREMENT,
  `PerIden` int NOT NULL,
  `PerApePat` varchar(50) NOT NULL,
  `PerApeMat` varchar(50) NOT NULL,
  `PerNom` varchar(50) NOT NULL,
  `PerNac` date NOT NULL,
  `PerCor` varchar(20) NOT NULL,
  `PerFot` blob NOT NULL,
  `PerCoo` int NOT NULL,
  PRIMARY KEY (`PerCod`),
  KEY `FK coo_idx` (`PerCoo`),
  CONSTRAINT `FK coo` FOREIGN KEY (`PerCoo`) REFERENCES `cooperativa` (`CooCod`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `ProCod` int NOT NULL AUTO_INCREMENT,
  `ProIden` int NOT NULL,
  `ProDes` varchar(50) NOT NULL,
  `ProSoc` int NOT NULL,
  `ProMon` varchar(10) NOT NULL,
  PRIMARY KEY (`ProCod`),
  KEY `FK prod_idx` (`ProSoc`),
  CONSTRAINT `FK prod` FOREIGN KEY (`ProSoc`) REFERENCES `socio` (`SocCod`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_cab`
--

DROP TABLE IF EXISTS `registro_cab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro_cab` (
  `RegCabCod` int NOT NULL,
  `RegCabFecha` date NOT NULL,
  `CooCod` int NOT NULL,
  PRIMARY KEY (`RegCabCod`),
  KEY `FK coope_idx` (`CooCod`),
  CONSTRAINT `FK coope` FOREIGN KEY (`CooCod`) REFERENCES `cooperativa` (`CooCod`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_cab`
--

LOCK TABLES `registro_cab` WRITE;
/*!40000 ALTER TABLE `registro_cab` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro_cab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro_det`
--

DROP TABLE IF EXISTS `registro_det`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro_det` (
  `RegDetCod` int NOT NULL AUTO_INCREMENT,
  `UsuCod` int NOT NULL,
  `PerCod` int NOT NULL,
  `SocCod` int NOT NULL,
  `RegDetCant` int NOT NULL,
  PRIMARY KEY (`RegDetCod`,`UsuCod`,`PerCod`,`SocCod`),
  KEY `FK usu_idx` (`UsuCod`),
  KEY `FK per_idx` (`PerCod`),
  KEY `FK soc_idx` (`SocCod`),
  CONSTRAINT `FK cab` FOREIGN KEY (`RegDetCod`) REFERENCES `registro_cab` (`RegCabCod`),
  CONSTRAINT `FK per` FOREIGN KEY (`PerCod`) REFERENCES `persona` (`PerCod`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK soc` FOREIGN KEY (`SocCod`) REFERENCES `socio` (`SocCod`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK usu` FOREIGN KEY (`UsuCod`) REFERENCES `usuario` (`UsuCod`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro_det`
--

LOCK TABLES `registro_det` WRITE;
/*!40000 ALTER TABLE `registro_det` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro_det` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `RolCod` int NOT NULL AUTO_INCREMENT,
  `RolRol` int NOT NULL,
  `RolNom` varchar(50) NOT NULL,
  `RolUsu` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`RolCod`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,1,'Contador','A');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socio`
--

DROP TABLE IF EXISTS `socio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socio` (
  `SocCod` int NOT NULL AUTO_INCREMENT,
  `SocIden` int NOT NULL,
  `SocApePat` varchar(50) NOT NULL,
  `SocApeMat` varchar(50) NOT NULL,
  `SocNom` varchar(50) NOT NULL,
  `SocNac` date NOT NULL,
  `SocCor` varchar(20) NOT NULL,
  `SocTipPro` varchar(30) NOT NULL,
  `SocCta` int NOT NULL,
  `SocEmp` int NOT NULL,
  `SocDep` varchar(30) NOT NULL,
  `SocPro` varchar(30) NOT NULL,
  `SocDis` varchar(30) NOT NULL,
  PRIMARY KEY (`SocCod`),
  KEY `FK emp_idx` (`SocEmp`),
  CONSTRAINT `FK emp` FOREIGN KEY (`SocEmp`) REFERENCES `cooperativa` (`CooCod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socio`
--

LOCK TABLES `socio` WRITE;
/*!40000 ALTER TABLE `socio` DISABLE KEYS */;
/*!40000 ALTER TABLE `socio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasa`
--

DROP TABLE IF EXISTS `tasa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasa` (
  `TasCod` int NOT NULL AUTO_INCREMENT,
  `TasIden` int NOT NULL,
  `TasDes` varchar(50) NOT NULL,
  `TasTasa` float NOT NULL,
  `TasPLazDias` int NOT NULL,
  `TasIni` date NOT NULL,
  `TasFin` date NOT NULL,
  `producto_ProCod` int NOT NULL,
  PRIMARY KEY (`TasCod`,`producto_ProCod`),
  KEY `fk_tasa_producto1_idx` (`producto_ProCod`),
  CONSTRAINT `fk_tasa_producto1` FOREIGN KEY (`producto_ProCod`) REFERENCES `producto` (`ProCod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasa`
--

LOCK TABLES `tasa` WRITE;
/*!40000 ALTER TABLE `tasa` DISABLE KEYS */;
/*!40000 ALTER TABLE `tasa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `UsuCod` int NOT NULL AUTO_INCREMENT,
  `UsuIde` int NOT NULL,
  `UsuUsu` varchar(30) NOT NULL,
  `UsuPas` varchar(20) NOT NULL,
  `UsuRol` int NOT NULL,
  `UsuEmp` int NOT NULL,
  PRIMARY KEY (`UsuCod`),
  KEY `FK rol_idx` (`UsuRol`),
  KEY `FK coop_idx` (`UsuEmp`),
  CONSTRAINT `FK coop` FOREIGN KEY (`UsuEmp`) REFERENCES `cooperativa` (`CooCod`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK rol` FOREIGN KEY (`UsuRol`) REFERENCES `rol` (`RolCod`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-08 20:56:16
