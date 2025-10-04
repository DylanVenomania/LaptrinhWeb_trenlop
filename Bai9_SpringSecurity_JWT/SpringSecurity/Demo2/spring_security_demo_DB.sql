CREATE DATABASE spring_security_demo;
USE spring_security_demo



CREATE TABLE users (
    id INT PRIMARY KEY IDENTITY(1,1),
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BIT DEFAULT 1
);

CREATE TABLE roles (
    id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);

-- Thêm dữ liệu mẫu
INSERT INTO roles (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');
INSERT INTO users (username, password, enabled) VALUES 
    ('admin', '$2a$10$randomSalt1234567890abcde', 1),
    ('user', '$2a$10$randomSalt1234567890abcdef', 1);
INSERT INTO user_roles (user_id, role_id) VALUES 
    (1, 2), -- admin có ROLE_ADMIN
    (2, 1); -- user có ROLE_USER