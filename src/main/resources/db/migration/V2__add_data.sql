-- admin/admin
INSERT INTO users (first_name, last_name, login, password, roles) values ('The', 'Architect', 'admin', '$2a$10$csznBGiCXAQ.v4QyRSkbV.x8//TvJISzzRse21AgHTIgQoVF3jpfK', 'ADMIN');
-- a/a
INSERT INTO users (first_name, last_name, login, password, roles) values ('a', 'a', 'a', '$2a$10$Jgw7wVQNW.LxYIIsbi2Ug..Yb7SIEnn/y3Mo3tA.x6DW1A3rnkGye', 'ADMIN');
-- xxx/123
INSERT INTO users (first_name, last_name, login, password, roles) values ('The', 'Student', 'student', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 'STUDENT');
INSERT INTO users (first_name, last_name, login, password, roles) values ('The', 'Teacher', 'teacher', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 'TEACHER');
INSERT INTO users (first_name, last_name, login, password, roles) values ('The', 'Stuff', 'stuff', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 'STUFF');
INSERT INTO users (first_name, last_name, login, password, roles) values ('Bruce', 'Banner', 'hulk', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 'STUDENT');
INSERT INTO users (first_name, last_name, login, password, roles) values ('Tony', 'Stark', 'ironman', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 'STUDENT');
INSERT INTO users (first_name, last_name, login, password, roles) values ('Jacob', 'Smith', 'jacob-smith', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 'TEACHER');
INSERT INTO users (first_name, last_name, login, password, roles) values ('Mason', 'Johnson', 'mason-johnson', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 'TEACHER');

INSERT INTO groups (group_name) values ('it-01');
INSERT INTO groups (group_name) values ('it-02');
INSERT INTO groups (group_name) values ('it-03');
INSERT INTO groups (group_name) values ('up-01');
INSERT INTO groups (group_name) values ('up-02');
INSERT INTO groups (group_name) values ('up-03');

INSERT INTO courses (course_name) values ('Math');
INSERT INTO courses (course_name) values ('Biology');
INSERT INTO courses (course_name) values ('Philology');
INSERT INTO courses (course_name) values ('History');
INSERT INTO courses (course_name) values ('Economy');
INSERT INTO courses (course_name) values ('Physics');
INSERT INTO courses (course_name) values ('Chemistry');
INSERT INTO courses (course_name) values ('Geography');
INSERT INTO courses (course_name) values ('Psychology');
INSERT INTO courses (course_name) values ('Computer science');

INSERT INTO users_groups values (6,1);
INSERT INTO users_groups values (7,2);

INSERT INTO users_courses values (8,1);
INSERT INTO users_courses values (9,2);

INSERT INTO classrooms (classroom_number) values (11);
INSERT INTO classrooms (classroom_number) values (12);
INSERT INTO classrooms (classroom_number) values (13);
INSERT INTO classrooms (classroom_number) values (14);
INSERT INTO classrooms (classroom_number) values (21);
INSERT INTO classrooms (classroom_number) values (22);
INSERT INTO classrooms (classroom_number) values (23);
INSERT INTO classrooms (classroom_number) values (24);

INSERT INTO lesson_slot (lesson_slot_number, min_start) values (1, 510);
INSERT INTO lesson_slot (lesson_slot_number, min_start) values (2, 610);
INSERT INTO lesson_slot (lesson_slot_number, min_start) values (3, 740);
INSERT INTO lesson_slot (lesson_slot_number, min_start) values (4, 840);
INSERT INTO lesson_slot (lesson_slot_number, min_start) values (5, 950);
INSERT INTO lesson_slot (lesson_slot_number, min_start) values (6, 1050);
