DROP TABLE IF EXISTS `kotlin.backend.db`.`user`;

CREATE TABLE `kotlin.backend.db`.`user` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `role_code` int(10) UNSIGNED NOT NULL COMMENT 'The user role',
  `status_code` int(10) NOT NULL,
  `timezone_offset` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='List of users';
