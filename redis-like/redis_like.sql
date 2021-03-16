/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : redis_like

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2021-03-16 16:38:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_like
-- ----------------------------
DROP TABLE IF EXISTS `user_like`;
CREATE TABLE `user_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `like_user_id` varchar(50) NOT NULL COMMENT '被点赞用户',
  `liked_post_id` varchar(50) NOT NULL COMMENT '点赞用户id',
  `status` tinyint(1) DEFAULT '0' COMMENT '点赞状态 0取消/未点赞 1点赞',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `LikeUserId_LikedPostId` (`like_user_id`,`liked_post_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户点赞表';

-- ----------------------------
-- Records of user_like
-- ----------------------------
