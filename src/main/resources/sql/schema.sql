DROP TABLE IF EXISTS `customer_address`;
CREATE TABLE `customer_address` (
    `customer_id` BIGINT NOT NULL,
    `address_type` VARCHAR(12) NOT NULL,
    `postal_code` VARCHAR(6) NOT NULL,
    `street` VARCHAR(120) NOT NULL,
    `city` VARCHAR(20) NOT NULL
);

DROP TABLE IF EXISTS `customer_details`;
CREATE TABLE `customer_details`(
    `id` BIGINT NOT NULL,
    `birth_place` VARCHAR(100) NOT NULL,
    `birth_day` DATETIME NOT NULL,
    `father_name` VARCHAR(50),
    `mother_name` VARCHAR(50),
    `pesel` VARCHAR(11),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id`) REFERENCES customer(`id`)
);
