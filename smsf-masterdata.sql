CREATE SCHEMA `smsf_master_data` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
-- DROP TABLE IF EXISTS `t_employee`;
-- CREATE TABLE `t_employee` (
--  `avatar` varchar(255) DEFAULT NULL,
--  `direct_manager_id` varchar(50)  DEFAULT NULL,
--  `id` varchar(50) NOT NULL,
--  `mail` varchar(100) DEFAULT NULL,
--  `name` varchar(100) NOT NULL,
--  `organization` varchar(255)  DEFAULT NULL,
--  `status` tinyint(1) DEFAULT NULL,
--  PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `smsf_master_data`.`t_tat`;
CREATE TABLE `smsf_master_data`.`t_tat` (
  `id` varchar(50) NOT NULL,
  `process_id` varchar(50) DEFAULT NULL,
  `tat` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `smsf_master_data`.`t_client_service_level`;
CREATE TABLE `smsf_master_data`.`t_client_service_level` (
  `id` varchar(50) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `ety` decimal(65,0) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(50) DEFAULT NULL,
  `process_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `smsf_master_data`.`t_company_service_level`;
CREATE TABLE `smsf_master_data`.`t_company_service_level` (
  `id` varchar(50) NOT NULL,
  `parent_id` varchar(50) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `smsf_master_data`.`t_cutoff_time`;
CREATE TABLE `smsf_master_data`.`t_cutoff_time` (
  `id` varchar(50) NOT NULL,
  `process_id` varchar(50) DEFAULT NULL,
  `time` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `smsf_master_data`.`t_spt`;
CREATE TABLE `smsf_master_data`.`t_spt` (
  `id` varchar(50) NOT NULL,
  `process_id` varchar(50) DEFAULT NULL,
  `step_id` varchar(50) DEFAULT NULL,
  `spt` decimal(18,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `smsf_master_data`.`t_step`;
CREATE TABLE `smsf_master_data`.`t_step` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `process_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `smsf_master_data`.`t_process`;
CREATE TABLE `smsf_master_data`.`t_process` (
  `id` varchar(50) NOT NULL,
  `company_service_level_id` varchar(50) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- DROP TABLE IF EXISTS `t_businesstype`;
-- CREATE TABLE `t_businesstype` (
--  `ID` varchar(50) NOT NULL,
--  `BUSINESSCODE` varchar(100) DEFAULT NULL,
--  `NAME` text,
--  `PROCESSID` varchar(50) DEFAULT NULL,
--  `TRACKERCFG_ID` varchar(50) DEFAULT NULL,
--  `PID` varchar(50) DEFAULT NULL,
--  `PNAME` text,
--  `HAVEPERNISSION` tinyint(4) NOT NULL,
--  `VALUE1` varchar(100) DEFAULT NULL,
--  `VALUE2` varchar(100) DEFAULT NULL,
--  `VALUE3` varchar(100) DEFAULT NULL,
--  `VALUE4` varchar(100) DEFAULT NULL,
--  `VALUE5` varchar(100) DEFAULT NULL,
--  PRIMARY KEY (`ID`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8;