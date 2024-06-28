/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : threat_perception

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 04/06/2024 08:41:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;



-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `user_pwd` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `user_role` int(10) UNSIGNED NOT NULL DEFAULT 2 COMMENT '用户角色',
  `user_status` int(10) UNSIGNED NULL DEFAULT 1 COMMENT '用户状态：1 正常 2 锁定',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '用户创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '用户更新时间',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '用户最后登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_user_name`(`user_name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$l4/yfZ1SuK/8tpafODPLnuryGoL6YpZC4aYOhApSQ0Sqq4LSGrkBq', 1, 1, '2024-05-31 09:21:26', NULL, '2024-05-31 09:21:26');
INSERT INTO `user` VALUES (2, 'tomcat', '$2a$10$XUgMIGI/yKmQJFrx0oPwHOLi.Xkc2GqnES9qDvtZ79/IS2sjPaNNS', 2, 1, '2024-05-24 14:21:00', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
