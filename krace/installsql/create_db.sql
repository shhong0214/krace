CREATE DATABASE cam DEFAULT CHARACTER SET utf8 ;

CREATE USER 'kra'@'%' IDENTIFIED BY 'kra';

GRANT ALL PRIVILEGES ON *.* TO 'kra'@'%';
