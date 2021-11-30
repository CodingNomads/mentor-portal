CREATE TABLE `support_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `mentor_student_lookup_id` int NOT NULL COMMENT 'Foreign Key',
  `log` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mentor_student_lookup_id_idx` (`mentor_student_lookup_id`),
  CONSTRAINT `fk_mentor_student_lookup_id_support_log` FOREIGN KEY (`mentor_student_lookup_id`) REFERENCES `mentor_student_lookup` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Support log for mentorship';
