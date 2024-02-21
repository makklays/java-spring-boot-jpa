DROP TABLE IF EXISTS storehouse;

CREATE TABLE `storehouse` (
   `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
   `city_id` int(10) NOT NULL,
   `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `city_id` (`city_id`),
 KEY `title` (`title`),
 KEY `description` (`description`),
 KEY `created_at` (`created_at`)
 KEY `updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

