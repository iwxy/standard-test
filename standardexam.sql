/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : standardexam

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-20 19:58:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `lesson`
-- ----------------------------
DROP TABLE IF EXISTS `lesson`;
CREATE TABLE `lesson` (
  `leid` int(2) NOT NULL,
  `lename` char(20) NOT NULL,
  PRIMARY KEY (`leid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lesson
-- ----------------------------
INSERT INTO `lesson` VALUES ('31', 'JAVA');
INSERT INTO `lesson` VALUES ('32', '数据结构');
INSERT INTO `lesson` VALUES ('33', '网页设计');

-- ----------------------------
-- Table structure for `score`
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `sid` int(2) NOT NULL,
  `leid` int(2) NOT NULL,
  `times` int(1) DEFAULT NULL,
  `score` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('21', '32', '0', '72');
INSERT INTO `score` VALUES ('22', '32', '0', '60');
INSERT INTO `score` VALUES ('21', '31', '3', '0');
INSERT INTO `score` VALUES ('22', '31', '0', '70');
INSERT INTO `score` VALUES ('22', '33', '0', '0');
INSERT INTO `score` VALUES ('21', '33', '0', '0');
INSERT INTO `score` VALUES ('25', '31', '0', '0');
INSERT INTO `score` VALUES ('25', '32', '0', '0');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sid` int(2) NOT NULL,
  `sname` char(10) NOT NULL,
  `spassword` int(3) NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('21', '张三', '123');
INSERT INTO `student` VALUES ('22', '李四', '123');
INSERT INTO `student` VALUES ('25', '25', '25');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `tid` int(2) NOT NULL,
  `tname` char(10) NOT NULL,
  `tpassword` int(3) NOT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('11', '罗丹', '123');

-- ----------------------------
-- Table structure for `test`
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `leid` int(2) NOT NULL,
  `istest` int(1) DEFAULT NULL,
  `num` int(3) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `times` int(1) DEFAULT NULL,
  `file` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`leid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('31', '1', '10', '10', '3', 'G:\\eclipse-workspace\\standardtest\\题.xls');
INSERT INTO `test` VALUES ('32', '0', '0', '0', '0', '');
INSERT INTO `test` VALUES ('33', '0', '0', '0', '0', null);
DROP TRIGGER IF EXISTS `addlesson`;
DELIMITER ;;
CREATE TRIGGER `addlesson` AFTER INSERT ON `lesson` FOR EACH ROW insert test
values(new.leid,0,0,0,0,' ')
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `dellesson`;
DELIMITER ;;
CREATE TRIGGER `dellesson` AFTER DELETE ON `lesson` FOR EACH ROW delete from test where leid=old.leid
;;
DELIMITER ;
