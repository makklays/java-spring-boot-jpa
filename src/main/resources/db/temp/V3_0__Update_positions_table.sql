
ALTER TABLE `positions` DROP COLUMN `code`;
ALTER TABLE `positions` ADD COLUMN `code_pos` varchar(25) NULL AFTER `title`;

# Example RENAME column
# ALTER TABLE `positions` RENAME COLUMN `code` TO `code_pos` ;

