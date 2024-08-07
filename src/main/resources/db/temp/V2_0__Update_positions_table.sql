
ALTER TABLE `positions` ADD COLUMN `code` varchar(25) NULL AFTER `title`;

INSERT INTO `positions` (`title`, `description`, `created_at`, `updated_at`) VALUE ('Marinero', 'Description un marinero para algun barco.', '2024-02-21 18:19:03', '2024-02-21 18:19:03');
INSERT INTO `positions` (`title`, `description`, `created_at`, `updated_at`) VALUE ('Pisition 2', 'Description position 2', '2024-02-21 18:20:04', '2024-02-21 18:20:04');
INSERT INTO `positions` (`title`, `description`, `created_at`, `updated_at`) VALUE ('Pisition 3', 'Description position 3', '2024-02-21 18:21:05', '2024-02-21 18:21:05');

