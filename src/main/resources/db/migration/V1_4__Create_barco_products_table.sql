# DROP SEQUENCE IF EXISTS `barco_products_seq`;
# CREATE SEQUENCE `barco_products_seq`;

DROP TABLE IF EXISTS `barco_products`;

CREATE TABLE `barco_products` (
   #`id` BIGINT(21) DEFAULT nextval(`barco_products_seq`),
   `id` bigint(21) unsigned NOT NULL AUTO_INCREMENT,
   `barco_id` bigint(10) NOT NULL,
   `product_id` bigint(10) NOT NULL,
   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `barco_id` (`barco_id`),
 KEY `product_id` (`product_id`),
 KEY `created_at` (`created_at`),
 KEY `updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

