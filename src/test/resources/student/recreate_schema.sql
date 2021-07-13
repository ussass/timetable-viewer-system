DROP TABLE IF EXISTS students CASCADE;

CREATE TABLE IF NOT EXISTS students(
       student_id SERIAL NOT NULL PRIMARY KEY,
       group_id INTEGER NOT NULL,
       first_name VARCHAR(255) NOT NULL,
       last_name VARCHAR(255) NOT NULL
);