DROP TABLE IF EXISTS teachers CASCADE;

CREATE TABLE IF NOT EXISTS teachers(
       teacher_id SERIAL NOT NULL PRIMARY KEY,
       first_name VARCHAR(255) NOT NULL,
       last_name VARCHAR(255) NOT NULL
);