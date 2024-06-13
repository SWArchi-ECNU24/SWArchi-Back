CREATE
    DATABASE IF NOT EXISTS auth DEFAULT CHARACTER
SET
    utf8mb4;

CREATE
    DATABASE IF NOT EXISTS parter DEFAULT CHARACTER
SET
    utf8mb4;

USE auth;

/*
 Navicat Premium Data Transfer

 Source Server         : conference
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : auth

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 05/06/2024 18:22:23
*/
SET
NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP
    TABLE
        IF EXISTS USER;

CREATE
    TABLE
        USER(
            user_id INT NOT NULL AUTO_INCREMENT,
            user_name VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            user_password VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            authority enum(
                'administrator',
                'user'
            ) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'user',
            email VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            institution VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            signup_date datetime NULL DEFAULT NULL,
            PRIMARY KEY(user_id)
                USING BTREE
        ) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER
    SET
        = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user_followers
-- ----------------------------
DROP
    TABLE
        IF EXISTS user_followers;

CREATE
    TABLE
        user_followers(
            uf_id INT NOT NULL AUTO_INCREMENT,
            user_id INT NOT NULL,
            followers_id INT NOT NULL,
            PRIMARY KEY(uf_id)
                USING BTREE,
            INDEX user_id(
                user_id ASC
            )
                USING BTREE,
            INDEX followers_id(
                followers_id ASC
            )
                USING BTREE,
            CONSTRAINT user_followers_ibfk_1 FOREIGN KEY(user_id) REFERENCES USER(user_id) ON
            DELETE
                CASCADE ON
                UPDATE
                    CASCADE,
                    CONSTRAINT user_followers_ibfk_2 FOREIGN KEY(followers_id) REFERENCES USER(user_id) ON
                    DELETE
                        CASCADE ON
                        UPDATE
                            CASCADE
        ) ENGINE = InnoDB CHARACTER
    SET
        = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;
SET
FOREIGN_KEY_CHECKS = 1;

USE parter;

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

 Date: 11/06/2024 23:47:05
*/
SET
NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for conference
-- ----------------------------
DROP
    TABLE
        IF EXISTS conference;

CREATE
    TABLE
        conference(
            conference_id INT NOT NULL AUTO_INCREMENT,
            conference_name VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            conference_url VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            ccf_rank VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ccf等级',
            delay enum(
                'yes',
                'no'
            ) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'no' COMMENT '延期',
            submission_deadline datetime NULL DEFAULT NULL COMMENT '截稿日期',
            notification_date datetime NULL DEFAULT NULL COMMENT '通知日期',
            conference_date datetime NULL DEFAULT NULL COMMENT '会议日期',
            conference_location VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会议地点',
            session_number INT NULL DEFAULT NULL COMMENT '届数',
            submission_information VARCHAR(10000) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '征稿信息',
            PRIMARY KEY(conference_id)
                USING BTREE
        ) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER
    SET
        = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for conference_cfp
-- ----------------------------
DROP
    TABLE
        IF EXISTS conference_cfp;

CREATE
    TABLE
        conference_cfp(
            cfp_id INT NOT NULL AUTO_INCREMENT,
            conference_id INT NOT NULL,
            submission_deadline datetime NULL DEFAULT NULL COMMENT '截稿日期',
            notification_date datetime NULL DEFAULT NULL COMMENT '通知日期',
            submission_information VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '征稿信息',
            is_approved enum(
                'yes',
                'no'
            ) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'no' COMMENT '是否延期',
            PRIMARY KEY(cfp_id)
                USING BTREE,
            INDEX conference_cfp_ibfk_1(
                conference_id ASC
            )
                USING BTREE,
            CONSTRAINT conference_cfp_ibfk_1 FOREIGN KEY(conference_id) REFERENCES conference(conference_id) ON
            DELETE
                CASCADE ON
                UPDATE
                    CASCADE
        ) ENGINE = InnoDB CHARACTER
    SET
        = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for conference_group
-- ----------------------------
DROP
    TABLE
        IF EXISTS conference_group;

CREATE
    TABLE
        conference_group(
            group_id INT NOT NULL AUTO_INCREMENT,
            conference_id INT NOT NULL,
            group_name VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            user_id VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            PRIMARY KEY(group_id)
                USING BTREE,
            INDEX group_ibfk_1(
                conference_id ASC
            )
                USING BTREE,
            CONSTRAINT group_ibfk_1 FOREIGN KEY(conference_id) REFERENCES conference(conference_id) ON
            DELETE
                CASCADE ON
                UPDATE
                    CASCADE
        ) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER
    SET
        = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for followed_conference
-- ----------------------------
DROP
    TABLE
        IF EXISTS followed_conference;

CREATE
    TABLE
        followed_conference(
            user_id INT NOT NULL,
            conference_id INT NOT NULL,
            follow_id INT NOT NULL AUTO_INCREMENT,
            PRIMARY KEY(follow_id)
                USING BTREE
        ) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER
    SET
        = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for followed_journal
-- ----------------------------
DROP
    TABLE
        IF EXISTS followed_journal;

CREATE
    TABLE
        followed_journal(
            followed_id INT NOT NULL AUTO_INCREMENT,
            user_id INT NOT NULL,
            journal_id INT NOT NULL,
            PRIMARY KEY(followed_id)
                USING BTREE
        ) ENGINE = InnoDB CHARACTER
    SET
        = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for journal
-- ----------------------------
DROP
    TABLE
        IF EXISTS journal;

CREATE
    TABLE
        journal(
            journal_id INT NOT NULL AUTO_INCREMENT,
            journal_name VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            journal_url VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            ccf_rank VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            impact_factor VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            publisher VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            issn VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            submission_information VARCHAR(5000) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            special_issue VARCHAR(5000) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            PRIMARY KEY(journal_id)
                USING BTREE
        ) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER
    SET
        = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for journal_cfp
-- ----------------------------
DROP
    TABLE
        IF EXISTS journal_cfp;

CREATE
    TABLE
        journal_cfp(
            jc_id INT NOT NULL AUTO_INCREMENT,
            journal_id INT NULL DEFAULT NULL,
            submission_information VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            is_approved enum(
                'yes',
                'no'
            ) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'no',
            PRIMARY KEY(jc_id)
                USING BTREE,
            INDEX journal_cfp_ibfk_1(
                journal_id ASC
            )
                USING BTREE,
            CONSTRAINT journal_cfp_ibfk_1 FOREIGN KEY(journal_id) REFERENCES journal(journal_id) ON
            DELETE
                CASCADE ON
                UPDATE
                    CASCADE
        ) ENGINE = InnoDB CHARACTER
    SET
        = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for journal_issue
-- ----------------------------
DROP
    TABLE
        IF EXISTS journal_issue;

CREATE
    TABLE
        journal_issue(
            ji_id INT NOT NULL AUTO_INCREMENT,
            journal_id INT NOT NULL,
            special_issue VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            is_approved enum(
                'yes',
                'no'
            ) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'no',
            PRIMARY KEY(ji_id)
                USING BTREE,
            INDEX journal_id(
                journal_id ASC
            )
                USING BTREE,
            CONSTRAINT journal_issue_ibfk_1 FOREIGN KEY(journal_id) REFERENCES journal(journal_id) ON
            DELETE
                CASCADE ON
                UPDATE
                    CASCADE
        ) ENGINE = InnoDB CHARACTER
    SET
        = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;
SET
FOREIGN_KEY_CHECKS = 1;

