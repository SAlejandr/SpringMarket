-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 10.230.109.136    Database: springMarket
-- ------------------------------------------------------
-- Server version	8.0.27-0ubuntu0.20.04.1

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
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `usuario` bigint DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `borrado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario` (`usuario`),
  CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (1,1,NULL,_binary '\0'),(2,3,'2021-11-19 18:08:36',_binary '\0'),(3,7,'2021-11-25 19:14:33',_binary '\0'),(4,9,'2021-11-26 19:02:35',_binary '\0'),(5,8,'2022-01-26 17:44:07',_binary ''),(6,8,'2022-01-26 19:34:29',_binary '');
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listaCompra`
--

DROP TABLE IF EXISTS `listaCompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listaCompra` (
  `id` bigint NOT NULL,
  `articulo` bigint NOT NULL,
  `cantidad` int DEFAULT NULL,
  PRIMARY KEY (`id`,`articulo`),
  KEY `articulo` (`articulo`),
  CONSTRAINT `listaCompra_ibfk_1` FOREIGN KEY (`articulo`) REFERENCES `producto` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `listaCompra_ibfk_2` FOREIGN KEY (`id`) REFERENCES `compra` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listaCompra`
--

LOCK TABLES `listaCompra` WRITE;
/*!40000 ALTER TABLE `listaCompra` DISABLE KEYS */;
INSERT INTO `listaCompra` VALUES (1,1,1),(1,2,1),(3,5,1);
/*!40000 ALTER TABLE `listaCompra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lista_compra`
--

DROP TABLE IF EXISTS `lista_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lista_compra` (
  `cantidad` int DEFAULT NULL,
  `articulo` bigint NOT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`,`articulo`),
  KEY `FK2qgcksk30xvhg54elfc7iig7i` (`articulo`),
  CONSTRAINT `FK2qgcksk30xvhg54elfc7iig7i` FOREIGN KEY (`articulo`) REFERENCES `producto` (`id`),
  CONSTRAINT `FK3uv6tijxmynbiipw3x9wsg1i9` FOREIGN KEY (`id`) REFERENCES `compra` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lista_compra`
--

LOCK TABLES `lista_compra` WRITE;
/*!40000 ALTER TABLE `lista_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `lista_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `descuento` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'coche1','este fue el primer coche de Batman',1234.4,8),(2,'coche2','Este fue el primer coche que tuvo goku',1478.5,25),(5,'alex','otro coche',1,1),(6,'Elkaistor','p',1,2);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` tinyint NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Admin'),(2,'Cliente');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjeta`
--

DROP TABLE IF EXISTS `tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarjeta` (
  `numero` bigint NOT NULL,
  `titular` varchar(255) NOT NULL,
  `codigo_seguridad` int NOT NULL,
  `facturacion` varchar(255) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjeta`
--

LOCK TABLES `tarjeta` WRITE;
/*!40000 ALTER TABLE `tarjeta` DISABLE KEYS */;
INSERT INTO `tarjeta` VALUES (123456789,'MANDRAKE',123,'C/ CASA DE ALEJANDRO'),(147852369,'Alguien',123,'Una calle'),(741852963,'B',147,'c/13'),(987654321,'A',321,'C/OTRA CALLE');
/*!40000 ALTER TABLE `tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `contrasenna` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nacimiento` date DEFAULT NULL,
  `numero_tarjeta` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `usuario_ibfk_1` (`numero_tarjeta`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`numero_tarjeta`) REFERENCES `tarjeta` (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Marco','Polo','viajes','marco.polo@mail.com','1989-12-01',123456789),(2,'Atila','Elhuno','apocalipsis','caballo.negro@hunos.com','1963-10-28',987654321),(3,'alex','a','a','a@gmail.es','2021-11-03',987654321),(4,'alex','a','pl','alejandro.guerra@dfs.es','2021-11-17',987654321),(5,'Yo','guerra','1','ad@gmail.es','2021-11-10',123456789),(6,'Arnaket','DeMuial','$2a$10$BfVrTqN3NuusBu1nmjfRs.hqpONiVWXiMWiTWSSGi4opXq8.6UW56','nosealgo@mail.err','2021-06-11',NULL),(7,'b','b','$2a$10$JDCaJjxn0K4iVbaT1J2nHe/jMmvlkUrNWB2Bvy.8KdyqljYxRuYGy','b@gmail.com','2021-11-15',NULL),(8,'hoola','hol','$2a$10$s6V/xvtWg/kaTCjAML8jHugpvzIukPyR9V4JC8PXORZsRjSiuStpq','hola@gmail.com','2021-11-23',123456789),(9,'prueba','prueba','$2a$10$ZKtOVdWo.xTZCjYF63lEGeebAss1hqO2RfaEXLWLyKk/Rv3YOYRUq','prueba@gmail.com','2021-11-23',NULL),(10,'alex','guerra','$2a$10$p30XCDXgBvEUCwj8ksBLVer81b4qAtYEwy7FYLiLuWQMcTsPLd3Um','aaaaa@gmail.com','2022-01-05',NULL),(11,'Marcos','Puente','$2a$10$fXBcqzGnwIhGl.0sf1uHNuVBzD7Lf3iWnE/9TvZQyTYJf15TBkiE2','marcos@marcos.com','2022-01-05',987654321);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_rol` (
  `rol` tinyint NOT NULL,
  `usuario` bigint NOT NULL,
  PRIMARY KEY (`rol`,`usuario`),
  KEY `usuario` (`usuario`),
  CONSTRAINT `usuario_rol_ibfk_1` FOREIGN KEY (`rol`) REFERENCES `rol` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `usuario_rol_ibfk_2` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol` VALUES (2,6),(1,7),(2,7),(2,8),(2,9),(2,10),(2,11);
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-28 16:08:33
