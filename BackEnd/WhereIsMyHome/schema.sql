-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema homedb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema homedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `homedb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `homedb` ;

-- -----------------------------------------------------
-- Table `homedb`.`dongcode`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `homedb`.`dongcode` ;

CREATE TABLE IF NOT EXISTS `homedb`.`dongcode` (
  `dongCode` VARCHAR(10) NOT NULL,
  `sidoName` VARCHAR(30) NULL DEFAULT NULL,
  `gugunName` VARCHAR(30) NULL DEFAULT NULL,
  `dongName` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`dongCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `homedb`.`houseinfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `homedb`.`houseinfo` ;

CREATE TABLE IF NOT EXISTS `homedb`.`houseinfo` (
  `aptCode` BIGINT NOT NULL,
  `buildYear` INT NULL DEFAULT NULL,
  `roadName` VARCHAR(40) NULL DEFAULT NULL,
  `roadNameBonbun` VARCHAR(5) NULL DEFAULT NULL,
  `roadNameBubun` VARCHAR(5) NULL DEFAULT NULL,
  `roadNameSeq` VARCHAR(2) NULL DEFAULT NULL,
  `roadNameBasementCode` VARCHAR(1) NULL DEFAULT NULL,
  `roadNameCode` VARCHAR(7) NULL DEFAULT NULL,
  `dong` VARCHAR(40) NULL DEFAULT NULL,
  `bonbun` VARCHAR(4) NULL DEFAULT NULL,
  `bubun` VARCHAR(4) NULL DEFAULT NULL,
  `sigunguCode` VARCHAR(5) NULL DEFAULT NULL,
  `eubmyundongCode` VARCHAR(5) NULL DEFAULT NULL,
  `dongCode` VARCHAR(10) NULL DEFAULT NULL,
  `landCode` VARCHAR(1) NULL DEFAULT NULL,
  `apartmentName` VARCHAR(40) NULL DEFAULT NULL,
  `jibun` VARCHAR(10) NULL DEFAULT NULL,
  `lng` VARCHAR(30) NULL DEFAULT NULL,
  `lat` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`aptCode`),
  UNIQUE INDEX `UNIQUE` (`buildYear` ASC, `apartmentName` ASC, `jibun` ASC, `sigunguCode` ASC, `eubmyundongCode` ASC) INVISIBLE,
  INDEX `houseinfo_dongCode_dongcode_dongCode_fk_idx` (`dongCode` ASC) INVISIBLE,
  CONSTRAINT `houseinfo_dongCode_dongcode_dongCode_fk`
    FOREIGN KEY (`dongCode`)
    REFERENCES `homedb`.`dongcode` (`dongCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `homedb`.`housedeal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `homedb`.`housedeal` ;

CREATE TABLE IF NOT EXISTS `homedb`.`housedeal` (
  `no` BIGINT NOT NULL,
  `dealAmount` VARCHAR(20) NULL DEFAULT NULL,
  `dealYear` INT NULL DEFAULT NULL,
  `dealMonth` INT NULL DEFAULT NULL,
  `dealDay` INT NULL DEFAULT NULL,
  `area` VARCHAR(20) NULL DEFAULT NULL,
  `floor` VARCHAR(4) NULL DEFAULT NULL,
  `cancelDealType` VARCHAR(1) NULL DEFAULT NULL,
  `aptCode` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  INDEX `housedeal_aptCode_houseinfo_aptCode_fk_idx` (`aptCode` ASC) VISIBLE,
  CONSTRAINT `housedeal_aptCode_houseinfo_aptCode_fk`
    FOREIGN KEY (`aptCode`)
    REFERENCES `homedb`.`houseinfo` (`aptCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `homedb`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `homedb`.`member` ;

CREATE TABLE IF NOT EXISTS `homedb`.`member` (
  `memberid` VARCHAR(30) NOT NULL,
  `pwd` VARCHAR(30) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `createdate` TIMESTAMP NOT NULL DEFAULT current_timestamp(),
  `auth` ENUM('nomal', 'admin') NOT NULL DEFAULT 'nomal',
  PRIMARY KEY (`memberid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `homedb`.`notice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `homedb`.`notice` ;

CREATE TABLE IF NOT EXISTS `homedb`.`notice` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `createdate` TIMESTAMP NOT NULL DEFAULT current_timestamp(),
  `title` VARCHAR(50) NOT NULL,
  `content` VARCHAR(4000) NOT NULL,
  `count` INT NOT NULL DEFAULT 0,
  `memberid` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`no`),
  INDEX `fk_notice_member1_idx` (`memberid` ASC) VISIBLE,
  CONSTRAINT `fk_notice_member1`
    FOREIGN KEY (`memberid`)
    REFERENCES `homedb`.`member` (`memberid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `homedb`.`interestarea`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `homedb`.`interestarea` ;

CREATE TABLE IF NOT EXISTS `homedb`.`interestarea` (
  `dongCode` VARCHAR(10) NOT NULL,
  `memberid` VARCHAR(30) NOT NULL,
  INDEX `fk_interestarea_dongcode1_idx` (`dongCode` ASC) VISIBLE,
  INDEX `fk_interestarea_member1_idx` (`memberid` ASC) VISIBLE,
  PRIMARY KEY (`dongCode`, `memberid`),
  CONSTRAINT `fk_interestarea_dongcode1`
    FOREIGN KEY (`dongCode`)
    REFERENCES `homedb`.`dongcode` (`dongCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_interestarea_member1`
    FOREIGN KEY (`memberid`)
    REFERENCES `homedb`.`member` (`memberid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `homedb`.`interestarea`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `homedb`.`interestarea` ;

CREATE TABLE IF NOT EXISTS `homedb`.`interestarea` (
  `dongCode` VARCHAR(10) NOT NULL,
  `memberid` VARCHAR(30) NOT NULL,
  INDEX `fk_interestarea_dongcode1_idx` (`dongCode` ASC) VISIBLE,
  INDEX `fk_interestarea_member1_idx` (`memberid` ASC) VISIBLE,
  PRIMARY KEY (`dongCode`, `memberid`),
  CONSTRAINT `fk_interestarea_dongcode1`
    FOREIGN KEY (`dongCode`)
    REFERENCES `homedb`.`dongcode` (`dongCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_interestarea_member1`
    FOREIGN KEY (`memberid`)
    REFERENCES `homedb`.`member` (`memberid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
