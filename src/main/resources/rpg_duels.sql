CREATE DATABASE  IF NOT EXISTS `rpg_duels` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rpg_duels`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: rpg_duels
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `characters`
--

DROP TABLE IF EXISTS `characters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `characters` (
  `uuid` binary(16) NOT NULL,
  `name` varchar(45) NOT NULL,
  `level` tinyint unsigned NOT NULL DEFAULT '1',
  `unit_armor` tinyint NOT NULL DEFAULT '10',
  `unit_magic_resistance` tinyint NOT NULL DEFAULT '10',
  `unit_class` tinyint unsigned NOT NULL,
  `unit_stats` binary(16) NOT NULL,
  `unit_model` int NOT NULL DEFAULT '0',
  `player_uuid` binary(16) NOT NULL,
  PRIMARY KEY (`uuid`,`player_uuid`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_unit_template_unit_stats_template_idx` (`unit_stats`),
  KEY `fk_characters_player1_idx` (`player_uuid`),
  CONSTRAINT `fk_characters_player1` FOREIGN KEY (`player_uuid`) REFERENCES `player` (`uuid`),
  CONSTRAINT `fk_unit_stat0` FOREIGN KEY (`unit_stats`) REFERENCES `unit_stat_template` (`uuid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characters`
--

LOCK TABLES `characters` WRITE;
/*!40000 ALTER TABLE `characters` DISABLE KEYS */;
INSERT INTO `characters` VALUES (_binary 'ˆ\Ü2eZ¥9\êª\Ç\ëB_Lu','Alysrazor',10,10,10,6,_binary '§s÷V\í³8e“D‘¼·¡Á',0,_binary ' ¥üL7i<÷›Põ\Z–\è‘');
/*!40000 ALTER TABLE `characters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characters_spell`
--

DROP TABLE IF EXISTS `characters_spell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `characters_spell` (
  `character_uuid` binary(16) NOT NULL,
  `spell_uuid` binary(16) NOT NULL,
  PRIMARY KEY (`character_uuid`,`spell_uuid`),
  KEY `fk_characters_has_spell_template_spell_template1_idx` (`spell_uuid`),
  KEY `fk_characters_has_spell_template_characters1_idx` (`character_uuid`),
  CONSTRAINT `fk_characters_has_spell_template_characters1` FOREIGN KEY (`character_uuid`) REFERENCES `characters` (`uuid`),
  CONSTRAINT `fk_characters_has_spell_template_spell_template1` FOREIGN KEY (`spell_uuid`) REFERENCES `spell_template` (`spell_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characters_spell`
--

LOCK TABLES `characters_spell` WRITE;
/*!40000 ALTER TABLE `characters_spell` DISABLE KEYS */;
INSERT INTO `characters_spell` VALUES (_binary 'ˆ\Ü2eZ¥9\êª\Ç\ëB_Lu',_binary 'þ^8\Ö\\ü>G¥3—f\ÆKb\Ú');
/*!40000 ALTER TABLE `characters_spell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creature_spell`
--

DROP TABLE IF EXISTS `creature_spell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creature_spell` (
  `creature_uuid` binary(16) NOT NULL,
  `spell_uuid` binary(16) NOT NULL,
  PRIMARY KEY (`creature_uuid`,`spell_uuid`),
  KEY `fk_creature_template_has_spell_template_spell_template1_idx` (`spell_uuid`),
  KEY `fk_creature_template_has_spell_template_creature_template1_idx` (`creature_uuid`),
  CONSTRAINT `fk_creature_template_has_spell_template_creature_template1` FOREIGN KEY (`creature_uuid`) REFERENCES `creature_template` (`uuid`),
  CONSTRAINT `fk_creature_template_has_spell_template_spell_template1` FOREIGN KEY (`spell_uuid`) REFERENCES `spell_template` (`spell_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creature_spell`
--

LOCK TABLES `creature_spell` WRITE;
/*!40000 ALTER TABLE `creature_spell` DISABLE KEYS */;
/*!40000 ALTER TABLE `creature_spell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creature_template`
--

DROP TABLE IF EXISTS `creature_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creature_template` (
  `uuid` binary(16) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `level` tinyint unsigned NOT NULL DEFAULT '1',
  `unit_armor` tinyint NOT NULL DEFAULT '10',
  `unit_magic_resistance` tinyint NOT NULL DEFAULT '10',
  `unit_class` tinyint unsigned NOT NULL,
  `unit_stats` binary(16) NOT NULL,
  `unit_model` int NOT NULL DEFAULT '0',
  `comment` text,
  PRIMARY KEY (`uuid`),
  KEY `fk_unit_template_unit_stats_template_idx` (`unit_stats`),
  CONSTRAINT `fk_unit_stat1` FOREIGN KEY (`unit_stats`) REFERENCES `unit_stat_template` (`uuid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creature_template`
--

LOCK TABLES `creature_template` WRITE;
/*!40000 ALTER TABLE `creature_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `creature_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player` (
  `uuid` binary(16) NOT NULL,
  `player_name` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `salt` varchar(64) NOT NULL,
  `android_version` tinyint unsigned NOT NULL DEFAULT '9',
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `player_name_UNIQUE` (`player_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (_binary ' ¥üL7i<÷›Põ\Z–\è‘','Alysrazor','6a6d1524b41ff04454905773f691e7e0c9fbdb5697590004b973a623cc0e0933','1ed18e7cc8409b852eec39e8528f1151G-OLc.5)Q>/MS9{yr$b|U5!TcTb8Ho9P',13);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spell_template`
--

DROP TABLE IF EXISTS `spell_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spell_template` (
  `spell_uuid` binary(16) NOT NULL,
  `spell_name` varchar(45) DEFAULT NULL,
  `spell_description` varchar(255) DEFAULT 'spell_description',
  `spell_school` tinyint unsigned NOT NULL DEFAULT '1',
  `base_damage` smallint DEFAULT NULL,
  `stat_modifier` tinyint unsigned NOT NULL,
  `stat_multiplier` double unsigned NOT NULL,
  PRIMARY KEY (`spell_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spell_template`
--

LOCK TABLES `spell_template` WRITE;
/*!40000 ALTER TABLE `spell_template` DISABLE KEYS */;
INSERT INTO `spell_template` VALUES (_binary 'þ^8\Ö\\ü>G¥3—f\ÆKb\Ú','Descarga de escarcha','Lanza una descarga de escarcha al objetivo haciÃ©ndole $1 de daÃ±o de frÃ­o.',2,12,3,1.08);
/*!40000 ALTER TABLE `spell_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit_stat_template`
--

DROP TABLE IF EXISTS `unit_stat_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit_stat_template` (
  `uuid` binary(16) NOT NULL,
  `strength` tinyint unsigned DEFAULT '10',
  `dexterity` tinyint unsigned DEFAULT '10',
  `constitution` tinyint unsigned DEFAULT '10',
  `intelligence` tinyint unsigned DEFAULT '10',
  `wisdom` tinyint unsigned DEFAULT '10',
  `charisma` tinyint unsigned DEFAULT '10',
  `comment` text,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit_stat_template`
--

LOCK TABLES `unit_stat_template` WRITE;
/*!40000 ALTER TABLE `unit_stat_template` DISABLE KEYS */;
INSERT INTO `unit_stat_template` VALUES (_binary '§s÷V\í³8e“D‘¼·¡Á',10,10,10,10,10,10,'EstadÃ­sticas por defecto');
/*!40000 ALTER TABLE `unit_stat_template` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-10  6:41:11
