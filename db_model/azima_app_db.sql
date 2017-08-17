-- MySQL Script generated by MySQL Workbench
-- Thu 17 Aug 2017 05:19:56 PM EAT
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema azima_app_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema azima_app_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `azima_app_db` DEFAULT CHARACTER SET utf8 ;
USE `azima_app_db` ;

-- -----------------------------------------------------
-- Table `azima_app_db`.`tblUsers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azima_app_db`.`tblUsers` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `othernames` VARCHAR(100) NULL,
  `email` VARCHAR(200) NULL,
  `gender` VARCHAR(10) NULL,
  `idno` VARCHAR(10) NULL,
  `dob` DATE NULL,
  `imei` VARCHAR(20) NULL,
  `loan_limit` FLOAT NULL,
  `phone` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azima_app_db`.`tblLoanApplications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azima_app_db`.`tblLoanApplications` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `amount` FLOAT NULL,
  `date` VARCHAR(45) NULL,
  `tblUsers_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `tblUsers_id`),
  INDEX `fk_tblLoanApplications_tblUsers_idx` (`tblUsers_id` ASC),
  CONSTRAINT `fk_tblLoanApplications_tblUsers`
    FOREIGN KEY (`tblUsers_id`)
    REFERENCES `azima_app_db`.`tblUsers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
