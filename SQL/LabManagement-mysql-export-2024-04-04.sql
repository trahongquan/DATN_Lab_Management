CREATE TABLE `users`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) NOT NULL,
    `passwordint` VARCHAR(255) NOT NULL,
    `people_id` INT NOT NULL,
    `is_deleted` TINYINT NOT NULL
);
CREATE TABLE `room`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `room_name` VARCHAR(255) NOT NULL,
    `capacity` INT NOT NULL,
    `location` VARCHAR(255) NOT NULL,
    `leader_id` INT NOT NULL,
    `is_deleted` TINYINT NOT NULL
);
CREATE TABLE `authorities`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) NOT NULL,
    `authority` VARCHAR(255) NOT NULL
);
CREATE TABLE `booking`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `room_id` INT NOT NULL,
    `people_id` INT NOT NULL,
    `booking_date` TIMESTAMP NOT NULL,
    `booking_status` TINYINT NOT NULL
);
CREATE TABLE `equipment`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `equipment_number` VARCHAR(255) NOT NULL,
    `equipment_type` VARCHAR(255) NOT NULL,
    `origin` VARCHAR(255) NOT NULL,
    `production_yearint` INT NOT NULL,
    `status` TINYINT NOT NULL,
    `room_id` INT NOT NULL,
    `is_deleted` TINYINT NOT NULL
);
CREATE TABLE `people`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `rank` VARCHAR(255) NOT NULL,
    `unit` VARCHAR(255) NOT NULL,
    `military_number` BIGINT NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(255) NOT NULL,
    `user_id` INT NOT NULL,
    `is_delete` TINYINT NOT NULL
);
CREATE TABLE `role`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `role` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `booking` ADD CONSTRAINT `booking_people_id_foreign` FOREIGN KEY(`people_id`) REFERENCES `people`(`id`);
ALTER TABLE
    `booking` ADD CONSTRAINT `booking_room_id_foreign` FOREIGN KEY(`room_id`) REFERENCES `room`(`id`);
ALTER TABLE
    `room` ADD CONSTRAINT `room_leader_id_foreign` FOREIGN KEY(`leader_id`) REFERENCES `people`(`id`);
ALTER TABLE
    `people` ADD CONSTRAINT `people_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `users`(`id`);
ALTER TABLE
    `equipment` ADD CONSTRAINT `equipment_room_id_foreign` FOREIGN KEY(`room_id`) REFERENCES `room`(`id`);