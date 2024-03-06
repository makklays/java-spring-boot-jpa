# DROP SEQUENCE IF EXISTS `products_seq`;
# CREATE SEQUENCE `products_seq`;

DROP TABLE IF EXISTS products;

CREATE TABLE `products` (
   #`id` BIGINT(21) DEFAULT nextval(`products_seq`),
   `id` bigint(21) unsigned NOT NULL AUTO_INCREMENT,
   `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   `weight` int(10) NOT NULL,
   `is_dangerous` int(2) DEFAULT 0,
   `is_glass` int(2) DEFAULT 0,
   `category_id` bigint(10) NOT NULL,
   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `title` (`title`),
 KEY `description` (`description`),
 KEY `created_at` (`created_at`),
 KEY `updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

