/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44)
 Source Host           : localhost:3306
 Source Schema         : lon_admin

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44)
 File Encoding         : 65001

 Date: 31/08/2024 18:38:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(16) NOT NULL COMMENT '角色名称',
  `role_code` varchar(32) NOT NULL COMMENT '角色编码',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '角色状态 0正常 1冻结',
  `role_desc` varchar(64) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nick_name` varchar(16) NOT NULL COMMENT '昵称',
  `username` varchar(16) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(64) NOT NULL COMMENT '盐',
  `mobile` varchar(16) DEFAULT NULL COMMENT '电话',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态 0启用 1禁用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_user_pk` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` (`id`, `nick_name`, `username`, `password`, `salt`, `mobile`, `email`, `status`, `create_time`, `update_time`) VALUES (1, 'superadmin', 'superadmin', '$2a$10$S2EqJJWn3vhdzG3G.4wzXO2TseZDmvOS4z3arIsV1PNPITe1WFXjO', 'JTOTT6id4S5qcE7l', '', '', 0, '2024-08-24 21:52:59', '2024-08-24 21:52:59');
INSERT INTO `t_user` (`id`, `nick_name`, `username`, `password`, `salt`, `mobile`, `email`, `status`, `create_time`, `update_time`) VALUES (2, 'rbw123', 'rbw123', '$2a$10$ZQZ6gPC8.xAq.ZHhtnMFue/hVSGFLK04v.O8.oqsIlDYgglJB9xIG', 'NP2oBQkhIp8VjIqx', '2', '1', 1, '2024-08-24 22:12:05', '2024-08-24 22:12:05');
INSERT INTO `t_user` (`id`, `nick_name`, `username`, `password`, `salt`, `mobile`, `email`, `status`, `create_time`, `update_time`) VALUES (3, 'qweqwe', 'qweqwe', '$2a$10$LJrioWTKZQd4F19XHzTBaOT.83EbnRa9cWeFRMwERoSgcOwe9V0Fq', 'X0BcW5lGmMmgHpnY', '2', '1', 0, '2024-08-24 22:12:15', '2024-08-24 22:12:15');
COMMIT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`,`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
