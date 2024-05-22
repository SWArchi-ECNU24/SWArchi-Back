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
            delay datetime NULL DEFAULT NULL COMMENT '延期',
            submission_deadline datetime NULL DEFAULT NULL COMMENT '截稿日期',
            notification_date datetime NULL DEFAULT NULL COMMENT '通知日期',
            conference_date datetime NULL DEFAULT NULL COMMENT '会议日期',
            conference_location VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会议地点',
            session_number INT NULL DEFAULT NULL COMMENT '届数',
            submission_information VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '征稿信息',
            PRIMARY KEY(conference_id)
                USING BTREE
        ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER
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
            user_id INT NOT NULL,
            conference_id INT NOT NULL,
            conference_name VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            conference_url VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            ccf_rank VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ccf等级',
            delay datetime NULL DEFAULT NULL COMMENT '延期',
            submission_deadline datetime NULL DEFAULT NULL COMMENT '截稿日期',
            notification_date datetime NULL DEFAULT NULL COMMENT '通知日期',
            conference_date datetime NULL DEFAULT NULL COMMENT '会议日期',
            conference_location VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会议地点',
            session_number INT NULL DEFAULT NULL COMMENT '届数',
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
-- Table structure for conference_participation
-- ----------------------------
DROP
    TABLE
        IF EXISTS conference_participation;

CREATE
    TABLE
        conference_participation(
            user_id INT NOT NULL,
            conference_id INT NOT NULL,
            PRIMARY KEY(
                user_id,
                conference_id
            )
                USING BTREE
        ) ENGINE = InnoDB CHARACTER
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
        ) ENGINE = InnoDB CHARACTER
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
            user_id INT NOT NULL,
            conference_id INT NOT NULL,
            followed_id INT NOT NULL AUTO_INCREMENT,
            PRIMARY KEY(followed_id)
                USING BTREE
        ) ENGINE = InnoDB CHARACTER
    SET
        = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP
    TABLE
        IF EXISTS GROUP;

CREATE
    TABLE
        GROUP(
            group_id INT NOT NULL AUTO_INCREMENT,
            conference_id INT NOT NULL,
            group_name VARCHAR(255) CHARACTER
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
        ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER
    SET
        = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for group_user
-- ----------------------------
DROP
    TABLE
        IF EXISTS group_user;

CREATE
    TABLE
        group_user(
            group_id INT NOT NULL AUTO_INCREMENT,
            user_id INT NOT NULL,
            gu_id INT NOT NULL,
            PRIMARY KEY(gu_id)
                USING BTREE,
            INDEX group_id(
                group_id ASC
            )
                USING BTREE,
            CONSTRAINT group_user_ibfk_1 FOREIGN KEY(group_id) REFERENCES GROUP(group_id) ON
            DELETE
                RESTRICT ON
                UPDATE
                    RESTRICT
        ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER
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
            submission_information VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            special_issue VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            PRIMARY KEY(journal_id)
                USING BTREE
        ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER
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
            user_id INT NOT NULL,
            journal_id INT NOT NULL,
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
            submission_information VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
            special_issue VARCHAR(255) CHARACTER
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
            INDEX journal_id(
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
            user_id INT NOT NULL,
            journal_id INT NOT NULL,
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
            submission_information VARCHAR(255) CHARACTER
        SET
            utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
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
                USING BTREE
        ) ENGINE = InnoDB CHARACTER
    SET
        = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;
SET
FOREIGN_KEY_CHECKS = 1;
