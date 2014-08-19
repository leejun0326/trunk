/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50508
Source Host           : localhost:3306
Source Database       : spring-data-jpa

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2014-01-12 15:54:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'zhouhc', '123456');
INSERT INTO `t_user` VALUES ('2', '叶花仙', '1234');
INSERT INTO `t_user` VALUES ('3', 'zhc', '234567');
INSERT INTO `t_user` VALUES ('6', 'zhouhc6', '12394');
INSERT INTO `t_user` VALUES ('7', 'zhc1136', '234567');
