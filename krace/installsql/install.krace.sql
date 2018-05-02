DROP TABLE IF EXISTS `kra`.`kra_member`;
DROP TABLE IF EXISTS `kra`.`horseowner`;
DROP TABLE IF EXISTS `kra`.`horseowner_own`;
DROP TABLE IF EXISTS `kra`.`horseowner_victoryrace`;

CREATE TABLE `kra`.`kra_member` (
	`memberid` VARCHAR(50) NOT NULL ,
	`membername` VARCHAR(50) NOT NULL ,
	`membertype` CHAR(1) NOT NULL ,
	`password` VARCHAR(100) NOT NULL ,
	`passwordEncoding` CHAR(1),
	`createdate` DATETIME,
	`modifydate` DATETIME,
  	`deleted` CHAR(1) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`memberid`) 
);	

CREATE TABLE `kra`.`horseowner` (
	`id` VARCHAR(10) NOT NULL ,			
	`name` VARCHAR(50) NOT NULL ,
	`addhorsecnt` INT,
	`delhorsecnt` INT,
	`totalhorsecnt` INT,
	`reddate` DATETIME,
	`newrecord` VARCHAR(50),
	`newmoney` VARCHAR(50),
	`totalrecord` VARCHAR(50),
	`totalmoney` VARCHAR(50),
	`clothcolor` VARCHAR(50),
	`meet` VARCHAR(5),
	PRIMARY KEY (`id`)
);

CREATE TABLE `kra`.`horseowner_own` (
	`horseownerid` VARCHAR(10) NOT NULL ,	
	`horseid` VARCHAR(10) NOT NULL ,	
	`horsename` VARCHAR(50),
	`trainername` VARCHAR(50),
	`period` VARCHAR(50),
	`etc` VARCHAR(100),
	PRIMARY KEY (`horseownerid`)
);

CREATE TABLE `kra`.`horseowner_victory` (
	`horseownerid` VARCHAR(10) NOT NULL ,	
	`racedate` VARCHAR(20),	
	`round` VARCHAR(10),
	`racename` VARCHAR(50),
	`horsename` VARCHAR(50),
	PRIMARY KEY (`horseownerid`)
);


