-- user
DROP TABLE IF EXISTS `kotlin.backend.db`.`user`;

CREATE TABLE `kotlin.backend.db`.`user` (
    `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `role_code` int(10) UNSIGNED NOT NULL COMMENT 'The user role',
    `status_code` int(10) NOT NULL,
    `timezone_offset` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='List of users';

-- mentor_student_lookup
DROP TABLE IF EXISTS `kotlin.backend.db`.`mentor_student_lookup`;

CREATE TABLE `kotlin.backend.db`.`mentor_student_lookup` (
    `id` INT NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `mentor_id` int NOT NULL COMMENT 'Foreign Key',
    `student_id` int NOT NULL COMMENT 'Foreign Key',
    `status_code` int NOT NULL,
    `created_at` timestamp NOT NULL,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `mentor_id_idx` (`mentor_id`),
    KEY `student_id_idx` (`student_id`),
    CONSTRAINT `fk_user_id_mentor` FOREIGN KEY (`mentor_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_user_id_student` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Mentor and student status';

-- security
DROP TABLE IF EXISTS `kotlin.backend.db`.`security`;

CREATE TABLE `kotlin.backend.db`.`security` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `user_id` int NOT NULL COMMENT 'Foreign Key',
    `username` varchar(255) NOT NULL,
    `password_hash` varchar(255) NOT NULL,
    `is_admin` tinyint NOT NULL,
    `created_at` timestamp NOT NULL,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `user_id_idx` (`user_id`),
    CONSTRAINT `fk_user_id_security` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Username, password, admin status';

-- contact
DROP TABLE IF EXISTS `kotlin.backend.db`.`contact`;

CREATE TABLE `kotlin.backend.db`.`contact` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `user_id` int NOT NULL,
    `first_name` varchar(255) NOT NULL,
    `last_name` varchar(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `telephone` varchar(255) NOT NULL,
    `forum_username` varchar(255) NOT NULL,
    `slack_username` varchar(255) NOT NULL,
    `created_at` timestamp NOT NULL,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `user_id_idx` (`user_id`),
    CONSTRAINT `fk_user_id_contact` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='User contact and account information';

-- support_log
DROP TABLE IF EXISTS `kotlin.backend.db`.`support_log`;

CREATE TABLE `kotlin.backend.db`.`support_log` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `mentor_student_lookup_id` int NOT NULL,
    `log` varchar(255) NOT NULL,
    `created_at` timestamp NOT NULL,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `mentor_student_lookup_id_idx` (`mentor_student_lookup_id`),
    CONSTRAINT `fk_mentor_student_lookup_id_support_log` FOREIGN KEY (`mentor_student_lookup_id`) REFERENCES `mentor_student_lookup` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Log for tracking mentorship program';

-- role
DROP TABLE IF EXISTS `kotlin.backend.db`.`role`;

CREATE TABLE `kotlin.backend.db`.`role` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `code` int NOT NULL,
    `name` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    `created_at` timestamp NOT NULL,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Classification of user role with code';

-- status
DROP TABLE IS EXISTS `kotlin.backend.db`.`status`;

CREATE TABLE `kotlin.backend.db`.`status` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `code` int NOT NULL,
    `description` varchar(255) NOT NULL,
    `created_at` timestamp NOT NULL,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Classification of mentorship status';

