CREATE DATABASE IF NOT EXISTS hello_world;
USE hello_world;
CREATE TABLE IF NOT EXISTS users(
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    birth datetime,
    PRIMARY KEY (id)
);
