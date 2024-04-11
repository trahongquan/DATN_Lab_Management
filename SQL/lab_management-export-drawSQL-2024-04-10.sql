CREATE TABLE `experiment_type`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `type_name` VARCHAR(255) NOT NULL,
    `experiment_group_id` INT NOT NULL,
    `scroce` DOUBLE NOT NULL
);
CREATE TABLE `equipment_status`(
    `id` INT NOT NULL,
    `equi_id` INT NOT NULL,
    `seri` VARCHAR(255) NOT NULL,
    `status` TINYINT NOT NULL,
    `fixed` TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `date_fixed` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL COMMENT 'list nội dung sửa chữa, tương ứng theo list ngày sửa chữa',
    PRIMARY KEY(`id`)
);
CREATE TABLE `experiment_group`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `group_name` VARCHAR(255) NOT NULL
);
CREATE TABLE `authorites`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) NOT NULL,
    `authority` VARCHAR(255) NOT NULL
);
CREATE TABLE `users`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `people_id` INT NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `enabled` TINYINT NOT NULL
);
CREATE TABLE `equipment_lab`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `lab_id` INT NOT NULL,
    `equi_id` INT NOT NULL,
    `equi_series` VARCHAR(255) NOT NULL
);
CREATE TABLE `equipment`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `series` VARCHAR(255) NOT NULL,
    `type` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `status` INT NOT NULL,
    `quantity` INT NOT NULL,
    `is_deleted` TINYINT NOT NULL
);
CREATE TABLE `people`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `rank` VARCHAR(255) NOT NULL,
    `unit` VARCHAR(255) NOT NULL,
    `military_number` INT NOT NULL,
    `contact` VARCHAR(255) NOT NULL,
    `is_delete` TINYINT NOT NULL
);
CREATE TABLE `content`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `reservationist_id` INT NOT NULL,
    `experiment_type` INT NOT NULL,
    `class_name` VARCHAR(255) NOT NULL,
    `amount_of_people` BIGINT NOT NULL,
    `list_id_Participants` VARCHAR(255) NOT NULL
);
CREATE TABLE `roles`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `role` VARCHAR(255) NOT NULL
);
CREATE TABLE `Lab`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Lab_name` VARCHAR(255) NOT NULL,
    `capacity` INT NOT NULL,
    `location` VARCHAR(255) NOT NULL,
    `lab_managemet_id` INT NOT NULL,
    `is_delete` TINYINT NOT NULL
);
CREATE TABLE `booking`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `lab_id` INT NOT NULL,
    `booking_equi_id` INT NOT NULL,
    `content_id` INT NOT NULL,
    `booking_date` BIGINT NOT NULL,
    `comfirm_status` BIGINT NOT NULL,
    `work_times` INT NOT NULL,
    `note` VARCHAR(255) NOT NULL,
    `is_delete` TINYINT NOT NULL
);
CREATE TABLE `booking_equi`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `equi_id` INT NOT NULL,
    `equi_series` VARCHAR(255) NOT NULL,
    `booking_id` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `booking` ADD CONSTRAINT `booking_lab_id_foreign` FOREIGN KEY(`lab_id`) REFERENCES `Lab`(`id`);
ALTER TABLE
    `equipment_lab` ADD CONSTRAINT `equipment_lab_equi_id_foreign` FOREIGN KEY(`equi_id`) REFERENCES `equipment`(`id`);
ALTER TABLE
    `Lab` ADD CONSTRAINT `lab_lab_managemet_id_foreign` FOREIGN KEY(`lab_managemet_id`) REFERENCES `people`(`id`);
ALTER TABLE
    `users` ADD CONSTRAINT `users_username_foreign` FOREIGN KEY(`username`) REFERENCES `authorites`(`username`);
ALTER TABLE
    `users` ADD CONSTRAINT `users_people_id_foreign` FOREIGN KEY(`people_id`) REFERENCES `people`(`id`);
ALTER TABLE
    `content` ADD CONSTRAINT `content_reservationist_id_foreign` FOREIGN KEY(`reservationist_id`) REFERENCES `people`(`id`);
ALTER TABLE
    `booking` ADD CONSTRAINT `booking_booking_equi_id_foreign` FOREIGN KEY(`booking_equi_id`) REFERENCES `booking_equi`(`id`);
ALTER TABLE
    `equipment_lab` ADD CONSTRAINT `equipment_lab_lab_id_foreign` FOREIGN KEY(`lab_id`) REFERENCES `Lab`(`id`);
ALTER TABLE
    `experiment_type` ADD CONSTRAINT `experiment_type_experiment_group_id_foreign` FOREIGN KEY(`experiment_group_id`) REFERENCES `experiment_group`(`id`);
ALTER TABLE
    `booking` ADD CONSTRAINT `booking_content_id_foreign` FOREIGN KEY(`content_id`) REFERENCES `content`(`id`);
ALTER TABLE
    `content` ADD CONSTRAINT `content_experiment_type_foreign` FOREIGN KEY(`experiment_type`) REFERENCES `experiment_type`(`id`);