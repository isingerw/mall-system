/*
 Navicat Premium Data Transfer

 Source Server         : MySQL-8.0.22
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : mydb

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 21/06/2021 23:15:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `bookid` int NOT NULL,
  `bookname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `bookauthor` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `bookprice` decimal(10, 2) NOT NULL,
  `bookremark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`bookid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book
-- ----------------------------

-- ----------------------------
-- Table structure for deptment
-- ----------------------------
DROP TABLE IF EXISTS `deptment`;
CREATE TABLE `deptment`  (
  `deptid` int NOT NULL AUTO_INCREMENT,
  `deptname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `deptinfo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deptloc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`deptid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of deptment
-- ----------------------------
INSERT INTO `deptment` VALUES (1, '开发部门', '开发产品', '厦门');
INSERT INTO `deptment` VALUES (2, '人力资源', '人事管理', '北京');
INSERT INTO `deptment` VALUES (3, '秘书部', '秘书', '北京');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `empid` int NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `empsfz` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号码',
  `empname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `salary` decimal(10, 2) NULL DEFAULT NULL,
  `dept` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`empid`) USING BTREE,
  UNIQUE INDEX `sfz_unique`(`empsfz`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 666 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (1, '1111116', 'test1', '男', 124456.00, '开发部');
INSERT INTO `emp` VALUES (2, '1111117', 'test2', '男', 1.00, '测试部');
INSERT INTO `emp` VALUES (4, '11111111', '陈鑫', '女', 6.00, '秘书部');
INSERT INTO `emp` VALUES (5, '22222222', '家璇', '女', 7.00, NULL);
INSERT INTO `emp` VALUES (666, '1111119', '水文', '女', 1.00, '');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `empid` int NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `empsfz` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号码',
  `empname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `salary` decimal(10, 2) NULL DEFAULT NULL,
  `deptid` int NULL DEFAULT NULL,
  PRIMARY KEY (`empid`) USING BTREE,
  UNIQUE INDEX `sfz_unique`(`empsfz`) USING BTREE,
  INDEX `fk_deptid`(`deptid`) USING BTREE,
  CONSTRAINT `fk_deptid` FOREIGN KEY (`deptid`) REFERENCES `deptment` (`deptid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 678 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '1111116', '张三疯', '男', 124456.00, 1);
INSERT INTO `employee` VALUES (2, '1111117', 'test2', '男', 1.00, 2);
INSERT INTO `employee` VALUES (4, '11111111', '陈鑫', '女', 6.00, 3);
INSERT INTO `employee` VALUES (5, '22222222', '家璇', '女', 7.00, 2);
INSERT INTO `employee` VALUES (666, '1111119', '诸葛亮', '女', 1.00, 1);
INSERT INTO `employee` VALUES (667, '3333333', '法天', '女', 10000.00, 1);
INSERT INTO `employee` VALUES (669, '99999999', '九九', '男', 1.00, 1);
INSERT INTO `employee` VALUES (670, '7777777', '琪琪', '女', 1.00, 1);
INSERT INTO `employee` VALUES (671, '123123', '狗蛋', '男', 5500.00, 1);
INSERT INTO `employee` VALUES (672, '12', '张飞', '男', 10000.00, 2);
INSERT INTO `employee` VALUES (673, '132425', '大哥刘备', '男', 50000.00, 1);
INSERT INTO `employee` VALUES (674, '1235462', '二弟关羽', '男', 30000.00, 2);
INSERT INTO `employee` VALUES (675, '895212', '貂蝉', '女', 100000.00, 1);
INSERT INTO `employee` VALUES (678, '2123123', '四弟阳晨', '男', 1.00, 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` int NOT NULL AUTO_INCREMENT,
  `sname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sage` int NOT NULL,
  `sbirthdate` date NULL DEFAULT NULL,
  `saddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
