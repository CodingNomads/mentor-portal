USE `mentor_portal_db_dev`;

INSERT INTO `role` (code, name, description)
VALUES (10, 'mentor', 'A CodingNomads mentor'),
       (20, 'student', 'A CodingNomads student');

INSERT INTO `status` (code, description)
VALUES (100, 'active'),
       (200, 'paused'),
       (300, 'cancelled'),
       (999, 'deleted');

INSERT INTO `user` (first_name, last_name, role_code, status_code, flag, timezone_offset)
VALUES ('student 1', 'smith', 20, 100, 0, '-8'),
       ('mentor 1', 'doe', 10, 100, 0, '-4'),
       ('student 2', 'smith', 20, 100, 0, '-8'),
       ('mentor 2', 'doe', 10, 100, 0, '-4'),
       ('inactive student', 'smith', 20, 200, 1, '-4'),
       ('admin', 'admin', 10, 100, 0, '-8'),
       ('student', 'smith', 20, 100, 1, "-5");

INSERT INTO `security` (user_id, username, password_hash, is_admin)
VALUES (1, 'username1', 'placeholder until real hash', 0),
       (2, 'username2', 'placeholder until real hash', 0),
       (3, 'username3', 'placeholder until real hash', 0),
       (4, 'username4', 'placeholder until real hash', 0),
       (5, 'username5', 'placeholder until real hash', 0),
       (6, 'username6', 'placeholder until real hash', 1),
       (7, 'username7', 'placeholder until real hash', 0);

INSERT INTO `contact` (user_id, email, telephone, forum_username, slack_username)
VALUES (1, 'email1@email.com', '8009001234', 'forum_username1', 'slack me'),
       (2, 'email2@email.com', '8009001234', 'forum_username2', 'slack me'),
       (3, 'email3@email.com', '8009001234', 'forum_username3', 'slack me'),
       (4, 'email4@email.com', '8009001234', 'forum_username4', 'slack me'),
       (5, 'email5@email.com', '8009001234', 'forum_username5', 'slack me'),
       (6, 'email6@email.com', '8009001234', 'forum_username6', 'slack me'),
       (7, 'email7@email.com', '8009001234', 'forum_username7', 'slack me');

INSERT INTO `mentor_student_lookup` (mentor_id, student_id, status_code)
VALUES (2, 1, 100),
       (2, 3, 100),
       (4, 3, 200);

INSERT INTO `support_log` (mentor_id, student_id, type, flag, duration, log)
VALUES (2, 1, 'Scheduled 1:1', 0, 25, 'logging some support for student1.'),
       (2, 1, 'Impromptu 1:1', 0, 10, 'second support log for student1.'),
       (2, 3, 'Slack/Discord', 0, 5, 'logging some support for student2.'),
       (4, 1, 'Forum', 1, 12, 'logging some support for student1 by another mentor.');

INSERT INTO `user_config_option` (option_name, description, is_sensitive)
VALUES ("maxStudents", "The maximum students a mentor can be assigned.", 0),
       ("proficiency", "The programming language(s) a mentor id proficient with", 0),
       ("courseTrack", "The mentorship program a student is enrolled in", 0);

INSERT INTO `user_config_value` (user_id, option_id, value)
VALUES (2, 1, "10"),
       (4, 1, "10"),
       (6, 1, "10"),
       (2, 2, "python"),
       (4, 2, "python"),
       (4, 2, "java"),
       (1, 3, "Python Web Dev Career"),
       (3, 3, "Java Career"),
       (5, 3, "Month-to-Month"),
       (7, 3, "Month-to-Month");

