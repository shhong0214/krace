DROP TABLE IF EXISTS `kra`.`kra_member`;
DROP TABLE IF EXISTS `kra`.`horseowner`;

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
	`newrecord` VARCHAR(30),
	`newmoney` VARCHAR(50),
	`totalrecord` VARCHAR(30),
	`totalmoney` VARCHAR(50),
	`clothcolor` VARCHAR(50),
	`meet` VARCHAR(5)
);
