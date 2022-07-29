
INSERT INTO `chores1`.`user`
(`id`,`app_user_role`,`dob`,`email`,`enabled`,`first_name`,`last_name`,`locked`,`password`,`group_id`)
VALUES
(1,'ADMIN','8/1/1985','rob@rob.com',false,'Robert','Reed',false,'password',null),
(2,'ADMIN','2/3/1983','jessie@jessie.com',false,'Jessie','Reed',false,'password',null),
(3,'USER','10/13/2015','jayne@jayne.com',false,'Jayne','Reed',false,'password',null),
(4,'USER','1/2/2012','aiden@aiden.com',false,'Aiden','Chambers',false,'password',null),
(5,'USER','2/10/2009','iam@iam.com',false,'Iam','Chambers',false,'password',null);

UPDATE `chores1`.`user_sequence`
SET `next_val` = 6
WHERE `next_val` = 1;

INSERT INTO `chores1`.`user_group`
(`id`,
`user_id`,
`created`)
VALUES
(1,
1,
now());
UPDATE `chores1`.`user_group_sequence`
SET `next_val` = 2
WHERE `next_val` = 1;

UPDATE `chores1`.`user`
SET `group_id` = 1
WHERE `id` BETWEEN 0 AND 6;

INSERT INTO `chores1`.`chore`
(`id`,`chore_level`,`frequency`,`name`,`scope`,`group_id`)
VALUES
(1,'EASY','WEEKLY','Laundry','PERSONAL',1),
(2,'EASY','WEEKLY','Cook a Meal','PERSONAL',1),
(3,'EASY','WEEKLY','Clean Room','PERSONAL',1),
(4,'EASY','WEEKLY','Vacuum','PERSONAL',1),
(5,'EASY','WEEKLY','Clean Litter Box','PERSONAL',1),
(6,'EASY','WEEKLY','Mop','PERSONAL',1),
(7,'EASY','WEEKLY','Clean Bathroom','PERSONAL',1),
(8,'EASY','WEEKLY','Little Trash Cans','PERSONAL',1),
(9,'EASY','WEEKLY','Collect Dishes','PERSONAL',1),
(10,'EASY','DAILY','Wash Dishes','PERSONAL',1),
(11,'EASY','DAILY','Sweep','PERSONAL',1),
(12,'EASY','DAILY','Feed Animals','PERSONAL',1),
(13,'EASY','WEEKLY','Scoop Poop','GROUP',1),
(14,'EASY','WEEKLY','Take Trash to Dump','GROUP',1),
(15,'EASY','WEEKLY','Pick Up Back Yard','GROUP',1),
(16,'EASY','WEEKLY','Clean Microwave','GROUP',1);

UPDATE `chores1`.`chore_sequence`
SET `next_val` = 17
WHERE `next_val` = 1;

INSERT INTO `chores1`.`assignment`
(`id`,`chore_id`,`user_id`)
VALUES
(1,1,3),
(2,1,4),
(3,1,5),
(4,2,3),
(5,2,4),
(6,2,5),
(7,3,3),
(8,3,4),
(9,3,5),
(10,4,4),
(11,5,4),
(12,6,5),
(13,7,5),
(14,8,3),
(15,9,3),
(16,10,4),
(17,10,4),
(18,10,4),
(19,10,4),
(20,10,4),
(21,11,5),
(22,11,5),
(23,11,5),
(24,11,5),
(25,11,5),
(26,12,3),
(27,12,3),
(28,12,3),
(29,12,3),
(30,12,3);

UPDATE `chores1`.`assignment_sequence`
SET
`next_val` = 31
WHERE `next_val` = 1;
