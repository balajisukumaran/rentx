-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema RentX
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema RentX
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `RentX` DEFAULT CHARACTER SET utf8 ;
USE `RentX` ;

-- -----------------------------------------------------
-- Table `RentX`.`Orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Orders` (
  `orderID` INT NOT NULL,
  `customerID` INT NULL,
  `providerID` INT NULL,
  `orderDate` DATE NULL,
  `deliveryDate` DATE NULL,
  `totalCost` INT NULL,
  PRIMARY KEY (`orderID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`Area`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Area` (
  `areaID` CHAR(6) NOT NULL,
  `area_name` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  PRIMARY KEY (`areaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Category` (
  `categoryID` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`categoryID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Product` (
  `productID` INT NOT NULL,
  `Description` VARCHAR(45) NOT NULL,
  `Price` VARCHAR(45) NULL,
  `categoryID` INT NULL,
  `areaID` CHAR(6) NULL,
  PRIMARY KEY (`productID`),
  INDEX `areaID_idx` (`areaID` ASC) VISIBLE,
  INDEX `categoryID_idx` (`categoryID` ASC) VISIBLE,
  CONSTRAINT `areaID`
    FOREIGN KEY (`areaID`)
    REFERENCES `RentX`.`Area` (`areaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `caID`
    FOREIGN KEY (`categoryID`)
    REFERENCES `RentX`.`Category` (`categoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`Images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Images` (
  `imageID` INT NOT NULL,
  `idProduct` INT NOT NULL,
  `Image` LONGBLOB NOT NULL,
  PRIMARY KEY (`imageID`),
  INDEX `idProduct_idx` (`idProduct` ASC) INVISIBLE,
  CONSTRAINT `idProduct`
    FOREIGN KEY (`idProduct`)
    REFERENCES `RentX`.`Product` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`Credentials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Credentials` (
  `phone` CHAR(15) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`phone`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`User` (
  `userID` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `token` VARCHAR(115) NOT NULL,
  `reset_token` VARCHAR(45),
  `phone` CHAR(15) NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `pref_language` VARCHAR(105) NULL,
  `joining_date` DATE NULL,
  `privacy` ENUM("enabled", "disabled") NULL,
  `status` ENUM("blocked", "unknown", "verified") NULL,
  `area_ID` CHAR(6) NOT NULL,
  PRIMARY KEY (`userID`, `phone`),
  INDEX `phone_idx` (`phone` ASC) INVISIBLE,
  INDEX `area_ID_idx` (`area_ID` ASC) INVISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`Purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Purchase` (
  `productID` INT NOT NULL,
  `buyer_id` INT NOT NULL,
  `seller_id` INT NOT NULL,
  `date_of_sale` VARCHAR(45) NULL,
  PRIMARY KEY (`buyer_id`, `productID`, `seller_id`),
  INDEX `seller_id_idx` (`seller_id` ASC) INVISIBLE,
  CONSTRAINT `buyer_id`
    FOREIGN KEY (`buyer_id`)
    REFERENCES `RentX`.`User` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `seller_id`
    FOREIGN KEY (`seller_id`)
    REFERENCES `RentX`.`User` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`Adminstrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Adminstrator` (
  `phone` CHAR(15) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `date_of_joining` DATE NULL,
  `role` VARCHAR(25) NULL,
  PRIMARY KEY (`phone`, `first_name`),
  CONSTRAINT `phoneNo`
    FOREIGN KEY (`phone`)
    REFERENCES `RentX`.`Credentials` (`phone`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`Message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Message` (
  `sender_id` INT NOT NULL,
  `receiver_id` INT NOT NULL,
  `message` VARCHAR(45) NULL,
  `timestamp` DATETIME NOT NULL,
  PRIMARY KEY (`sender_id`, `receiver_id`),
  INDEX `receiver_id_idx` (`receiver_id` ASC) VISIBLE,
  CONSTRAINT `sender_id`
    FOREIGN KEY (`sender_id`)
    REFERENCES `RentX`.`User` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `receiver_id`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `RentX`.`User` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`Furniture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Furniture` (
  `furnitureID` INT NOT NULL,
  `cat_id` INT NOT NULL,
  `manufacturer` VARCHAR(45) NULL,
  `Furniture_Type` VARCHAR(45) NOT NULL,
  `Year_of_Purchase` YEAR(4) NOT NULL,
  `condition` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`furnitureID`),
  INDEX `cat_id_idx` (`cat_id` ASC) INVISIBLE,
  CONSTRAINT `cat_id`
    FOREIGN KEY (`cat_id`)
    REFERENCES `RentX`.`Category` (`categoryID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `idFurniture`
    FOREIGN KEY (`furnitureID`)
    REFERENCES `RentX`.`Product` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`Kitchen_Appliances`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Kitchen_Appliances` (
  `kitchenAppliancesID` INT NOT NULL,
  `cat_id` INT NOT NULL,
  `Appliance_Type` VARCHAR(45) NULL,
  `manufacturer` VARCHAR(45) NULL,
  `Year_Of_Purchase` YEAR(4) NULL,
  `model_name` VARCHAR(45) NULL,
  PRIMARY KEY (`kitchenAppliancesID`),
  INDEX `catid_idx` (`cat_id` ASC) INVISIBLE,
  CONSTRAINT `idKitchen_Appliances`
    FOREIGN KEY (`kitchenAppliancesID`)
    REFERENCES `RentX`.`Product` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `catid`
    FOREIGN KEY (`cat_id`)
    REFERENCES `RentX`.`Category` (`categoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`Electronic_Gadgets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Electronic_Gadgets` (
  `electronicGadgetsID` INT NOT NULL,
  `categoryID` INT NOT NULL,
  `manufacturer` VARCHAR(45) NULL,
  `year_of_purchase` YEAR(4) NOT NULL,
  `model_name` VARCHAR(45) NOT NULL,
  `Gadget_Type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`electronicGadgetsID`),
  INDEX `cat_Id_idx` (`categoryID` ASC) INVISIBLE,
  CONSTRAINT `idElectronic_Gadgets`
    FOREIGN KEY (`electronicGadgetsID`)
    REFERENCES `RentX`.`Product` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `cat__ID`
    FOREIGN KEY (`categoryID`)
    REFERENCES `RentX`.`Category` (`categoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`Books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Books` (
  `booksID` INT NOT NULL,
  `catid` INT NOT NULL,
  `author` VARCHAR(45) NULL,
  `year_of_public` YEAR(4) NOT NULL,
  `condition` VARCHAR(45) NULL,
  PRIMARY KEY (`booksID`),
  INDEX `catid_idx` (`catid` ASC) INVISIBLE,
  CONSTRAINT `idbooks`
    FOREIGN KEY (`booksID`)
    REFERENCES `RentX`.`Product` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idcat`
    FOREIGN KEY (`catid`)
    REFERENCES `RentX`.`Category` (`categoryID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RentX`.`Advertisement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `RentX`.`Advertisement` (
  `advertisementID` INT NOT NULL,
  `verification_status` ENUM('unknown', 'rejected', 'verified') NOT NULL,
  `adv_title` VARCHAR(45) NULL,
  `is_active` ENUM('y', 'n') NULL,
  `post_date` DATETIME NULL,
  `expiry_date` DATETIME NULL,
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`advertisementID`),
  INDEX `user_id_idx` (`user_id` ASC) INVISIBLE,
  INDEX `product_id_idx` (`product_id` ASC) INVISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `RentX`.`User` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `RentX`.`Product` (`productID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
