CREATE TABLE `mentor_student_lookup` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `mentor_id` int NOT NULL COMMENT 'Foreign Key',
  `student_id` int NOT NULL COMMENT 'Foreign Key',
  `status_code` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mentor_id_idx` (`mentor_id`),
  KEY `student_id_idx` (`student_id`),
  CONSTRAINT `fk_user_id_mentor` FOREIGN KEY (`mentor_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id_student` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=compressed COMMENT='Mentor and student status';
