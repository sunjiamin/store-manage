/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : storage

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 21/10/2018 20:49:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jhib4legehrm4yscx9t3lirqi` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
BEGIN;
INSERT INTO `t_customer` VALUES ('64502401859588096', '张三', 1, '18212121211', '11111111@qq.com', '虹桥路11号', NULL, NULL, '2018-10-16 14:01:52', 0, NULL, NULL, '121');
INSERT INTO `t_customer` VALUES ('64502538610675712', '李四', 1, '18212121111', 'qqq@qq.cc', '胡青皮', NULL, NULL, '2018-10-16 14:02:25', 0, NULL, NULL, 'undefined');
COMMIT;

-- ----------------------------
-- Table structure for t_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionary`;
CREATE TABLE `t_dictionary` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'id',
  `type` varchar(30) DEFAULT NULL COMMENT '类型',
  `type_name` varchar(30) DEFAULT NULL COMMENT '类型名称',
  `code` int(11) DEFAULT NULL COMMENT '代码',
  `value` varchar(20) DEFAULT NULL COMMENT '值',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) DEFAULT NULL COMMENT '数据状态： 0：准备 1：有效  2：冻结  3：作废',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `sort` int(11) DEFAULT NULL COMMENT '排序值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `t_dictionary` VALUES ('1', 'unit', '配件单位', 0, '件', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dictionary` VALUES ('10', 'unit', '配件单位', 9, '桶', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dictionary` VALUES ('2', 'unit', '配件单位', 1, '个', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dictionary` VALUES ('3', 'unit', '配件单位', 2, '壶', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dictionary` VALUES ('4', 'unit', '配件单位', 3, '对', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dictionary` VALUES ('5', 'unit', '配件单位', 4, '套', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dictionary` VALUES ('6', 'unit', '配件单位', 5, '包', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dictionary` VALUES ('7', 'unit', '配件单位', 6, '粒', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dictionary` VALUES ('8', 'unit', '配件单位', 7, '条', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dictionary` VALUES ('9', 'unit', '配件单位', 8, '片', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_input_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `t_input_warehouse`;
CREATE TABLE `t_input_warehouse` (
  `id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT 'id',
  `warehouse_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '入库ID',
  `operuser_Id` bigint(20) DEFAULT NULL COMMENT '经办人id',
  `department` bigint(20) DEFAULT NULL COMMENT '出库部门',
  `total_amount` decimal(10,3) DEFAULT NULL COMMENT '出库总金额',
  `remark` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) DEFAULT NULL COMMENT '数据状态： 0：准备 1：有效  2：冻结  3：作废',
  `create_by` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `input_no` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '入库编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_input_warehouse
-- ----------------------------
BEGIN;
INSERT INTO `t_input_warehouse` VALUES ('65182174512418816', NULL, NULL, NULL, NULL, NULL, 0, NULL, '2018-10-18 11:03:06', NULL, NULL, '65182189913903104');
COMMIT;

-- ----------------------------
-- Table structure for t_input_warehouse_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_input_warehouse_detail`;
CREATE TABLE `t_input_warehouse_detail` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `input_warehouse_id` bigint(20) DEFAULT NULL COMMENT '入库id',
  `product_id` varchar(255) DEFAULT NULL COMMENT '入库商品ID',
  `num` int(11) DEFAULT NULL COMMENT '入库数量',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `price` decimal(20,3) DEFAULT NULL COMMENT '入库单价',
  `amount` decimal(20,3) DEFAULT NULL COMMENT '入库总价',
  `warehouse_id` bigint(20) DEFAULT NULL COMMENT '仓库ID',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) DEFAULT NULL COMMENT '数据状态： 0：准备 1：有效  2：冻结  3：作废',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK20q1sobdcfnbwsjmuajwyqyrx` (`product_id`),
  CONSTRAINT `FK20q1sobdcfnbwsjmuajwyqyrx` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_input_warehouse_detail
-- ----------------------------
BEGIN;
INSERT INTO `t_input_warehouse_detail` VALUES ('62182119184542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-09-17 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182129884542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-09-16 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182139884542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-09-15 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182149884542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-09-14 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182159884542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-09-13 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182169884542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-09-12 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182189184542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-10-17 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182189284542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-10-16 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182189384542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-10-15 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182189484542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-10-14 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182189584542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-10-13 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182189684542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-10-12 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182189814542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-08-17 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182189824542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-08-16 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182189834542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-08-15 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182189844542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-08-14 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182189854542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-08-13 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('62182189864542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-08-12 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('65182189867765760', 65182174512418816, '62613285781901312', 100, '1', 55.000, 5500.000, NULL, NULL, 0, NULL, '2018-10-18 11:03:06', NULL, NULL);
INSERT INTO `t_input_warehouse_detail` VALUES ('65182189884542976', 65182174512418816, '62613122287931392', 30, '0', 500.000, 15000.000, NULL, NULL, 0, NULL, '2018-10-18 11:03:06', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `cost_time` int(11) DEFAULT NULL,
  `ip` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ip_info` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `request_param` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `request_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `request_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_log
-- ----------------------------
BEGIN;
INSERT INTO `t_log` VALUES ('18418451445977088', NULL, '2018-06-11 10:00:43', 0, NULL, '2018-06-11 10:00:43', 933, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('18436671640965120', NULL, '2018-06-11 11:13:07', 0, NULL, '2018-06-11 11:13:07', 409, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('18436807553191936', NULL, '2018-06-11 11:13:39', 0, NULL, '2018-06-11 11:13:39', 689, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('18436936968441856', NULL, '2018-06-11 11:14:11', 0, NULL, '2018-06-11 11:14:11', 1978, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('18437248206770176', NULL, '2018-06-11 11:15:31', 0, NULL, '2018-06-11 11:15:31', 7030, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('18437585131016192', NULL, '2018-06-11 11:16:46', 0, NULL, '2018-06-11 11:16:46', 2044, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('18437733043146752', NULL, '2018-06-11 11:17:24', 0, NULL, '2018-06-11 11:17:24', 4993, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('18438310733025280', NULL, '2018-06-11 11:19:40', 0, NULL, '2018-06-11 11:19:40', 3160, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('18438375396610048', NULL, '2018-06-11 11:19:58', 0, NULL, '2018-06-11 11:19:58', 5773, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('18442097023520768', NULL, '2018-06-11 11:34:43', 0, NULL, '2018-06-11 11:34:43', 3227, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"jojo\"}', 'POST', '/xboot/login', 'jojo');
INSERT INTO `t_log` VALUES ('18468445523808256', NULL, '2018-06-11 13:19:23', 0, NULL, '2018-06-11 13:19:23', 1841, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('18469468162232320', NULL, '2018-06-11 13:23:28', 0, NULL, '2018-06-11 13:23:28', 2230, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('18475099287982080', NULL, '2018-06-11 13:45:53', 0, NULL, '2018-06-11 13:45:53', 4998, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('18476319784308736', NULL, '2018-06-11 13:50:40', 0, NULL, '2018-06-11 13:50:40', 693, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('18876949665419264', NULL, '2018-06-12 16:22:40', 0, NULL, '2018-06-12 16:22:40', 3201, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('19200364775477248', NULL, '2018-06-13 13:47:49', 0, NULL, '2018-06-13 13:47:49', 3444, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('19227654821515264', NULL, '2018-06-13 15:36:14', 0, NULL, '2018-06-13 15:36:14', 3197, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"sunjiamin\"}', 'POST', '/xboot/login', 'sunjiamin');
INSERT INTO `t_log` VALUES ('19242852236136448', NULL, '2018-06-13 16:36:38', 0, NULL, '2018-06-13 16:36:38', 3595, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"sunjiamin\"}', 'POST', '/xboot/login', 'sunjiamin');
INSERT INTO `t_log` VALUES ('21331845224337408', NULL, '2018-06-19 10:57:33', 0, NULL, '2018-06-19 10:57:33', 3376, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22063507570692096', NULL, '2018-06-21 11:24:55', 0, NULL, '2018-06-21 11:24:55', 3442, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"sunjiamin\"}', 'POST', '/xboot/login', 'sunjiamin');
INSERT INTO `t_log` VALUES ('22064287019175936', NULL, '2018-06-21 11:28:00', 0, NULL, '2018-06-21 11:28:00', 2971, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22064398243729408', NULL, '2018-06-21 11:28:24', 0, NULL, '2018-06-21 11:28:24', 168, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22065576646021120', NULL, '2018-06-21 11:33:08', 0, NULL, '2018-06-21 11:33:08', 3174, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22065633982156800', NULL, '2018-06-21 11:33:21', 0, NULL, '2018-06-21 11:33:21', 3011, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22065666987134976', NULL, '2018-06-21 11:33:29', 0, NULL, '2018-06-21 11:33:29', 3010, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22065711861993472', NULL, '2018-06-21 11:33:37', 0, NULL, '2018-06-21 11:33:37', 176, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22066976734056448', NULL, '2018-06-21 11:38:41', 0, NULL, '2018-06-21 11:38:41', 3180, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22069862830444544', NULL, '2018-06-21 11:50:10', 0, NULL, '2018-06-21 11:50:10', 3190, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22070145341984768', NULL, '2018-06-21 11:51:17', 0, NULL, '2018-06-21 11:51:17', 3007, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22070175054434304', NULL, '2018-06-21 11:51:21', 0, NULL, '2018-06-21 11:51:21', 242, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22070408844939264', NULL, '2018-06-21 11:52:17', 0, NULL, '2018-06-21 11:52:17', 576, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22099706976931840', NULL, '2018-06-21 13:48:45', 0, NULL, '2018-06-21 13:48:45', 3192, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22099936787042304', NULL, '2018-06-21 13:49:40', 0, NULL, '2018-06-21 13:49:40', 3244, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22101172840042496', NULL, '2018-06-21 13:54:34', 0, NULL, '2018-06-21 13:54:34', 3246, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22102043510444032', NULL, '2018-06-21 13:58:02', 0, NULL, '2018-06-21 13:58:02', 3279, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22102671473250304', NULL, '2018-06-21 14:00:29', 0, NULL, '2018-06-21 14:00:29', 632, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22102952248348672', NULL, '2018-06-21 14:01:36', 0, NULL, '2018-06-21 14:01:36', 329, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22103011056685056', NULL, '2018-06-21 14:01:50', 0, NULL, '2018-06-21 14:01:50', 250, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22103157043630080', NULL, '2018-06-21 14:02:25', 0, NULL, '2018-06-21 14:02:25', 545, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22103200349818880', NULL, '2018-06-21 14:02:35', 0, NULL, '2018-06-21 14:02:35', 233, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22103239281348608', NULL, '2018-06-21 14:02:44', 0, NULL, '2018-06-21 14:02:44', 291, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22103284126846976', NULL, '2018-06-21 14:02:55', 0, NULL, '2018-06-21 14:02:55', 324, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22103343493025792', NULL, '2018-06-21 14:03:12', 0, NULL, '2018-06-21 14:03:12', 3012, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22103376351203328', NULL, '2018-06-21 14:03:17', 0, NULL, '2018-06-21 14:03:17', 170, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22103380012830720', NULL, '2018-06-21 14:03:18', 0, NULL, '2018-06-21 14:03:18', 157, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22103382781071360', NULL, '2018-06-21 14:03:18', 0, NULL, '2018-06-21 14:03:18', 281, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22104110442483712', NULL, '2018-06-21 14:06:15', 0, NULL, '2018-06-21 14:06:15', 3046, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22104163714338816', NULL, '2018-06-21 14:06:24', 0, NULL, '2018-06-21 14:06:24', 136, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22112148234702848', NULL, '2018-06-21 14:38:11', 0, NULL, '2018-06-21 14:38:11', 3208, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22112597243334656', NULL, '2018-06-21 14:39:58', 0, NULL, '2018-06-21 14:39:58', 3019, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22112634367119360', NULL, '2018-06-21 14:40:04', 0, NULL, '2018-06-21 14:40:04', 205, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22112726314651648', NULL, '2018-06-21 14:40:29', 0, NULL, '2018-06-21 14:40:29', 2728, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22112730865471488', NULL, '2018-06-21 14:40:29', 0, NULL, '2018-06-21 14:40:29', 1644, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22112772808511488', NULL, '2018-06-21 14:40:37', 0, NULL, '2018-06-21 14:40:37', 521, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22112873727660032', NULL, '2018-06-21 14:41:01', 0, NULL, '2018-06-21 14:41:01', 454, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22113771786866688', NULL, '2018-06-21 14:44:38', 0, NULL, '2018-06-21 14:44:38', 2725, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22114273610174464', NULL, '2018-06-21 14:46:38', 0, NULL, '2018-06-21 14:46:38', 3020, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22116718331564032', NULL, '2018-06-21 14:56:21', 0, NULL, '2018-06-21 14:56:21', 3193, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22116764254998528', NULL, '2018-06-21 14:56:29', 0, NULL, '2018-06-21 14:56:29', 246, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22116822912339968', NULL, '2018-06-21 14:56:46', 0, NULL, '2018-06-21 14:56:46', 3019, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22132686793478144', NULL, '2018-06-21 15:59:46', 0, NULL, '2018-06-21 15:59:46', 940, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22132893119680512', NULL, '2018-06-21 16:00:35', 0, NULL, '2018-06-21 16:00:35', 1272, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22133120065081344', NULL, '2018-06-21 16:01:28', 0, NULL, '2018-06-21 16:01:28', 253, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22134767868710912', NULL, '2018-06-21 16:08:01', 0, NULL, '2018-06-21 16:08:01', 572, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22134820280733696', NULL, '2018-06-21 16:08:14', 0, NULL, '2018-06-21 16:08:14', 333, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22136205210554368', NULL, '2018-06-21 16:13:44', 0, NULL, '2018-06-21 16:13:44', 410, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22136281018404864', NULL, '2018-06-21 16:14:02', 0, NULL, '2018-06-21 16:14:02', 212, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22143226945212416', NULL, '2018-06-21 16:41:41', 0, NULL, '2018-06-21 16:41:41', 4366, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22143648741199872', NULL, '2018-06-21 16:43:21', 0, NULL, '2018-06-21 16:43:21', 60766, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22143714256228352', NULL, '2018-06-21 16:43:37', 0, NULL, '2018-06-21 16:43:37', 3068, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22143780467511296', NULL, '2018-06-21 16:43:53', 0, NULL, '2018-06-21 16:43:53', 3013, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22144224833048576', NULL, '2018-06-21 16:45:39', 0, NULL, '2018-06-21 16:45:39', 3228, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22144410804293632', NULL, '2018-06-21 16:46:23', 0, NULL, '2018-06-21 16:46:23', 3013, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22144554966716416', NULL, '2018-06-21 16:46:57', 0, NULL, '2018-06-21 16:46:57', 3014, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22144701234679808', NULL, '2018-06-21 16:47:32', 0, NULL, '2018-06-21 16:47:32', 3015, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22144955241730048', NULL, '2018-06-21 16:48:33', 0, NULL, '2018-06-21 16:48:33', 3017, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22145355109896192', NULL, '2018-06-21 16:50:08', 0, NULL, '2018-06-21 16:50:08', 3166, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22145435112050688', NULL, '2018-06-21 16:50:27', 0, NULL, '2018-06-21 16:50:27', 3143, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22145820589559808', NULL, '2018-06-21 16:51:59', 0, NULL, '2018-06-21 16:51:59', 3029, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22399447749103616', NULL, '2018-06-22 09:39:46', 0, NULL, '2018-06-22 09:39:46', 656, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22399532759257088', NULL, '2018-06-22 09:40:06', 0, NULL, '2018-06-22 09:40:06', 337, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22399657585938432', NULL, '2018-06-22 09:40:36', 0, NULL, '2018-06-22 09:40:36', 253, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22410468144451584', NULL, '2018-06-22 10:23:36', 0, NULL, '2018-06-22 10:23:36', 3294, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22410739557863424', NULL, '2018-06-22 10:24:41', 0, NULL, '2018-06-22 10:24:41', 3024, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22411201480757248', NULL, '2018-06-22 10:26:31', 0, NULL, '2018-06-22 10:26:31', 3260, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22411657984610304', NULL, '2018-06-22 10:28:20', 0, NULL, '2018-06-22 10:28:20', 3026, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22418576937848832', NULL, '2018-06-22 10:55:47', 0, NULL, '2018-06-22 10:55:47', 639, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22419665040969728', NULL, '2018-06-22 11:00:06', 0, NULL, '2018-06-22 11:00:06', 670, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22423244527112192', NULL, '2018-06-22 11:14:20', 0, NULL, '2018-06-22 11:14:20', 569, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22423756358029312', NULL, '2018-06-22 11:16:22', 0, NULL, '2018-06-22 11:16:22', 629, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22424093974335488', NULL, '2018-06-22 11:17:42', 0, NULL, '2018-06-22 11:17:42', 341, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22424241462841344', NULL, '2018-06-22 11:18:17', 0, NULL, '2018-06-22 11:18:17', 339, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22424477589573632', NULL, '2018-06-22 11:19:13', 0, NULL, '2018-06-22 11:19:13', 306, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22424729604329472', NULL, '2018-06-22 11:20:14', 0, NULL, '2018-06-22 11:20:14', 514, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22425261798592512', NULL, '2018-06-22 11:22:21', 0, NULL, '2018-06-22 11:22:21', 606, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('22432351464198144', NULL, '2018-06-22 11:51:10', 0, NULL, '2018-06-22 11:51:10', 25032, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22434569961607168', NULL, '2018-06-22 11:59:20', 0, NULL, '2018-06-22 11:59:20', 5439, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"test\"}', 'POST', '/xboot/login', 'test');
INSERT INTO `t_log` VALUES ('22434646818033664', NULL, '2018-06-22 11:59:38', 0, NULL, '2018-06-22 11:59:38', 328, '172.20.10.6', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('23877208514760704', NULL, '2018-06-26 11:31:55', 0, NULL, '2018-06-26 11:31:55', 3433, '10.80.4.253', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');
INSERT INTO `t_log` VALUES ('65548560669609984', NULL, '2018-10-19 11:18:59', 0, NULL, '2018-10-19 11:18:59', 3445, '10.68.21.199', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/store/login', 'admin');
INSERT INTO `t_log` VALUES ('65548613526228992', NULL, '2018-10-19 11:19:09', 0, NULL, '2018-10-19 11:19:09', 228, '10.68.21.199', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/store/login', 'admin');
INSERT INTO `t_log` VALUES ('65549817832542208', NULL, '2018-10-19 11:23:56', 0, NULL, '2018-10-19 11:23:56', 237, '10.68.21.199', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/store/login', 'admin');
INSERT INTO `t_log` VALUES ('65549864313819136', NULL, '2018-10-19 11:24:07', 0, NULL, '2018-10-19 11:24:07', 214, '10.68.21.199', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/store/login', 'admin');
INSERT INTO `t_log` VALUES ('65616176226504704', NULL, '2018-10-19 15:47:37', 0, NULL, '2018-10-19 15:47:37', 44571, '10.68.21.199', '未知', '新增配件', '{\"unit\":\"2\",\"productCode\":\"121\",\"productClass\":\"62612836962013184\",\"remark\":\"12\",\"productSpec\":\"11\",\"productName\":\"嘻嘻\"}', 'POST', '/store/product/add', 'admin');
INSERT INTO `t_log` VALUES ('65616208686223360', NULL, '2018-10-19 15:47:44', 0, NULL, '2018-10-19 15:47:44', 3685, '10.68.21.199', '未知', '新增配件', '{\"unit\":\"2\",\"productCode\":\"121\",\"productClass\":\"62612836962013184\",\"remark\":\"12\",\"productSpec\":\"11\",\"productName\":\"嘻嘻\"}', 'POST', '/store/product/add', 'admin');
INSERT INTO `t_log` VALUES ('65624216833101824', NULL, '2018-10-19 16:19:46', 0, NULL, '2018-10-19 16:19:46', 12433, '10.68.21.199', '未知', '删除出库员', '{\"ids\":\"65621640288931840\"}', 'DELETE', '/store/salePerson/delByIds', 'admin');
INSERT INTO `t_log` VALUES ('65624464934572032', NULL, '2018-10-19 16:20:33', 0, NULL, '2018-10-19 16:20:33', 257, '10.68.21.199', '未知', '添加出库员', '{\"sex\":\"1\",\"mobile\":\"18321248611\",\"remark\":\"1\",\"userName\":\"111\",\"email\":\"111\"}', 'POST', '/store/salePerson/add', 'admin');
INSERT INTO `t_log` VALUES ('65625646088327168', NULL, '2018-10-19 16:25:15', 0, NULL, '2018-10-19 16:25:15', 600, '10.68.21.199', '未知', '删除出库员', '{\"ids\":\"65624464770994176\"}', 'DELETE', '/store/salePerson/delByIds', 'admin');
INSERT INTO `t_log` VALUES ('66378812710981632', NULL, '2018-10-21 18:18:04', 0, NULL, '2018-10-21 18:18:04', 683, '192.168.1.103', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/store/login', 'admin');
INSERT INTO `t_log` VALUES ('66395961689116672', NULL, '2018-10-21 19:26:12', 0, NULL, '2018-10-21 19:26:12', 574, '192.168.1.103', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/store/login', 'admin');
INSERT INTO `t_log` VALUES ('66396323028406272', NULL, '2018-10-21 19:27:38', 0, NULL, '2018-10-21 19:27:38', 283, '192.168.1.103', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/store/login', 'admin');
INSERT INTO `t_log` VALUES ('66396700436074496', NULL, '2018-10-21 19:29:08', 0, NULL, '2018-10-21 19:29:08', 319, '192.168.1.103', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/store/login', 'admin');
INSERT INTO `t_log` VALUES ('66397048915628032', NULL, '2018-10-21 19:30:31', 0, NULL, '2018-10-21 19:30:31', 264, '192.168.1.103', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/store/login', 'admin');
INSERT INTO `t_log` VALUES ('66397186002259968', NULL, '2018-10-21 19:31:05', 0, NULL, '2018-10-21 19:31:05', 1658, '192.168.1.103', '未知', '登录系统', '{\"password\":\"你是看不见我的\",\"saveLogin\":\"true\",\"username\":\"admin\"}', 'POST', '/store/login', 'admin');
COMMIT;

-- ----------------------------
-- Table structure for t_output_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `t_output_warehouse`;
CREATE TABLE `t_output_warehouse` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `output_no` varchar(255) DEFAULT NULL COMMENT '出库编码',
  `warehouse_id` varchar(255) DEFAULT NULL COMMENT '仓库ID',
  `operuser_Id` varchar(255) DEFAULT NULL COMMENT '经办人id',
  `department` bigint(20) DEFAULT NULL COMMENT '出库部门',
  `total_amount` decimal(10,3) DEFAULT NULL COMMENT '出库总金额',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) DEFAULT NULL COMMENT '数据状态： 0：准备 1：有效  2：冻结  3：作废',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_output_warehouse
-- ----------------------------
BEGIN;
INSERT INTO `t_output_warehouse` VALUES ('65182584274948096', '65182584375611392', NULL, NULL, NULL, NULL, NULL, 0, NULL, '2018-10-18 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse` VALUES ('65229726221864960', '65229726255419392', NULL, NULL, NULL, NULL, NULL, 0, NULL, '2018-10-18 14:12:00', NULL, NULL);
INSERT INTO `t_output_warehouse` VALUES ('65229886154870784', '65229886171648002', NULL, NULL, NULL, NULL, NULL, 0, NULL, '2018-10-18 14:12:38', NULL, NULL);
INSERT INTO `t_output_warehouse` VALUES ('65231503994392576', '65231504011169793', NULL, NULL, NULL, NULL, NULL, 0, NULL, '2018-10-18 14:19:04', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_output_warehouse_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_output_warehouse_detail`;
CREATE TABLE `t_output_warehouse_detail` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `output_warehouse_id` varchar(255) DEFAULT NULL COMMENT '出库id',
  `product_id` varchar(255) DEFAULT NULL COMMENT '出库商品ID',
  `num` int(11) DEFAULT NULL COMMENT '出库数量',
  `unit` varchar(20) DEFAULT NULL COMMENT '单位',
  `price` decimal(20,3) DEFAULT NULL COMMENT '出库单价',
  `amount` decimal(20,3) DEFAULT NULL COMMENT '出库总价',
  `pay_status` varchar(1) DEFAULT NULL COMMENT '收款状态 1收款完成  2部分收款  3未收款',
  `surcharge` decimal(20,3) DEFAULT NULL COMMENT '附加费',
  `total_amount` decimal(20,3) DEFAULT NULL COMMENT '所有总价 附加费+出库总价',
  `nopay_amount` decimal(20,3) DEFAULT NULL COMMENT '未到账金额',
  `sale_person_id` varchar(255) DEFAULT NULL COMMENT '销售员ID',
  `customer_id` varchar(255) DEFAULT NULL COMMENT '客户id',
  `warehouse_id` varchar(255) DEFAULT NULL COMMENT '仓库ID',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) DEFAULT NULL COMMENT '数据状态： 0：准备 1：有效  2：冻结  3：作废',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_index` (`customer_id`),
  KEY `FKfk86h8kq0jfoaukvxwd2hx6wc` (`product_id`),
  KEY `FK4wkutyfuegl2s1maqo9b5p2x` (`sale_person_id`),
  CONSTRAINT `FK2e7at1agm1uod3ywkfd79idx7` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FK4wkutyfuegl2s1maqo9b5p2x` FOREIGN KEY (`sale_person_id`) REFERENCES `t_sale_person` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FKfk86h8kq0jfoaukvxwd2hx6wc` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_output_warehouse_detail
-- ----------------------------
BEGIN;
INSERT INTO `t_output_warehouse_detail` VALUES ('651821q4342056963', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-09-12 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182234146251262', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-09-11 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182514342056961', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-09-10 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182514346251265', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-09-13 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182524342056966', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-09-14 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182534346251267', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-09-15 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182544342056968', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-09-16 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182554346251269', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-09-17 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182564342056911', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-09-18 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182574342056961', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-08-10 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182581342056963', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-08-12 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182582346251265', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-08-13 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182583342056966', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-08-14 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584342056911', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-10-18 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584342056931', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-10-18 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584342056951', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-10-18 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584342056960', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-10-18 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584342056961', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-10-10 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584342056963', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-10-12 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584342056966', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-10-14 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584342056968', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-10-16 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584346251222', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-10-18 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584346251242', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-10-18 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584346251262', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-10-11 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584346251264', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-10-18 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584346251265', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-10-13 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584346251267', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-10-15 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584346251269', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-10-17 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182584347251262', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-10-18 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182585346251267', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-08-15 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182586342056968', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-08-16 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182587346251269', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-08-17 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65182588342056911', '65182584274948096', '62613122287931392', 1, '', 600.000, 600.000, '2', 20.000, 620.000, 100.000, '62639246141296640', '64502538610675712', NULL, '11', 0, NULL, '2018-08-18 11:04:40', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65229726251225088', '65229726221864960', '62613285781901312', 2, '', 100.000, 200.000, '1', 20.000, 220.000, 0.000, '65228993279823872', '64502401859588096', '', '0', 0, '', '2018-10-18 14:12:00', '', '2018-10-21 18:25:35');
INSERT INTO `t_output_warehouse_detail` VALUES ('65229886171648000', '65229886154870784', '62613285781901312', 200, '', 2.000, 400.000, '3', 20.000, 420.000, 420.000, '65229160313786368', '64502401859588096', '', '12', 0, '', '2018-10-18 14:12:38', '', '2018-10-18 14:13:04');
INSERT INTO `t_output_warehouse_detail` VALUES ('65231504006975488', '65231503994392576', '62613285781901312', 1, '', 200.000, 200.000, '1', 20.000, 220.000, 0.000, '65229160313786368', '64502401859588096', NULL, '1', 0, NULL, '2018-10-18 14:19:04', NULL, NULL);
INSERT INTO `t_output_warehouse_detail` VALUES ('65282584346251262', '65182584274948096', '62613285781901312', 1, '', 80.000, 80.000, '1', 10.000, 90.000, 0.000, '62639246141296640', '64502401859588096', NULL, '1', 0, NULL, '2018-08-11 11:04:40', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `button_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `component` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `parent_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_bin NOT NULL,
  `sort_order` decimal(10,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_permission` VALUES ('1', 'sjm', '2018-06-11 10:37:19', 0, 'sjm', NULL, NULL, 'Main', NULL, 'ios-gear', 1, 'sys', '', 'form', 1.00, 0, '系统管理', 0);
INSERT INTO `t_permission` VALUES ('2', 'sjm', '2018-06-11 10:53:47', 0, NULL, NULL, NULL, 'sys/user-manage/userManage', NULL, 'android-person', 2, 'user-manage', '1', 'user-manage', 1.10, 0, '用户管理', 0);
INSERT INTO `t_permission` VALUES ('3', '', NULL, 0, '', NULL, '', 'Main', '', 'locked', 1, 'access', '', 'access', 99.00, 0, '权限按钮测试页', 0);
INSERT INTO `t_permission` VALUES ('4', NULL, NULL, 0, NULL, NULL, NULL, 'sys/role-manage/roleManage', NULL, 'android-contacts', 2, 'role-manage', '1', 'role-manage', 1.20, 0, '角色管理', 0);
INSERT INTO `t_permission` VALUES ('5', NULL, '2018-06-11 14:22:33', 0, NULL, NULL, NULL, 'access/access', NULL, 'locked', 2, 'access_index', '3', 'index', 2.10, 0, '权限按钮测试页', 0);
INSERT INTO `t_permission` VALUES ('6', NULL, NULL, NULL, NULL, NULL, NULL, 'sys/menu-manage/menuManage', NULL, 'navicon-round', 2, 'menu-manage', '1', 'menu-manage', 1.30, 0, '菜单权限管理', 0);
INSERT INTO `t_permission` VALUES ('18486880530075648', '', '2018-06-11 14:32:37', 0, '', '2018-06-11 14:32:56', '', 'sys/log-manage/logManage', '', 'android-list', 2, 'log-manage', '1', 'log-manage', 4.00, 0, '日志管理', 0);
INSERT INTO `t_permission` VALUES ('18488812241948672', '', '2018-06-11 14:40:17', 0, '', '2018-06-13 14:34:08', '', 'Main', '', 'android-plane', 1, 'push-manager', '', 'push-manager', 0.00, 0, '推送管理', 0);
INSERT INTO `t_permission` VALUES ('18489515098247168', '', '2018-06-11 14:43:05', 0, '', '2018-06-13 14:29:23', '', 'push/onlineUser', '', 'android-contact', 2, 'online-users', '18488812241948672', 'push', 1.00, 0, '在线列表', 0);
INSERT INTO `t_permission` VALUES ('18496505560174592', NULL, '2018-06-11 15:10:52', 0, NULL, '2018-06-11 15:10:52', 'add', NULL, NULL, NULL, 3, NULL, '5', 'add', 1.00, 0, 'add', 1);
INSERT INTO `t_permission` VALUES ('18490836455002112', '', '2018-06-11 14:48:20', 0, '', '2018-06-13 14:37:17', '', 'push/messagelist', '', 'android-list', 2, 'push-list', '18488812241948672', 'push-list', 2.00, 0, '消息列表', 0);
INSERT INTO `t_permission` VALUES ('61989699186593792', '', NULL, 0, '', '2018-10-21 19:30:04', '', 'Main', '', 'locked', 1, 'product-manager', '', 'form', 2.00, 0, '配件管理', 0);
INSERT INTO `t_permission` VALUES ('61990171792379904', '', NULL, 0, '', '2018-10-21 19:30:12', '', 'product/product', '', 'navicon-round', 2, 'product-list', '61989699186593792', 'product-list', 1.00, 0, '配件列表', 0);
INSERT INTO `t_permission` VALUES ('61990514022420480', '', NULL, 0, '', '2018-10-21 19:30:22', '', 'product/productClass', '', 'android-list', 2, 'product-class', '61989699186593792', 'product-class', 2.00, 0, '配件类别', 0);
INSERT INTO `t_permission` VALUES ('62635125921288192', '', NULL, 0, '', '2018-10-19 16:25:44', '', 'product/salePerson', '', 'android-person', 2, 'sale-person', '61989699186593792', 'sale-person', 3.00, 0, '出货员管理', 0);
INSERT INTO `t_permission` VALUES ('62679369595752448', NULL, NULL, 0, NULL, NULL, NULL, 'Main', NULL, 'funnel', 1, 'input-output', NULL, 'input-output', 3.00, 0, '出入库管理', 0);
INSERT INTO `t_permission` VALUES ('62679694251659264', '', NULL, 0, '', '2018-10-21 19:24:12', '', 'stock/inputProduct', '', 'log-in', 2, 'input-product', '62679369595752448', 'input-product', 1.00, 0, '配件入库', 0);
INSERT INTO `t_permission` VALUES ('62679979216867328', '', NULL, 0, '', '2018-10-21 19:24:02', '', 'stock/outputProduct', '', 'log-out', 2, 'output-product', '62679369595752448', 'output-product', 2.00, 0, '配件出库', 0);
INSERT INTO `t_permission` VALUES ('62680236608720896', '', NULL, 0, '', '2018-10-21 19:23:17', '', 'stock/productStock', '', 'jet', 2, 'stock-product', '62679369595752448', 'stock-product', 3.00, 0, '配件库存', 0);
INSERT INTO `t_permission` VALUES ('63835554399129600', '', NULL, 0, '', '2018-10-21 19:29:04', '', 'stock/inputRecord', '', 'android-bulb', 2, 'input-record', '62679369595752448', 'input-record', 4.00, 0, '入库记录', 0);
INSERT INTO `t_permission` VALUES ('63835817528791040', '', NULL, 0, '', '2018-10-21 19:27:33', '', 'stock/outputRecord', '', 'magnet', 2, 'output-record', '62679369595752448', 'output-record', 5.00, 0, '出库记录', 0);
INSERT INTO `t_permission` VALUES ('64450005213122560', '', NULL, 0, '', '2018-10-21 19:30:59', '', 'product/customer', '', 'android-contacts', 2, 'customer-manager', '61989699186593792', 'customer-manager', 4.00, 0, '客户管理', 0);
INSERT INTO `t_permission` VALUES ('64915081665712128', '', NULL, 0, '', '2018-10-21 19:27:13', '', 'Main', '', 'ios-photos', 1, 'main', '', 'main', 4.00, 0, '统计中心', 0);
INSERT INTO `t_permission` VALUES ('64915362721828864', '', NULL, 0, '', '2018-10-21 19:25:57', '', 'census/profitCensus', '', 'podium', 2, 'profit-census', '64915081665712128', 'profit-census', 1.00, 0, '利润统计', 0);
INSERT INTO `t_permission` VALUES ('64915516266909696', '', NULL, 0, '', '2018-10-21 19:27:03', '', 'census/censusRatio', '', 'ios-pie', 2, 'censusRatio', '64915081665712128', 'censusRatio', 2.00, 0, '出货占比', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `product_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '商品编号',
  `product_spec` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '商品规格',
  `product_class` varchar(255) DEFAULT NULL COMMENT '商品类别ID',
  `supplier_id` varchar(255) DEFAULT NULL COMMENT '供应商ID',
  `brand_id` varchar(255) DEFAULT NULL COMMENT '品牌ID',
  `sort` bigint(20) DEFAULT NULL COMMENT '排序',
  `quanping` varchar(255) DEFAULT NULL COMMENT '名称全拼',
  `unit` varchar(3) DEFAULT NULL COMMENT '商品单位',
  `images` varchar(499) DEFAULT NULL COMMENT '商品图片URL',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) DEFAULT NULL COMMENT '数据状态： 0：准备 1：有效  2：冻结  3：作废',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `product_class_name` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk6lf1lceyay54e43x06blyjkd` (`product_id`),
  KEY `FKrk9ad3i6s1emtnqu8qle9cubx` (`product_class`),
  CONSTRAINT `FKrk9ad3i6s1emtnqu8qle9cubx` FOREIGN KEY (`product_class`) REFERENCES `t_product_class` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------
BEGIN;
INSERT INTO `t_product` VALUES ('62613122287931392', '轴承', 'ACC', '1234', '62614654035169280', '', '', NULL, '', '', '', '11', 0, '', '2018-10-11 08:54:33', '', '2018-10-17 09:50:20', NULL, NULL);
INSERT INTO `t_product` VALUES ('62613285781901312', '机油', 'DSSS', '1000-11222222', '62612836962013184', '', '', NULL, '', '', '', '11', 0, '', '2018-10-11 08:55:12', '', '2018-10-17 09:50:40', NULL, NULL);
INSERT INTO `t_product` VALUES ('63712748294377472', 'jojo', 'hhh', 'jjjkk1', '62614654035169280', '', '', NULL, '', '', '', 'lmo1111', 0, '', '2018-10-14 09:44:04', '', '2018-10-17 09:50:30', '航话', NULL);
INSERT INTO `t_product` VALUES ('64811427369586688', '12', '1', '12', '62612836962013184', NULL, NULL, NULL, NULL, '1', NULL, '1', 0, NULL, '2018-10-17 10:29:49', NULL, NULL, NULL, NULL);
INSERT INTO `t_product` VALUES ('65615989777108992', '嘻嘻', '121', '11', '62612836962013184', NULL, NULL, NULL, NULL, '2', NULL, '12', 0, NULL, '2018-10-19 15:46:52', NULL, '2018-10-19 15:46:52', NULL, NULL);
INSERT INTO `t_product` VALUES ('65616193712558080', '嘻嘻', '121', '11', '62612836962013184', NULL, NULL, NULL, NULL, '2', NULL, '12', 0, NULL, '2018-10-19 15:47:41', NULL, '2018-10-19 15:47:41', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_product_class
-- ----------------------------
DROP TABLE IF EXISTS `t_product_class`;
CREATE TABLE `t_product_class` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `class_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `class_code` varchar(255) DEFAULT NULL COMMENT '商品编号',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) DEFAULT NULL COMMENT '数据状态： 0：准备 1：有效  2：冻结  3：作废',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product_class
-- ----------------------------
BEGIN;
INSERT INTO `t_product_class` VALUES ('62612836962013184', '曼宝', 'BCC-SQ', 'aa', 0, '', '2018-10-11 08:53:25', '', '2018-10-11 09:11:29');
INSERT INTO `t_product_class` VALUES ('62614654035169280', '骐达', 'AAA-99', 'aa', 0, '', '2018-10-11 09:00:38', '', '2018-10-11 09:11:17');
INSERT INTO `t_product_class` VALUES ('65619886910476288', '121', '12', '12', 0, NULL, '2018-10-19 16:02:21', NULL, '2018-10-19 16:02:21');
INSERT INTO `t_product_class` VALUES ('66379815522930688', '零件', '1', '2', 0, NULL, '2018-10-21 18:22:02', NULL, '2018-10-21 18:22:02');
COMMIT;

-- ----------------------------
-- Table structure for t_product_stock
-- ----------------------------
DROP TABLE IF EXISTS `t_product_stock`;
CREATE TABLE `t_product_stock` (
  `id` varchar(255) NOT NULL COMMENT 'id',
  `product_id` varchar(255) DEFAULT NULL COMMENT '商品Id',
  `product_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `product_stock` int(11) DEFAULT NULL COMMENT '商品库存',
  `stock_threshold` int(11) DEFAULT NULL COMMENT '库存阀值',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  `del_flag` int(11) DEFAULT NULL COMMENT '数据状态： 0：准备 1：有效  2：冻结  3：作废',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4hakvrlguppep8xveqsntv5vq` (`product_id`),
  CONSTRAINT `FK4hakvrlguppep8xveqsntv5vq` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product_stock
-- ----------------------------
BEGIN;
INSERT INTO `t_product_stock` VALUES ('65182190127812608', '62613285781901312', NULL, 96, NULL, NULL, 0, NULL, '2018-10-18 11:03:06', NULL, '2018-10-18 14:19:04');
INSERT INTO `t_product_stock` VALUES ('65182190366887936', '62613122287931392', NULL, 29, NULL, NULL, 0, NULL, '2018-10-18 11:03:06', NULL, '2018-10-18 11:04:40');
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `default_role` bit(1) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES ('1', NULL, NULL, 0, NULL, '2018-06-11 14:16:07', b'1', 'ROLE_USER');
INSERT INTO `t_role` VALUES ('2', NULL, NULL, 0, NULL, NULL, NULL, 'ROLE_ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `permission_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_role_permission` VALUES ('64915617928450048', NULL, NULL, 0, NULL, NULL, '64915362721828864', '2');
INSERT INTO `t_role_permission` VALUES ('64915617920061440', NULL, NULL, 0, NULL, NULL, '64915081665712128', '2');
INSERT INTO `t_role_permission` VALUES ('64915617911672832', NULL, NULL, 0, NULL, NULL, '63835817528791040', '2');
INSERT INTO `t_role_permission` VALUES ('62363621237198848', NULL, NULL, 0, NULL, NULL, '18496505560174592', '1');
INSERT INTO `t_role_permission` VALUES ('62363621228810240', NULL, NULL, 0, NULL, NULL, '5', '1');
INSERT INTO `t_role_permission` VALUES ('62363621220421632', NULL, NULL, 0, NULL, NULL, '3', '1');
INSERT INTO `t_role_permission` VALUES ('62363621207838720', NULL, NULL, 0, NULL, NULL, '18486880530075648', '1');
INSERT INTO `t_role_permission` VALUES ('62363621195255808', NULL, NULL, 0, NULL, NULL, '6', '1');
INSERT INTO `t_role_permission` VALUES ('62363621186867200', NULL, NULL, 0, NULL, NULL, '4', '1');
INSERT INTO `t_role_permission` VALUES ('64915617899089920', NULL, NULL, 0, NULL, NULL, '63835554399129600', '2');
INSERT INTO `t_role_permission` VALUES ('64915617882312704', NULL, NULL, 0, NULL, NULL, '62680236608720896', '2');
INSERT INTO `t_role_permission` VALUES ('64915617865535488', NULL, NULL, 0, NULL, NULL, '62679979216867328', '2');
INSERT INTO `t_role_permission` VALUES ('64915617857146880', NULL, NULL, 0, NULL, NULL, '62679694251659264', '2');
INSERT INTO `t_role_permission` VALUES ('64915617848758272', NULL, NULL, 0, NULL, NULL, '62679369595752448', '2');
INSERT INTO `t_role_permission` VALUES ('64915617840369664', NULL, NULL, 0, NULL, NULL, '64450005213122560', '2');
INSERT INTO `t_role_permission` VALUES ('62363621165895680', NULL, NULL, 0, NULL, NULL, '2', '1');
INSERT INTO `t_role_permission` VALUES ('62363621132341248', NULL, NULL, 0, NULL, NULL, '1', '1');
INSERT INTO `t_role_permission` VALUES ('64915617831981056', NULL, NULL, 0, NULL, NULL, '62635125921288192', '2');
INSERT INTO `t_role_permission` VALUES ('64915617815203840', NULL, NULL, 0, NULL, NULL, '61990514022420480', '2');
INSERT INTO `t_role_permission` VALUES ('64915617802620928', NULL, NULL, 0, NULL, NULL, '61990171792379904', '2');
INSERT INTO `t_role_permission` VALUES ('64915617794232320', NULL, NULL, 0, NULL, NULL, '61989699186593792', '2');
INSERT INTO `t_role_permission` VALUES ('64915617781649408', NULL, NULL, 0, NULL, NULL, '18486880530075648', '2');
INSERT INTO `t_role_permission` VALUES ('64915617773260800', NULL, NULL, 0, NULL, NULL, '6', '2');
INSERT INTO `t_role_permission` VALUES ('64915617760677888', NULL, NULL, 0, NULL, NULL, '4', '2');
INSERT INTO `t_role_permission` VALUES ('64915617743900672', NULL, NULL, 0, NULL, NULL, '2', '2');
INSERT INTO `t_role_permission` VALUES ('64915617668403200', NULL, NULL, 0, NULL, NULL, '1', '2');
INSERT INTO `t_role_permission` VALUES ('64915617936838656', NULL, NULL, 0, NULL, NULL, '64915516266909696', '2');
INSERT INTO `t_role_permission` VALUES ('64915617949421568', NULL, NULL, 0, NULL, NULL, '3', '2');
INSERT INTO `t_role_permission` VALUES ('64915617957810176', NULL, NULL, 0, NULL, NULL, '5', '2');
INSERT INTO `t_role_permission` VALUES ('64915617970393088', NULL, NULL, 0, NULL, NULL, '18496505560174592', '2');
COMMIT;

-- ----------------------------
-- Table structure for t_sale_person
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_person`;
CREATE TABLE `t_sale_person` (
  `id` varchar(255) NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jhib4legehrm4yscx9t3lirqi` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sale_person
-- ----------------------------
BEGIN;
INSERT INTO `t_sale_person` VALUES ('62639246141296640', '陈杰', NULL, 1, '18212121211', '1111@qq.com', NULL, NULL, NULL, '2018-10-11 10:38:21', 0, NULL, NULL, 'aa');
INSERT INTO `t_sale_person` VALUES ('65228993279823872', '孙佳丽', NULL, 0, '18212121111', '88888@qq.com', NULL, NULL, NULL, '2018-10-18 14:09:05', 0, NULL, NULL, '112');
INSERT INTO `t_sale_person` VALUES ('65229160313786368', '李四', NULL, 1, '18912121111', '111@qq.com', NULL, NULL, NULL, '2018-10-18 14:09:45', 0, NULL, NULL, '131');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `avatar` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nick_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jhib4legehrm4yscx9t3lirqi` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('18418314988490752', NULL, '2018-06-11 10:00:10', 0, NULL, '2018-06-11 10:00:10', NULL, 'https://s1.ax1x.com/2018/05/19/CcdVQP.png', NULL, NULL, '18321248615', NULL, '4280d89a5a03f812751f504cc10ee8a5', NULL, 0, 0, 'test');
INSERT INTO `t_user` VALUES ('18440041265106944', NULL, '2018-06-11 11:26:30', 0, NULL, '2018-06-11 11:26:30', NULL, 'https://s1.ax1x.com/2018/05/19/CcdVQP.png', NULL, '111@qq.com', '18221819119', NULL, '$2a$10$RCqTQZD6bRxYqeB3mxAIMOjE88NIYx.ZhwReLNEHSEv11M0sR42y.', 1, 0, 0, 'jojo');
INSERT INTO `t_user` VALUES ('19227624140181504', NULL, '2018-06-13 15:36:04', 0, NULL, '2018-06-13 15:36:04', NULL, 'https://s1.ax1x.com/2018/05/19/CcdVQP.png', NULL, NULL, '18321248716', NULL, '$2a$10$AgZXQn8BWJuqu4UrBiC7H.bVARuiiROMWhDpGU7rRVsiSQZn88H1S', NULL, 0, 0, 'sunjiamin');
INSERT INTO `t_user` VALUES ('22114037391167488', NULL, '2018-06-21 14:45:39', 0, NULL, '2018-06-21 14:45:39', NULL, 'https://s1.ax1x.com/2018/05/19/CcdVQP.png', NULL, '823@qq.com', '18321248761', NULL, '$2a$10$OkF8BXfrdSNcioHV8U.hbuZdfdQ0ZBTk8UNK9Xz7BBGc/rzqZp1Ii', 1, 0, 1, 'admin');
INSERT INTO `t_user` VALUES ('23649757989703680', NULL, '2018-06-25 20:28:03', 0, NULL, '2018-06-25 20:28:03', NULL, 'https://s1.ax1x.com/2018/05/19/CcdVQP.png', NULL, '111@qq.ccc', '18232348716', NULL, '$2a$10$U28hKldGU0wAfZrbts3aauI1zQ6BKBQbllgOuX2h6rCJAAuxMTPuq', 0, 0, 0, 'jojo1');
INSERT INTO `t_user` VALUES ('23650487609856000', '', '2018-06-25 20:32:28', 0, '', '2018-06-25 20:32:28', '', 'https://s1.ax1x.com/2018/05/19/CcdVQP.png', '', 'qqq@qq.cc', '18212121212', '', '$2a$10$uPOLxqxojLecj0eKl6UFZ.honK3sm3GRzDHnYuLGCSSQ3uqNCUyHO', 1, -1, 0, 'qqq');
COMMIT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `role_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role` VALUES ('1', NULL, NULL, NULL, NULL, NULL, '1', '18418314988490752');
INSERT INTO `t_user_role` VALUES ('18440042020081664', NULL, '2018-06-11 11:26:30', 0, NULL, '2018-06-11 11:26:30', '1', '18440041265106944');
INSERT INTO `t_user_role` VALUES ('19227625000013824', NULL, NULL, 0, NULL, NULL, '1', '19227624140181504');
INSERT INTO `t_user_role` VALUES ('22114038188085248', NULL, '2018-06-21 14:45:39', 0, NULL, '2018-06-21 14:45:39', '2', '22114037391167488');
INSERT INTO `t_user_role` VALUES ('23649758966976512', NULL, '2018-06-25 20:28:03', 0, NULL, '2018-06-25 20:28:03', '1', '23649757989703680');
INSERT INTO `t_user_role` VALUES ('62328327062425600', NULL, '2018-10-10 14:02:52', 0, NULL, NULL, '1', '23650487609856000');
INSERT INTO `t_user_role` VALUES ('62328215745597440', NULL, '2018-10-10 14:02:26', 0, NULL, NULL, '1', '23650487609856000');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
