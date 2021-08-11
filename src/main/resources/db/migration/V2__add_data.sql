-- admin/admin
INSERT INTO users (first_name, last_name, login, password, roles) values ('The', 'Architect', 'admin', '$2a$10$csznBGiCXAQ.v4QyRSkbV.x8//TvJISzzRse21AgHTIgQoVF3jpfK', 'ADMIN');
-- xxx/123
INSERT INTO users (first_name, last_name, login, password, roles) values ('The', 'Student', 'student', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 'STUDENT');
INSERT INTO users (first_name, last_name, login, password, roles) values ('The', 'Teacher', 'teacher', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 'TEACHER');
INSERT INTO users (first_name, last_name, login, password, roles) values ('The', 'Stuff', 'stuff', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 'STUFF');

INSERT INTO groups (group_name) values ('it-01');
INSERT INTO groups (group_name) values ('up-11');

INSERT INTO students (group_id, first_name, last_name) values (2, 'Bruce', 'Banner');
INSERT INTO students (group_id, first_name, last_name) values (1, 'Tony', 'Stark');

INSERT INTO teachers (first_name, last_name) values ('Jacob', 'Smith');
INSERT INTO teachers (first_name, last_name) values ('Mason', 'Johnson');

INSERT INTO classrooms (classroom_number) values (101);
INSERT INTO classrooms (classroom_number) values (201);

INSERT INTO lesson_slot (lesson_slot_number) values (1);
INSERT INTO lesson_slot (lesson_slot_number) values (2);

INSERT INTO courses (course_name) values ('Math');
INSERT INTO courses (course_name) values ('Biology');

INSERT INTO classes (course_id, teacher_id, group_id, classroom_id, lesson_slot_id, classes_date) values (1,1,1,1,1,1618898400000);
INSERT INTO classes (course_id, teacher_id, group_id, classroom_id, lesson_slot_id, classes_date) values (2,2,2,2,1,1618898400000);
INSERT INTO classes (course_id, teacher_id, group_id, classroom_id, lesson_slot_id, classes_date) values (1,1,2,1,2,1618904400000);
INSERT INTO classes (course_id, teacher_id, group_id, classroom_id, lesson_slot_id, classes_date) values (2,2,1,2,2,1618904400000);




