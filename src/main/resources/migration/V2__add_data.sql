create sequence hibernate_sequence start 1 increment 1;
create table classrooms (classroom_id int8 not null, classroom_number int4, primary key (classroom_id));
create table courses (course_id int8 not null, course_name varchar(255), primary key (course_id));
create table groups (group_id int8 not null, group_name varchar(255), primary key (group_id));
create table lesson (lesson_id int8 not null, classroom_id int8, course_id int8, course_name varchar(255), day_of_week int4, group_id int8, group_name varchar(255), lesson_slot_id int8, user_id int8, teacher_name varchar(255), primary key (lesson_id));
create table lesson_slot (lesson_slot_id int8 not null, min_start int4, lesson_slot_number int4, primary key (lesson_slot_id));
create table users (user_id int8 not null, first_name varchar(255), last_name varchar(255), login varchar(255), password varchar(255), roles varchar(255), primary key (user_id));
