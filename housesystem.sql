/*
MySQL Data Transfer
Source Host: localhost
Source Database: housesystem
Target Host: localhost
Target Database: housesystem
Date: 2016/10/23 22:55:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `username` char(6) NOT NULL,
  `password` char(6) NOT NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_agency
-- ----------------------------
DROP TABLE IF EXISTS `t_agency`;
CREATE TABLE `t_agency` (
  `aid` int(4) NOT NULL auto_increment,
  `username` char(6) NOT NULL,
  `password` char(6) NOT NULL,
  `aname` varchar(10) NOT NULL,
  `asex` char(4) NOT NULL,
  PRIMARY KEY  (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `cid` int(4) NOT NULL auto_increment,
  `ctel` char(13) NOT NULL,
  `csex` varchar(4) NOT NULL,
  `cname` varchar(10) NOT NULL,
  `cage` int(4) NOT NULL,
  `aid` int(4) NOT NULL,
  PRIMARY KEY  (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_house
-- ----------------------------
DROP TABLE IF EXISTS `t_house`;
CREATE TABLE `t_house` (
  `hid` int(4) NOT NULL,
  `cid` int(4) NOT NULL,
  `harea` int(4) NOT NULL,
  `hadd` varchar(100) NOT NULL,
  `hspend` int(4) NOT NULL,
  `hstate` char(4) NOT NULL,
  `hpoto` varchar(100) NOT NULL,
  PRIMARY KEY  (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_inrent
-- ----------------------------
DROP TABLE IF EXISTS `t_inrent`;
CREATE TABLE `t_inrent` (
  `hid` int(4) NOT NULL,
  `cid` int(4) NOT NULL,
  PRIMARY KEY  (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_outrent
-- ----------------------------
DROP TABLE IF EXISTS `t_outrent`;
CREATE TABLE `t_outrent` (
  `hid` int(4) NOT NULL,
  `cid` int(4) NOT NULL,
  `tiem` datetime NOT NULL,
  `deposit` int(4) NOT NULL,
  PRIMARY KEY  (`hid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_admin` VALUES ('admin', '1234');
INSERT INTO `t_agency` VALUES ('2', 'aaa', '222', '李四', '女');
INSERT INTO `t_agency` VALUES ('3', 'ccc', '333', '', '男');
