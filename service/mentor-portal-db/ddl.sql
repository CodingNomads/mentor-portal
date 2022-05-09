DROP DATABASE IF EXISTS `mentor_portal_db_dev`;

CREATE DATABASE `mentor_portal_db_dev`;

USE `mentor_portal_db_dev`;

-- user
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `email` varchar(255) NOT NULL COMMENT 'User Email',
    `first_name` varchar(255),
    `last_name` varchar(255),
    `role_code` int(10) UNSIGNED NOT NULL COMMENT 'The user role',
    `status_code` int(10) NOT NULL COMMENT 'Active or inactive status',
    `flag` tinyint NOT NULL COMMENT 'Flag boolean for admin attention',
    `timezone_offset` varchar(255) NOT NULL,
    `bio` varchar(255),
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='List of users';

-- mentor_student_lookup
DROP TABLE IF EXISTS `mentor_student_lookup`;

CREATE TABLE `mentor_student_lookup` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `mentor_id` int(10) UNSIGNED NOT NULL COMMENT 'Foreign Key',
    `student_id` int(10) UNSIGNED NOT NULL COMMENT 'Foreign Key',
    `status_code` int NOT NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `mentor_id_idx` (`mentor_id`),
    KEY `student_id_idx` (`student_id`),
    CONSTRAINT `fk_user_id_mentor` FOREIGN KEY (`mentor_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_user_id_student` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Mentor and student status';

-- security
DROP TABLE IF EXISTS `security`;

CREATE TABLE `security` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `user_id` int(10) UNSIGNED NOT NULL COMMENT 'Foreign Key',
    `password_hash` varchar(255) NOT NULL,
    `is_admin` tinyint NOT NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `user_id_idx` (`user_id`),
    CONSTRAINT `fk_user_id_security` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Username, password, admin status';

-- contact
DROP TABLE IF EXISTS `contact`;

CREATE TABLE `contact` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `user_id` int(10) UNSIGNED NOT NULL,
    `telephone` varchar(255),
    `location` varchar(255),
    `forum_username` varchar(255),
    `slack_username` varchar(255),
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `user_id_idx` (`user_id`),
    CONSTRAINT `fk_user_id_contact` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='User contact and account information';

-- support_log
DROP TABLE IF EXISTS `support_log`;

CREATE TABLE `support_log` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `mentor_id` int(10) UNSIGNED NOT NULL,
    `student_id` int(10) UNSIGNED NOT NULL,
    `type` varchar(255),
    `flag` tinyint NOT NULL COMMENT 'Flag boolean for admin attention',
    `duration` int(10) NOT NULL,
    `log` varchar(255) NOT NULL,
    `log_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Log for tracking mentorship program';

-- role
DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
    `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `code` int NOT NULL,
    `name` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Classification of user role with code';

-- status
DROP TABLE IF EXISTS `status`;

CREATE TABLE `status` (
    `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `code` int NOT NULL,
    `description` varchar(255) NOT NULL,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Classification of mentorship status';

-- user config options
DROP TABLE IF EXISTS `user_config_option`;

CREATE TABLE `user_config_option` (
                          `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
                          `option_name` varchar(255) NOT NULL,
                          `description` varchar(255) NOT NULL,
                          `is_sensitive` tinyint NOT NULL DEFAULT 0,
                          `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Classification of mentorship status';

-- user config values
DROP TABLE IF EXISTS `user_config_value`;

CREATE TABLE `user_config_value` (
                          `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
                          `user_id`int(10) UNSIGNED NOT NULL,
                          `option_id` int NOT NULL,
                          `value` varchar(255) NOT NULL,
                          `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Classification of mentorship status';