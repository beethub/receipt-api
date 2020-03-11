DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
	`ticket_id` bigint(20) NOT NULL AUTO_INCREMENT,
	`image_key` varchar(250) NOT NULL,
	`image_name` varchar(250) NOT NULL,
	`image_content_type` varchar(250) NOT NULL,
	`image_size` bigint(20) NOT NULL,
	 PRIMARY KEY (`ticket_id`)
);

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
	`invoice_id` bigint(20) NOT NULL AUTO_INCREMENT,
	`xml_key` varchar(255) NOT NULL,
	`xml_name` varchar(250) NOT NULL,
	`xml_content_type` varchar(250) NOT NULL,
	`xml_size` bigint(20) NOT NULL,
	`pdf_key` varchar(255) DEFAULT NULL,
	`pdf_name` varchar(250) DEFAULT NULL,
	`pdf_content_type` varchar(250) DEFAULT NULL,
	`pdf_size` bigint(20) DEFAULT NULL,
	PRIMARY KEY (`invoice_id`)
);

DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
	`business_id` bigint(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	`rfc` varchar(15) DEFAULT NULL,
	PRIMARY KEY (`business_id`)
);

DROP TABLE IF EXISTS `receipt`;
CREATE TABLE `receipt` (
	`receipt_id` bigint(20) NOT NULL AUTO_INCREMENT,
	`account` varchar(255) NOT NULL,
	`business_id` bigint(20) DEFAULT NULL,
	`date` datetime DEFAULT NULL,
	`amount` bigint(20) DEFAULT NULL,
	`created_at` datetime NOT NULL,
	`ticket_id` bigint(20) DEFAULT NULL,
	`invoice_id` bigint(20) DEFAULT NULL,
	`status` ENUM('IN_PROGRESS','DONE','ERROR','NOT_APPLICABLE'),
	PRIMARY KEY (`receipt_id`),
	KEY `account_indx` (`account`),
	KEY `business_indx` (`business_id`),
	KEY `status_indx` (`status`),
	CONSTRAINT `busines_constraint` FOREIGN KEY (`business_id`) REFERENCES `business` (`business_id`)
);


