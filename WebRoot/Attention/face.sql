/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : face

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-02-18 15:29:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'hongcha');
INSERT INTO `product` VALUES ('2', 'kele');

-- ----------------------------
-- Table structure for recommand
-- ----------------------------
DROP TABLE IF EXISTS `recommand`;
CREATE TABLE `recommand` (
  `productid` int(11) NOT NULL,
  `w1` double NOT NULL,
  `w2` double NOT NULL,
  `w3` double NOT NULL,
  `w4` double NOT NULL,
  `w5` double NOT NULL,
  `w6` double NOT NULL,
  `w7` double NOT NULL,
  PRIMARY KEY (`productid`),
  CONSTRAINT `recommand_ibfk_1` FOREIGN KEY (`productid`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of recommand
-- ----------------------------
INSERT INTO `recommand` VALUES ('1', '0.24260075288869332', '0.3731989363336406', '7.794041085630237', '-0.0044091323788589334', '-0.026175413166935186', '-0.12813065736477655', '0.3731989363336406');
INSERT INTO `recommand` VALUES ('2', '0.05', '-0.5', '3.4', '1', '0.3', '0.04', '3');
