INSERT INTO groups (group_name) values ('it-01_test');
INSERT INTO groups (group_name) values ('it-02_test');

INSERT INTO courses (course_name) values ('Math');

INSERT INTO users (first_name, last_name, login, password, roles) values ('The', 'Architect', 'admin', '$2a$10$csznBGiCXAQ.v4QyRSkbV.x8//TvJISzzRse21AgHTIgQoVF3jpfK', 'ADMIN');
INSERT INTO users (first_name, last_name, login, password, group_id, roles) values ('The', 'Student', 'student', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 1, 'STUDENT');
INSERT INTO users (first_name, last_name, login, password, course_id, roles) values ('The', 'Teacher', 'teacher', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 1, 'TEACHER');
INSERT INTO users (first_name, last_name, login, password, roles) values ('The', 'Stuff', 'stuff', '$2a$10$EOv4C4b69cJf1Jx9YCKLCe3No7Rui1CTywg6rFw5/i4yhHF/GIR2S', 'STUFF');
