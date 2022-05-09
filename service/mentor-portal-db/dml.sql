USE `mentor_portal_db_dev`;

INSERT INTO `role` (code, name, description)
VALUES (10, 'mentor', 'A CodingNomads mentor'),
       (20, 'student', 'A CodingNomads student');

INSERT INTO `status` (code, description)
VALUES (100, 'active'),
       (200, 'paused'),
       (300, 'cancelled'),
       (999, 'deleted');

INSERT INTO `user` (first_name, last_name, role_code, status_code, flag, timezone_offset, bio, email)
VALUES ('student 1', 'smith', 20, 100, 1, '-8', 'Bio for student1 with their goals', 'email1@email.com'),
       ('mentor 1', 'doe', 10, 100, 0, '-4', 'Bio for mentor1 with their history', 'admin@email.com'),
       ('student 2', 'smith', 20, 100, 0, '-8', 'Bio for student2 with their goals', 'email3@email.com'),
       ('mentor 2', 'doe', 10, 100, 1, '-4', 'Bio for mentor2 with their history', 'email4@email.com'),
       ('inactive student', 'smith', 20, 200, 1, '-4', 'Bio for inactive student', 'email5@email.com'),
       ('admin', 'admin', 10, 100, 0, '-8', 'Bio for admin with their history', 'email6@email.com'),
       ('student', 'smith', 20, 100, 1, "-5", 'Bio for student with their goals', 'email7@email.com');

INSERT INTO `security` (user_id, password_hash, is_admin)
VALUES (1, '$2a$10$ttex2IYnW9u95E/qOQmS7uBoa4LNL7v/9BrYz/7Z8BJ77rHEGhA/2', 0),
       (2, '$2a$10$ttex2IYnW9u95E/qOQmS7uBoa4LNL7v/9BrYz/7Z8BJ77rHEGhA/2', 1),
       (3, '$2a$10$ttex2IYnW9u95E/qOQmS7uBoa4LNL7v/9BrYz/7Z8BJ77rHEGhA/2', 0),
       (4, '$2a$10$ttex2IYnW9u95E/qOQmS7uBoa4LNL7v/9BrYz/7Z8BJ77rHEGhA/2', 0),
       (5, '$2a$10$ttex2IYnW9u95E/qOQmS7uBoa4LNL7v/9BrYz/7Z8BJ77rHEGhA/2', 0),
       (6, '$2a$10$ttex2IYnW9u95E/qOQmS7uBoa4LNL7v/9BrYz/7Z8BJ77rHEGhA/2', 1),
       (7, '$2a$10$ttex2IYnW9u95E/qOQmS7uBoa4LNL7v/9BrYz/7Z8BJ77rHEGhA/2', 0);

INSERT INTO `contact` (user_id, telephone, location, forum_username, slack_username)
VALUES (1, '8009001234', 'London', 'forum_username1', 'slack me'),
       (2, '8009001234', 'Tokyo', 'forum_username2', 'slack me'),
       (3, '8009001234', 'Calgary', 'forum_username3', 'slack me'),
       (4, '8009001234', 'Oregon', 'forum_username4', 'slack me'),
       (5, '8009001234', 'Vienna', 'forum_username5', 'slack me'),
       (6, '8009001234', 'New Delhi', 'forum_username6', 'slack me'),
       (7, '8009001234', 'New York', 'forum_username7', 'slack me');

INSERT INTO `mentor_student_lookup` (mentor_id, student_id, status_code)
VALUES (2, 1, 100),
       (2, 3, 100),
       (4, 3, 200);

INSERT INTO `support_log` (mentor_id, student_id, type, flag, duration, log)
VALUES (2, 1, 'Scheduled 1:1', 0, 25, 'logging some support for student1.'),
       (2, 1, 'Impromptu 1:1', 0, 10, 'second support log for student1.'),
       (2, 3, 'Slack/Discord', 0, 5, 'logging some support for student2.'),
       (4, 1, 'Forum', 1, 12, 'logging some support for student1 by another mentor.'),
       (6, 2, 'Slack/Discord', 0, 10, 'admin logging support for mentor2.'),
       (6, 2, 'Scheduled 1:1', 1, 15, 'admin logging flagged log for mentor2');

INSERT INTO `user_config_option` (option_name, description, is_sensitive)
VALUES ("maxStudents", "The maximum students a mentor can be assigned.", 0),
       ("proficiencies", "The programming language(s) a mentor id proficient with.", 0),
       ("courseTrack", "The course a student is enrolled in.", 0),
       ("mentorshipOption", "The mentorship option a student is enrolled in.", 0);

INSERT INTO `user_config_value` (user_id, option_id, value)
VALUES (2, 1, "10"),
       (4, 1, "10"),
       (6, 1, "10"),
       (2, 2, "Python Programming"),
       (4, 2, "Python Programming"),
       (4, 2, "Java Programming"),
       (1, 3, "Python Programming"),
       (1, 4, "Python DSML Career"),
       (3, 3, "Java Programming"),
       (3, 4, "Java Career"),
       (5, 3, "Javascript"),
       (5, 4, "Month-to-Month"),
       (7, 3, "Javascript"),
       (6, 2, "Python Programming");

