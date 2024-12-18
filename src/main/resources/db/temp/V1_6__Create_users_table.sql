
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
   `id` bigint(21) unsigned NOT NULL AUTO_INCREMENT,
   `firstname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   `lastname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   `email` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
   `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
   `bio` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `firstname` (`firstname`),
 KEY `lastname` (`lastname`),
 KEY `email` (`email`),
 KEY `password` (`password`),
 KEY `bio` (`bio`),
 KEY `created_at` (`created_at`),
 KEY `updated_at` (`updated_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

