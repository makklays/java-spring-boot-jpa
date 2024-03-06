# DROP SEQUENCE IF EXISTS `transportations_seq`;
# CREATE SEQUENCE `transportations_seq`;

DROP TABLE IF EXISTS `transportations`;

CREATE TABLE `transportations` (
   #`id` BIGINT(21) DEFAULT nextval(`transportations_seq`),
   `id` bigint(21) unsigned NOT NULL AUTO_INCREMENT,
   `barco_id` bigint(10) NOT NULL,
   `storehouse_id` bigint(10) NOT NULL,
   `distance` int(10) NOT NULL,
   `weight` int(10) NOT NULL,
   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `barco_id` (`barco_id`),
 KEY `storehouse_id` (`storehouse_id`),
 KEY `distance` (`distance`),
 KEY `weight` (`weight`),
 KEY `created_at` (`created_at`),
 KEY `updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

