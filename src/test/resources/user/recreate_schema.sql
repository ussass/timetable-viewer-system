DROP TABLE IF EXISTS courses CASCADE;
DROP TABLE IF EXISTS groups CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE courses
(
    course_id   BIGSERIAL    NOT NULL PRIMARY KEY,
    course_name VARCHAR(255) NOT NULL
);

CREATE TABLE groups
(
    group_id   BIGSERIAL    NOT NULL PRIMARY KEY,
    group_name VARCHAR(255) NOT NULL
);

CREATE TABLE users
(
    user_id         BIGSERIAL    NOT NULL PRIMARY KEY,
    first_name      VARCHAR(255) NOT NULL,
    last_name       VARCHAR(255) NOT NULL,
    login           VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    "course_id"     BIGINT REFERENCES courses (course_id),
    "group_id"      BIGINT REFERENCES groups (group_id),
    roles           VARCHAR(255) NOT NULL
);
