CREATE TABLE `daren`.`healdine` (
  `headline_pk` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NULL,
  `title` VARCHAR(128) NULL,
  `description` VARCHAR(1024) NULL,
  `delete_flag` SMALLINT NULL DEFAULT 0,
  `select_flag` SMALLINT NULL DEFAULT 0,
  `flag` SMALLINT NULL DEFAULT 0,
  `author` VARCHAR(64) NULL,
  `seperator` VARCHAR(8) NULL,
  `create_time` TIMESTAMP NULL,
  `update_time` TIMESTAMP NULL,
  PRIMARY KEY (`headline_pk`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;