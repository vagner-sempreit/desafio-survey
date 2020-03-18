CREATE TABLE `poll` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(50) ,
	`title` varchar(50) NOT NULL ,
	`start_date` datetime  NOT NULL,
	`end_date` datetime NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `UK_poll_title` (`title`),
	INDEX `idx_poll_title` (`title`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `poll_id` bigint(20) NOT NULL,
  `description` varchar(50) NOT NULL,
  `amount` bigint(20) DEFAULT 0,
  `input_date` datetime  NOT NULL,
  `last_updated_date` datetime NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_question_poll_id` (`poll_id`),
  CONSTRAINT `FK_question_poll_id` FOREIGN KEY (`poll_id`) REFERENCES `poll` (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `poll_id` bigint(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `input_date` datetime  NOT NULL,
  `last_updated_date` datetime NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_person_poll_id` (`poll_id`),
  CONSTRAINT `FK_person_poll_id` FOREIGN KEY (`poll_id`) REFERENCES `poll` (`id`)
) DEFAULT CHARSET=utf8;



