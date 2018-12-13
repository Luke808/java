-- t_client_service_level
insert into t_businesstype
select *
from `xccelerator_prd_v2_md`.t_businesstype;

-- t_process
insert into `t_process` (
`id`,
`name`
) select
`ID`,
`PROCESS_NAME`
FROM `xccelerator_prd_v2_md`.`t_process`;

-- t_step
insert into `t_step` (
`id`,
`name`,
`process_id`
) select
`ID`,
`STEP_NAME`,
`PROCESS_ID`
FROM `xccelerator_prd_v2_md`.`t_step`;

-- t_tat
insert into `t_tat` (
`id`,
`process_id`,
`tat`
) select
`ID`,
`PROCESS_ID`,
`TAT`
FROM `xccelerator_prd_v2_md`.`t_tat`;

-- t_cutoff_time
insert into `t_cutoff_time` (
`id`,
`process_id`,
`time`
) select
`ID`,
`PROCESS_ID`,
`CUT_OFF_TIME`
FROM `xccelerator_prd_v2_md`.`t_cut_off_time`;

-- t_spt
insert into `t_spt` (
`id`,
`process_id`,
`spt`
) select
`ID`,
`PROCESS_ID`,
`SPT`
FROM `xccelerator_prd_v2_md`.`t_spt`;

-- t_employee
insert into `t_employee` (
`avatar`,
`direct_manager_id`,
`id`,
`mail`,
`name`,
`organization`,
`status`
) select
`AVATAR_URL`,
`DIRECT_MANAGER_ID`,
`ID`,
`USER_MAIL`,
`USER_NAME`,
`TEAM_ID`,
1
FROM `xccelerator_prd_v2_md`.`t_user_employee`;