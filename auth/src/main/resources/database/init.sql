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
