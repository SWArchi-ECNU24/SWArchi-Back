/*
 Navicat Premium Data Transfer

 Source Server         : conference
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : parter

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 21/05/2024 23:08:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for conference
-- ----------------------------
DROP TABLE IF EXISTS `conference`;
CREATE TABLE `conference`  (
                               `conference_id` int NOT NULL AUTO_INCREMENT,
                               `conference_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                               `conference_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                               `ccf_rank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ccf等级',
                               `delay` datetime NULL DEFAULT NULL COMMENT '延期',
                               `submission_deadline` datetime NULL DEFAULT NULL COMMENT '截稿日期',
                               `notification_date` datetime NULL DEFAULT NULL COMMENT '通知日期',
                               `conference_date` datetime NULL DEFAULT NULL COMMENT '会议日期',
                               `conference_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会议地点',
                               `session_number` int NULL DEFAULT NULL COMMENT '届数',
                               `submission_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '征稿信息',
                               PRIMARY KEY (`conference_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for conference_cfp
-- ----------------------------
DROP TABLE IF EXISTS `conference_cfp`;
CREATE TABLE `conference_cfp`  (
                                   `cfp_id` int NOT NULL AUTO_INCREMENT,
                                   `user_id` int NOT NULL,
                                   `conference_id` int NOT NULL,
                                   `conference_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                   `conference_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                   `ccf_rank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ccf等级',
                                   `delay` datetime NULL DEFAULT NULL COMMENT '延期',
                                   `submission_deadline` datetime NULL DEFAULT NULL COMMENT '截稿日期',
                                   `notification_date` datetime NULL DEFAULT NULL COMMENT '通知日期',
                                   `conference_date` datetime NULL DEFAULT NULL COMMENT '会议日期',
                                   `conference_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会议地点',
                                   `session_number` int NULL DEFAULT NULL COMMENT '届数',
                                   `submission_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '征稿信息',
                                   `is_approved` enum('yes','no') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'no' COMMENT '是否延期',
                                   PRIMARY KEY (`cfp_id`) USING BTREE,
                                   INDEX `conference_cfp_ibfk_1`(`conference_id` ASC) USING BTREE,
                                   CONSTRAINT `conference_cfp_ibfk_1` FOREIGN KEY (`conference_id`) REFERENCES `conference` (`conference_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for conference_participation
-- ----------------------------
DROP TABLE IF EXISTS `conference_participation`;
CREATE TABLE `conference_participation`  (
                                             `user_id` int NOT NULL,
                                             `conference_id` int NOT NULL,
                                             PRIMARY KEY (`user_id`, `conference_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for followed_conference
-- ----------------------------
DROP TABLE IF EXISTS `followed_conference`;
CREATE TABLE `followed_conference`  (
                                        `user_id` int NOT NULL,
                                        `conference_id` int NOT NULL,
                                        `follow_id` int NOT NULL AUTO_INCREMENT,
                                        PRIMARY KEY (`follow_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for followed_journal
-- ----------------------------
DROP TABLE IF EXISTS `followed_journal`;
CREATE TABLE `followed_journal`  (
                                     `user_id` int NOT NULL,
                                     `conference_id` int NOT NULL,
                                     `followed_id` int NOT NULL AUTO_INCREMENT,
                                     PRIMARY KEY (`followed_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
                          `group_id` int NOT NULL AUTO_INCREMENT,
                          `conference_id` int NOT NULL,
                          `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                          PRIMARY KEY (`group_id`) USING BTREE,
                          INDEX `group_ibfk_1`(`conference_id` ASC) USING BTREE,
                          CONSTRAINT `group_ibfk_1` FOREIGN KEY (`conference_id`) REFERENCES `conference` (`conference_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_user
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user`  (
                               `group_id` int NOT NULL AUTO_INCREMENT,
                               `user_id` int NOT NULL,
                               `gu_id` int NOT NULL,
                               PRIMARY KEY (`gu_id`) USING BTREE,
                               INDEX `group_id`(`group_id` ASC) USING BTREE,
                               CONSTRAINT `group_user_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `group` (`group_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for journal
-- ----------------------------
DROP TABLE IF EXISTS `journal`;
CREATE TABLE `journal`  (
                            `journal_id` int NOT NULL AUTO_INCREMENT,
                            `journal_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `journal_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `ccf_rank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `impact_factor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `issn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `submission_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            `special_issue` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                            PRIMARY KEY (`journal_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for journal_cfp
-- ----------------------------
DROP TABLE IF EXISTS `journal_cfp`;
CREATE TABLE `journal_cfp`  (
                                `jc_id` int NOT NULL AUTO_INCREMENT,
                                `user_id` int NOT NULL,
                                `journal_id` int NOT NULL,
                                `journal_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                `journal_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                `ccf_rank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                `impact_factor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                `issn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                `submission_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                `special_issue` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                `is_approved` enum('yes','no') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'no',
                                PRIMARY KEY (`jc_id`) USING BTREE,
                                INDEX `journal_id`(`journal_id` ASC) USING BTREE,
                                CONSTRAINT `journal_cfp_ibfk_1` FOREIGN KEY (`journal_id`) REFERENCES `journal` (`journal_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for journal_issue
-- ----------------------------
DROP TABLE IF EXISTS `journal_issue`;
CREATE TABLE `journal_issue`  (
                                  ` ji_id` int NOT NULL AUTO_INCREMENT,
                                  `user_id` int NOT NULL,
                                  `journal_id` int NOT NULL,
                                  `journal_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                  `journal_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                  `ccf_rank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                  `impact_factor` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                  `publisher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                  `issn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                  `submission_information` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                  `special_issue` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                  `is_approved` enum('yes','no') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'no',
                                  PRIMARY KEY (` ji_id`) USING BTREE,
                                  INDEX `journal_id`(`journal_id` ASC) USING BTREE,
                                  CONSTRAINT `journal_issue_ibfk_1` FOREIGN KEY (`journal_id`) REFERENCES `journal` (`journal_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_followers
-- ----------------------------
DROP TABLE IF EXISTS `user_followers`;
CREATE TABLE `user_followers`  (
                                   `uf_id` int NOT NULL AUTO_INCREMENT,
                                   `user_id` int NOT NULL,
                                   `followers_id` int NOT NULL,
                                   PRIMARY KEY (`uf_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
