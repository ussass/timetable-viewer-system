DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS groups CASCADE;
DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS teachers CASCADE;
DROP TABLE IF EXISTS classrooms CASCADE;
DROP TABLE IF EXISTS lesson_slot CASCADE;
DROP TABLE IF EXISTS courses CASCADE;
DROP TABLE IF EXISTS classes CASCADE;

CREATE TABLE groups(
    group_id BIGSERIAL NOT NULL PRIMARY KEY,
    group_name VARCHAR(255) NOT NULL
    );

CREATE TABLE users(
    user_id BIGSERIAL NOT NULL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL
    );

CREATE TABLE students(
    student_id BIGSERIAL NOT NULL PRIMARY KEY,
    "group_id" BIGINT REFERENCES groups (group_id),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
    );

CREATE TABLE teachers(
    teacher_id BIGSERIAL NOT NULL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
    );

CREATE TABLE classrooms(
    classroom_id BIGSERIAL NOT NULL PRIMARY KEY,
    classroom_number INTEGER NOT NULL
    );

CREATE TABLE lesson_slot(
    lesson_slot_id BIGSERIAL NOT NULL PRIMARY KEY,
    lesson_slot_number INTEGER NOT NULL
    );

CREATE TABLE courses(
    course_id BIGSERIAL NOT NULL PRIMARY KEY,
    course_name VARCHAR(255) NOT NULL
    );

CREATE TABLE classes(
    classes_id BIGSERIAL NOT NULL PRIMARY KEY,
    "course_id" BIGINT REFERENCES courses (course_id),
    "teacher_id" BIGINT REFERENCES teachers (teacher_id),
    "group_id" BIGINT REFERENCES groups (group_id),
    "classroom_id" BIGINT REFERENCES classrooms (classroom_id),
    "lesson_slot_id" BIGINT REFERENCES lesson_slot (lesson_slot_id),
    classes_date BIGINT
);
