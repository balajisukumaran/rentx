-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CSCI5308_8_DEVINT
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CSCI5308_8_DEVINT
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CSCI5308_8_DEVINT` DEFAULT CHARACTER SET utf8 ;
USE `CSCI5308_8_DEVINT` ;

-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Orders` (
  `orderID` INT NOT NULL,
  `customerID` INT NULL,
  `providerID` INT NULL,
  `orderDate` DATE NULL,
  `deliveryDate` DATE NULL,
  `totalCost` INT NULL,
  PRIMARY KEY (`orderID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Area`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Area` (
  `areaID` INT NOT NULL AUTO_INCREMENT,
  `area_name` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  PRIMARY KEY (`areaID`))
ENGINE = InnoDB
AUTO_INCREMENT = 501;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Category` (
  `categoryID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`categoryID`))
ENGINE = InnoDB
AUTO_INCREMENT = 11;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Product` (
  `productID` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `Description` VARCHAR(45) NOT NULL,
  `Price` VARCHAR(45) NULL,
  `categoryID` INT NULL,
  `areaID` INT NULL,
  PRIMARY KEY (`productID`),
  CONSTRAINT `aID`
    FOREIGN KEY (`areaID`)
    REFERENCES `CSCI5308_8_DEVINT`.`Area` (`areaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `caID`
    FOREIGN KEY (`categoryID`)
    REFERENCES `CSCI5308_8_DEVINT`.`Category` (`categoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 50001;

CREATE INDEX `areaID_idx` ON `CSCI5308_8_DEVINT`.`Product` (`areaID` ASC) VISIBLE;

CREATE INDEX `categoryID_idx` ON `CSCI5308_8_DEVINT`.`Product` (`categoryID` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Images` (
  `imageID` INT NOT NULL AUTO_INCREMENT,
  `productID` INT NOT NULL,
  `Image` LONGBLOB NULL,
  `url` VARCHAR(500) NULL,
  PRIMARY KEY (`imageID`),
  CONSTRAINT `idProduct`
    FOREIGN KEY (`productID`)
    REFERENCES `CSCI5308_8_DEVINT`.`Product` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 40001;

CREATE INDEX `idProduct_idx` ON `CSCI5308_8_DEVINT`.`Images` (`productID` ASC) INVISIBLE;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Credentials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Credentials` (
  `phone` CHAR(15) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`phone`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`User` (
  `userID` INT NOT NULL AUTO_INCREMENT,
  `phone` CHAR(15) NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `pref_language` ENUM('english', 'french') NULL,
  `joining_date` DATE NULL,
  `privacy` ENUM("enabled", "disabled") NULL,
  `status` ENUM("blocked", "unknown", "verified") NULL,
  `area_ID` INT NOT NULL,
  PRIMARY KEY (`userID`, `phone`),
  CONSTRAINT `phoneNumber`
    FOREIGN KEY (`phone`)
    REFERENCES `CSCI5308_8_DEVINT`.`Credentials` (`phone`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `area_ID`
    FOREIGN KEY (`area_ID`)
    REFERENCES `CSCI5308_8_DEVINT`.`Area` (`areaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1001;

CREATE INDEX `phone_idx` ON `CSCI5308_8_DEVINT`.`User` (`phone` ASC) INVISIBLE;

CREATE INDEX `area_ID_idx` ON `CSCI5308_8_DEVINT`.`User` (`area_ID` ASC) INVISIBLE;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Advertisement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Advertisement` (
  `advertisementID` INT NOT NULL AUTO_INCREMENT,
  `verification_status` ENUM('unknown', 'rejected', 'verified') NOT NULL,
  `adv_title` VARCHAR(45) NULL,
  `is_active` ENUM('y', 'n') NULL,
  `post_date` DATETIME NULL,
  `expiry_date` DATETIME NULL,
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`advertisementID`),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `CSCI5308_8_DEVINT`.`User` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `CSCI5308_8_DEVINT`.`Product` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 20001;

CREATE INDEX `user_id_idx` ON `CSCI5308_8_DEVINT`.`Advertisement` (`user_id` ASC) INVISIBLE;

CREATE INDEX `product_id_idx` ON `CSCI5308_8_DEVINT`.`Advertisement` (`product_id` ASC) INVISIBLE;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Purchase` (
  `productID` INT NOT NULL AUTO_INCREMENT,
  `buyer_id` INT NOT NULL,
  `seller_id` INT NOT NULL,
  `date_of_sale` VARCHAR(45) NULL,
  `advertisementID` INT NOT NULL,
  PRIMARY KEY (`productID`, `buyer_id`, `seller_id`, `advertisementID`),
  CONSTRAINT `buyer_id`
    FOREIGN KEY (`buyer_id`)
    REFERENCES `CSCI5308_8_DEVINT`.`User` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `seller_id`
    FOREIGN KEY (`seller_id`)
    REFERENCES `CSCI5308_8_DEVINT`.`User` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Purchase_Advertisement1`
    FOREIGN KEY (`advertisementID`)
    REFERENCES `CSCI5308_8_DEVINT`.`Advertisement` (`advertisementID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10001;

CREATE INDEX `seller_id_idx` ON `CSCI5308_8_DEVINT`.`Purchase` (`seller_id` ASC) INVISIBLE;

CREATE INDEX `fk_Purchase_Advertisement1_idx` ON `CSCI5308_8_DEVINT`.`Purchase` (`advertisementID` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Adminstrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Adminstrator` (
  `phone` CHAR(15) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `date_of_joining` DATE NULL,
  `role` VARCHAR(25) NULL,
  PRIMARY KEY (`phone`, `first_name`),
  CONSTRAINT `phoneNo`
    FOREIGN KEY (`phone`)
    REFERENCES `CSCI5308_8_DEVINT`.`Credentials` (`phone`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Message` (
  `sender_id` INT NOT NULL,
  `receiver_id` INT NOT NULL,
  `message` VARCHAR(45) NULL,
  `timestamp` DATETIME NOT NULL,
  PRIMARY KEY (`sender_id`, `receiver_id`),
  CONSTRAINT `sender_id`
    FOREIGN KEY (`sender_id`)
    REFERENCES `CSCI5308_8_DEVINT`.`User` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `receiver_id`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `CSCI5308_8_DEVINT`.`User` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `receiver_id_idx` ON `CSCI5308_8_DEVINT`.`Message` (`receiver_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Furniture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Furniture` (
  `furnitureID` INT NOT NULL,
  `cat_id` INT NOT NULL,
  `manufacturer` VARCHAR(45) NULL,
  `Furniture_Type` VARCHAR(45) NOT NULL,
  `Year_of_Purchase` YEAR(4) NOT NULL,
  `condition` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`furnitureID`),
  CONSTRAINT `cat_id`
    FOREIGN KEY (`cat_id`)
    REFERENCES `CSCI5308_8_DEVINT`.`Category` (`categoryID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `idFurniture`
    FOREIGN KEY (`furnitureID`)
    REFERENCES `CSCI5308_8_DEVINT`.`Product` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 51;

CREATE INDEX `cat_id_idx` ON `CSCI5308_8_DEVINT`.`Furniture` (`cat_id` ASC) INVISIBLE;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Kitchen_Appliances`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Kitchen_Appliances` (
  `kitchenAppliancesID` INT NOT NULL,
  `cat_id` INT NOT NULL,
  `Appliance_Type` VARCHAR(45) NULL,
  `manufacturer` VARCHAR(45) NULL,
  `Year_Of_Purchase` YEAR(4) NULL,
  `model_name` VARCHAR(45) NULL,
  PRIMARY KEY (`kitchenAppliancesID`),
  CONSTRAINT `idKitchen_Appliances`
    FOREIGN KEY (`kitchenAppliancesID`)
    REFERENCES `CSCI5308_8_DEVINT`.`Product` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `catid`
    FOREIGN KEY (`cat_id`)
    REFERENCES `CSCI5308_8_DEVINT`.`Category` (`categoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 81;

CREATE INDEX `catid_idx` ON `CSCI5308_8_DEVINT`.`Kitchen_Appliances` (`cat_id` ASC) INVISIBLE;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Electronic_Gadgets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Electronic_Gadgets` (
  `electronicGadgetsID` INT NOT NULL AUTO_INCREMENT,
  `categoryID` INT NOT NULL,
  `manufacturer` VARCHAR(45) NULL,
  `year_of_purchase` YEAR(4) NOT NULL,
  `model_name` VARCHAR(45) NOT NULL,
  `Gadget_Type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`electronicGadgetsID`),
  CONSTRAINT `idElectronic_Gadgets`
    FOREIGN KEY (`electronicGadgetsID`)
    REFERENCES `CSCI5308_8_DEVINT`.`Product` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cat__ID`
    FOREIGN KEY (`categoryID`)
    REFERENCES `CSCI5308_8_DEVINT`.`Category` (`categoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 71;

CREATE INDEX `cat_Id_idx` ON `CSCI5308_8_DEVINT`.`Electronic_Gadgets` (`categoryID` ASC) INVISIBLE;


-- -----------------------------------------------------
-- Table `CSCI5308_8_DEVINT`.`Books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CSCI5308_8_DEVINT`.`Books` (
  `booksID` INT NOT NULL,
  `catid` INT NOT NULL,
  `author` VARCHAR(45) NULL,
  `year_of_public` YEAR(4) NOT NULL,
  `condition` VARCHAR(45) NULL,
  PRIMARY KEY (`booksID`),
  CONSTRAINT `idbooks`
    FOREIGN KEY (`booksID`)
    REFERENCES `CSCI5308_8_DEVINT`.`Product` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idcat`
    FOREIGN KEY (`catid`)
    REFERENCES `CSCI5308_8_DEVINT`.`Category` (`categoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 61;

CREATE INDEX `catid_idx` ON `CSCI5308_8_DEVINT`.`Books` (`catid` ASC) INVISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
