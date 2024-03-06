# DROP SEQUENCE IF EXISTS `storehouse_barcos_seq`;
# CREATE SEQUENCE `storehouse_barcos_seq`;

DROP TABLE IF EXISTS `storehouse_barcos`;

CREATE TABLE `storehouse_barcos` (
   #`id` BIGINT(21) DEFAULT nextval(`storehouse_barcos_seq`),
   `id` bigint(21) unsigned NOT NULL AUTO_INCREMENT,
   `storehouse_id` bigint(10) NOT NULL,
   `barco_id` bigint(10) NOT NULL,
   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `storehouse_id` (`storehouse_id`),
 KEY `barco_id` (`barco_id`),
 KEY `created_at` (`created_at`),
 KEY `updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

