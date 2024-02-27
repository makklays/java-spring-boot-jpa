DROP TABLE IF EXISTS `barco_users`;

CREATE TABLE `barco_users` (
   `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
   `barco_id` int(10) NOT NULL,
   `user_id` int(10) NOT NULL,
   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `barco_id` (`barco_id`),
 KEY `user_id` (`user_id`),
 KEY `created_at` (`created_at`),
 KEY `updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

