DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
	`business_id` bigint(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	`rfc` varchar(15) DEFAULT NULL,
	PRIMARY KEY (`business_id`)
);

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
	`invoice_id` bigint(20) NOT NULL AUTO_INCREMENT,
	`xml_key` varchar(255) DEFAULT NULL,
	`pdf_key` varchar(255) DEFAULT NULL,
	PRIMARY KEY (`invoice_id`)
);

DROP TABLE IF EXISTS `receipt`;
CREATE TABLE `receipt` (
	`receipt_id` bigint(20) NOT NULL AUTO_INCREMENT,
	`account` varchar(255) NOT NULL,
	`business_id` bigint(20) DEFAULT NULL,
	`date` datetime DEFAULT NULL,
	`amount` bigint(20) DEFAULT NULL,
	`created_at` datetime NOT NULL,
	`ticket_key` varchar(255) DEFAULT NULL,
	`invoice_id` bigint(20) DEFAULT NULL,
	`status` ENUM('IN_PROGRESS', 'GENERATING','DONE','ERROR'),
	`text_search` varchar(1000) DEFAULT NULL,
	PRIMARY KEY (`receipt_id`),
	FULLTEXT KEY `text_indx` (`text_search`),
	KEY `account_indx` (`account`),
	KEY `business_indx` (`business_id`),
	KEY `status_indx` (`status`),
	CONSTRAINT `busines_constraint` FOREIGN KEY (`business_id`) REFERENCES `business` (`business_id`),
	CONSTRAINT `invoice_constraint` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`invoice_id`)
);


