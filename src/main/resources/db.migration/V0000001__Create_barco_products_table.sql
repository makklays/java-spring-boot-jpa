DROP TABLE IF EXISTS `barco_products`;

CREATE TABLE `barco_products` (
   `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
   `barco_id` int(10) NOT NULL,
   `product_id` int(10) NOT NULL,
   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `barco_id` (`barco_id`),
 KEY `product_id` (`product_id`),
 KEY `created_at` (`created_at`)
 KEY `updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

