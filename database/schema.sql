create database course_platform_db;

# User service

CREATE TABLE roles(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
role_name  VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE users(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
role_id BIGINT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT fk_user_role
FOREIGN KEY (role_id)
REFERENCES roles(id)
);

# course service

CREATE TABLE course_categories(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100) NOT NULL UNIQUE
);
CREATE TABLE courses(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(200) NOT NULL,
description TEXT,
level VARCHAR(50),
duration_hours INT,
active BOOLEAN DEFAULT TRUE,
category_id BIGINT,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT fk_course_category
FOREIGN KEY (category_id)
REFERENCES course_categories(id)
);
CREATE TABLE course_modules(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(100) NOT NULL,
video_url VARCHAR(200),
module_order INT,
duration_minutes INT,
course_id BIGINT,
CONSTRAINT fk_course_module
FOREIGN KEY (course_id)
REFERENCES courses(id)
);

# Enrollment service

CREATE TABLE enrollments(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
user_id BIGINT,
course_id BIGINT,
enrolled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
progress_percentage DOUBLE DEFAULT 0,
status VARCHAR(50) DEFAULT 'IN_PROGRESS'
);
CREATE TABLE module_progress(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
enrollment_id BIGINT,
module_id BIGINT,
completed BOOLEAN DEFAULT FALSE,
completed_at TIMESTAMP NULL,
CONSTRAINT fk_enrollment_module
FOREIGN KEY (enrollment_id)
REFERENCES enrollments(id)
);





