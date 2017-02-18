/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : face

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-02-18 17:24:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `Type` varchar(100) DEFAULT NULL,
  `SalesForMoon` int(11) DEFAULT NULL,
  `CommentCount` int(11) DEFAULT NULL,
  `DisNegativeCommentRate` float DEFAULT NULL,
  `Collection` int(11) DEFAULT NULL,
  `Pride` float DEFAULT NULL,
  `TBURL` text,
  `JDURL` text,
  `ImageURL` text,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '特价清仓包邮19.9超值福袋女装秋冬毛呢外套上衣潮流 2-6件随机发', '女装', '12027', '52', '98.07', '22088', '18.9', 'http://dwz.cn/5kgv40', '', '1');
INSERT INTO `product` VALUES ('2', '米川春季白衬衫女装长袖职业工作正装宽松工装打底V领衬衣韩范OL', '女装', '11025', '131306', '100', '128617', '23', 'http://dwz.cn/5kgZAA', 'http://dwz.cn/5kh12n', '2');
INSERT INTO `product` VALUES ('3', '秋冬新款韩版女装宽松显瘦百搭条纹上衣高领长袖t恤女学生打底衫', '女装', '7847', '81501', '99.79', '82096', '18.6', 'http://dwz.cn/5khkbQ', 'http://dwz.cn/5khlsi', '3');
INSERT INTO `product` VALUES ('4', '欧比森保暖白衬衫男士长袖修身韩版纯色加绒加厚商务衬衣男装寸衫', '男装', '11914', '273385', '100', '112126', '19.9', 'http://dwz.cn/5khxm0', 'http://dwz.cn/5khE3S', '4');
INSERT INTO `product` VALUES ('5', '男装NIAN JEEP牛仔裤男士春季款弹力宽松直筒大码商务休闲长裤子', '男装', '9875', '223808', '100', '152450', '83', 'http://dwz.cn/5khIEx', '', '5');
INSERT INTO `product` VALUES ('6', '复古眼镜框韩版平光镜女个性半框圆脸近视架男款简约眼睛框超轻潮', '近视眼镜', '14946', '99839', '100', '147839', '19', 'http://dwz.cn/5khRzz', '', '6');
INSERT INTO `product` VALUES ('7', '华为honor/荣耀 畅玩6X 全网通智能手机官方正品4G大内存旗舰手机', '数码', '86517', '331690', '100', '506398', '1299', 'http://dwz.cn/5kixJg', 'http://dwz.cn/4nHf7a', '7');

-- ----------------------------
-- Table structure for `recommand`
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
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recommand
-- ----------------------------
