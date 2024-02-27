DROP TABLE IF EXISTS `storehouse_barcos`;

CREATE TABLE `storehouse_barcos` (
   `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
   `storehouse_id` int(10) NOT NULL,
   `barco_id` int(10) NOT NULL,
   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `storehouse_id` (`storehouse_id`),
 KEY `barco_id` (`barco_id`),
 KEY `created_at` (`created_at`)
 KEY `updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

