# DROP SEQUENCE IF EXISTS `positions_seq`;
# CREATE SEQUENCE `positions_seq`;

DROP TABLE IF EXISTS positions;

CREATE TABLE `positions` (
   #`id` BIGINT(21) DEFAULT nextval(`positions_seq`),
   `id` bigint(21) unsigned NOT NULL AUTO_INCREMENT,
   `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `title` (`title`),
 KEY `description` (`description`),
 KEY `created_at` (`created_at`),
 KEY `updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

