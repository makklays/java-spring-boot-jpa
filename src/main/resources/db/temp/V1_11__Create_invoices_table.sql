# DROP SEQUENCE IF EXISTS `invoices_seq`;
# CREATE SEQUENCE `invoices_seq`;

DROP TABLE IF EXISTS invoices;

CREATE TABLE `invoices` (
   #`id` BIGINT(21) DEFAULT nextval(`invoices_seq`),
   `id` bigint(21) unsigned NOT NULL AUTO_INCREMENT,
   `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   `transportation_id` bigint(10) NOT NULL,
   `amount` float(10,2) NOT NULL,
   `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `title` (`title`),
 KEY `description` (`description`),
 KEY `transportation_id` (`transportation_id`),
 KEY `amount` (`amount`),
 KEY `status` (`status`),
 KEY `created_at` (`created_at`),
 KEY `updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

