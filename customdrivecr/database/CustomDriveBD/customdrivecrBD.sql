CREATE DATABASE  IF NOT EXISTS `customdrivecr` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `customdrivecr`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: customdrivecr
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `accesorios`
--

DROP TABLE IF EXISTS `accesorios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accesorios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` bit(1) DEFAULT NULL,
  `categoria` varchar(255) NOT NULL,
  `descripcion` varchar(1000) DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `precio` decimal(38,2) NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accesorios`
--

LOCK TABLES `accesorios` WRITE;
/*!40000 ALTER TABLE `accesorios` DISABLE KEYS */;
INSERT INTO `accesorios` VALUES (1,_binary '','iluminacion',' Luces LED para mejorar la visibilidad nocturna','','Luces LED H11',10900.00,0),(2,_binary '','ruedas','llanatas para carro','','llantas ',50000.00,0);
/*!40000 ALTER TABLE `accesorios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compatibilidades`
--

DROP TABLE IF EXISTS `compatibilidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compatibilidades` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` bit(1) DEFAULT NULL,
  `notas` varchar(255) DEFAULT NULL,
  `accesorio_id` bigint NOT NULL,
  `vehiculo_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKogpjx58j7s5wdeljv3ak3p116` (`accesorio_id`),
  KEY `FKmulehc2nxpp51tt8cj09x8172` (`vehiculo_id`),
  CONSTRAINT `FKmulehc2nxpp51tt8cj09x8172` FOREIGN KEY (`vehiculo_id`) REFERENCES `vehiculos` (`id`),
  CONSTRAINT `FKogpjx58j7s5wdeljv3ak3p116` FOREIGN KEY (`accesorio_id`) REFERENCES `accesorios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compatibilidades`
--

LOCK TABLES `compatibilidades` WRITE;
/*!40000 ALTER TABLE `compatibilidades` DISABLE KEYS */;
INSERT INTO `compatibilidades` VALUES (1,_binary '','Compatible con instalación frontal.',1,1),(2,_binary '',' Compatible con versión sedán.',1,3),(3,_binary '','compatible con tucson 2022 suv',1,3),(4,_binary '','compatible con coupe',2,1);
/*!40000 ALTER TABLE `compatibilidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pedido`
--

DROP TABLE IF EXISTS `detalle_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int DEFAULT NULL,
  `precio_unitario` decimal(38,2) DEFAULT NULL,
  `subtotal` decimal(38,2) DEFAULT NULL,
  `accesorio_id` bigint NOT NULL,
  `pedido_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt17xuqqxewny7awsbnaj02alr` (`accesorio_id`),
  KEY `FKg9h17fjynh9lgf1kbn0v9p4kf` (`pedido_id`),
  CONSTRAINT `FKg9h17fjynh9lgf1kbn0v9p4kf` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`),
  CONSTRAINT `FKt17xuqqxewny7awsbnaj02alr` FOREIGN KEY (`accesorio_id`) REFERENCES `accesorios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedido`
--

LOCK TABLES `detalle_pedido` WRITE;
/*!40000 ALTER TABLE `detalle_pedido` DISABLE KEYS */;
INSERT INTO `detalle_pedido` VALUES (1,2,10900.00,21800.00,1,1),(2,1,10900.00,10900.00,1,2),(3,1,10900.00,10900.00,1,3),(4,1,10900.00,10900.00,1,4),(5,1,50000.00,50000.00,2,5),(6,1,50000.00,50000.00,2,6),(7,1,50000.00,50000.00,2,7),(8,1,50000.00,50000.00,2,8);
/*!40000 ALTER TABLE `detalle_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcas`
--

DROP TABLE IF EXISTS `marcas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marcas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` bit(1) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `pais_origen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKf9j5vnky0egidx9qqqa4gbf85` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcas`
--

LOCK TABLES `marcas` WRITE;
/*!40000 ALTER TABLE `marcas` DISABLE KEYS */;
INSERT INTO `marcas` VALUES (1,_binary '','Marca japonesa de vehículos.','toyota','japon'),(2,_binary '','Marca surcoreana de vehículos.','hyundai','corea del sur'),(3,_binary '','Marca alemana de vehículos premium.','bmw','alemania');
/*!40000 ALTER TABLE `marcas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelos`
--

DROP TABLE IF EXISTS `modelos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modelos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` bit(1) DEFAULT NULL,
  `anio_fin` int DEFAULT NULL,
  `anio_inicio` int DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `marca_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfwmnvprqtv5jc4t60c076mj7h` (`marca_id`),
  CONSTRAINT `FKfwmnvprqtv5jc4t60c076mj7h` FOREIGN KEY (`marca_id`) REFERENCES `marcas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelos`
--

LOCK TABLES `modelos` WRITE;
/*!40000 ALTER TABLE `modelos` DISABLE KEYS */;
INSERT INTO `modelos` VALUES (1,_binary '',2024,2024,'Sedán compacto compatible con accesorios de uso diario.','corolla',1),(2,_binary '',2024,2014,' Vehículo deportivo híbrido de alto rendimiento.','i8 Coupé',3),(3,_binary '',2023,2019,'SUV compatible con accesorios interiores y exteriores.','Tucson',2);
/*!40000 ALTER TABLE `modelos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `correo_cliente` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_pedido` datetime(6) DEFAULT NULL,
  `nombre_cliente` varchar(255) DEFAULT NULL,
  `telefono_cliente` varchar(255) DEFAULT NULL,
  `total` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,'admin@customdrivecr.com','CONFIRMADO','2026-06-25 03:51:17.506834','Administrador CustomDrive','86179623',21800.00),(2,'admin@customdrivecr.com','PENDIENTE','2026-06-25 04:32:01.573842','Administrador CustomDrive','2222',10900.00),(3,'julio@gmail.com','PENDIENTE','2026-06-25 04:42:18.719502','julio','78287872',10900.00),(4,'cliente@customdrivecr.com','PENDIENTE','2026-06-27 22:28:55.308966','Cliente Prueba','86179623',10900.00),(5,'admin@customdrivecr.com','PENDIENTE','2026-06-27 22:46:05.390465','Administrador CustomDrive','2222',50000.00),(6,'cliente@customdrivecr.com','PENDIENTE','2026-06-27 23:16:04.844823','Cliente Prueba','11111',50000.00),(7,'cliente@customdrivecr.com','PENDIENTE','2026-06-27 23:16:58.700688','Cliente Prueba','222',50000.00),(8,'cliente@customdrivecr.com','PENDIENTE','2026-06-28 02:55:18.676533','Cliente Prueba','86179634',50000.00);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` bit(1) DEFAULT NULL,
  `correo` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKcdmw5hxlfj78uf4997i3qyyw5` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,_binary '','admin@customdrivecr.com','Administrador CustomDrive','1234','ADMIN'),(2,_binary '','cliente@customdrivecr.com','Cliente Prueba','1234','CLIENTE'),(3,_binary '\0','prueba@customdrivecr.com',' Usuario Prueba','1234','CLIENTE');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculos`
--

DROP TABLE IF EXISTS `vehiculos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `activo` bit(1) DEFAULT NULL,
  `anio` int DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `modelo_id` bigint NOT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmeeb0gml9pfxdswujruwuvjyg` (`modelo_id`),
  CONSTRAINT `FKmeeb0gml9pfxdswujruwuvjyg` FOREIGN KEY (`modelo_id`) REFERENCES `modelos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculos`
--

LOCK TABLES `vehiculos` WRITE;
/*!40000 ALTER TABLE `vehiculos` DISABLE KEYS */;
INSERT INTO `vehiculos` VALUES (1,_binary '',2024,' Vehículo deportivo híbrido compatible con accesorios premium.','coupe',1,'/uploads/vehiculos/5b095b94-079d-4cb0-b760-9501348d72a4.webp'),(2,_binary '',2020,' Vehículo compacto compatible con accesorios de uso diario.','sedan',1,NULL),(3,_binary '',2022,'SUV familiar compatible con accesorios interiores y exteriores.','SUV',3,NULL),(4,_binary '',2024,'bmw de lujo','coupe',2,NULL);
/*!40000 ALTER TABLE `vehiculos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-27 20:11:13
