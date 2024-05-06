-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema rpg_duels
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `rpg_duels` ;

-- -----------------------------------------------------
-- Schema rpg_duels
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rpg_duels` DEFAULT CHARACTER SET utf8 ;
USE `rpg_duels` ;

-- -----------------------------------------------------
-- Table `rpg_duels`.`unit_stats_template`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rpg_duels`.`unit_stats_template` ;

CREATE TABLE IF NOT EXISTS `rpg_duels`.`unit_stats_template` (
  `uuid` BINARY(16) NOT NULL,
  `strength` TINYINT UNSIGNED NULL DEFAULT 10,
  `dexterity` TINYINT UNSIGNED NULL DEFAULT 10,
  `constitution` TINYINT UNSIGNED NULL DEFAULT 10,
  `intelligence` TINYINT UNSIGNED NULL DEFAULT 10,
  `wisdom` TINYINT UNSIGNED NULL DEFAULT 10,
  `charisma` TINYINT UNSIGNED NULL DEFAULT 10,
  `comment` TEXT NULL,
  PRIMARY KEY (`uuid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rpg_duels`.`unit_template`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rpg_duels`.`unit_template` ;

CREATE TABLE IF NOT EXISTS `rpg_duels`.`unit_template` (
  `uuid` BINARY(16) NOT NULL,
  `name` VARCHAR(45) NULL,
  `level` TINYINT UNSIGNED NOT NULL DEFAULT 1,
  `unit_armor` TINYINT NOT NULL DEFAULT 10,
  `unit_magic_resistance` TINYINT NOT NULL DEFAULT 10,
  `unit_class` TINYINT UNSIGNED NOT NULL,
  `unit_stats` BINARY(16) NOT NULL,
  `comment` TEXT NULL,
  PRIMARY KEY (`uuid`, `unit_stats`),
  INDEX `fk_unit_template_unit_stats_template_idx` (`unit_stats` ASC) VISIBLE,
  CONSTRAINT `fk_unit_stat`
    FOREIGN KEY (`unit_stats`)
    REFERENCES `rpg_duels`.`unit_stats_template` (`uuid`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rpg_duels`.`spell_template`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rpg_duels`.`spell_template` ;

CREATE TABLE IF NOT EXISTS `rpg_duels`.`spell_template` (
  `spell_uuid` BINARY(16) NOT NULL,
  `spell_name` VARCHAR(45) NULL,
  `spell_school` TINYINT UNSIGNED NOT NULL DEFAULT 1,
  `base_damage` SMALLINT NULL,
  `stat_modifier` TINYINT UNSIGNED NOT NULL,
  `stat_multiplier` DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (`spell_uuid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rpg_duels`.`unit_spells`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rpg_duels`.`unit_spells` ;

CREATE TABLE IF NOT EXISTS `rpg_duels`.`unit_spells` (
  `unit_uuid` BINARY(16) NOT NULL,
  `spell_uuid` BINARY(16) NOT NULL,
  PRIMARY KEY (`unit_uuid`, `spell_uuid`),
  INDEX `fk_unit_template_has_spell_template_spell_template1_idx` (`spell_uuid` ASC) VISIBLE,
  INDEX `fk_unit_template_has_spell_template_unit_template1_idx` (`unit_uuid` ASC) VISIBLE,
  CONSTRAINT `fk_unit_spell`
    FOREIGN KEY (`unit_uuid`)
    REFERENCES `rpg_duels`.`unit_template` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_spell`
    FOREIGN KEY (`spell_uuid`)
    REFERENCES `rpg_duels`.`spell_template` (`spell_uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rpg_duels`.`player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rpg_duels`.`player` ;

CREATE TABLE IF NOT EXISTS `rpg_duels`.`player` (
  `uuid` BINARY(16) NOT NULL,
  `player_name` VARCHAR(20) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `salt` VARCHAR(64) NOT NULL,
  `android_version` TINYINT UNSIGNED NOT NULL DEFAULT 9,
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `player_name_UNIQUE` (`player_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rpg_duels`.`characters`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rpg_duels`.`characters` ;

CREATE TABLE IF NOT EXISTS `rpg_duels`.`characters` (
  `character_uuid` BINARY(16) NOT NULL,
  `unit_uuid` BINARY(16) NOT NULL,
  `player_uuid` BINARY(16) NOT NULL,
  PRIMARY KEY (`character_uuid`, `unit_uuid`, `player_uuid`),
  INDEX `fk_characters_player1_idx` (`player_uuid` ASC) VISIBLE,
  INDEX `fk_characters_unit_template1_idx` (`unit_uuid` ASC) VISIBLE,
  CONSTRAINT `fk_player`
    FOREIGN KEY (`player_uuid`)
    REFERENCES `rpg_duels`.`player` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_unit_template`
    FOREIGN KEY (`unit_uuid`)
    REFERENCES `rpg_duels`.`unit_template` (`uuid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
