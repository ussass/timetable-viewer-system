CREATE TABLE IF NOT EXISTS students(
    student_id SERIAL NOT NULL CONSTRAINT students_pkey PRIMARY KEY,
    group_id INTEGER NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS teachers(
    teacher_id SERIAL NOT NULL CONSTRAINT students_pkey PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS groups(
    group_id SERIAL NOT NULL CONSTRAINT groups_pkey PRIMARY KEY,
    group_name VARCHAR(255) NOT NULL,
    );
