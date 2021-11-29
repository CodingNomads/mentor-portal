CREATE TABLE `security` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `user_id` int NOT NULL COMMENT 'Foreign Key',
  `username` varchar(255) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `is_admin` tinyint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `fk_user_id_security` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Username, password, admin status';
