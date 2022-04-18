insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`,`job`) values(1, 'martin',10 ,'A',2005,1,21,'programmers');
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`) values(2, 'david' ,11 ,'B',2020,7,11);
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`) values(3, 'dennis',12 ,'O',1989,8,11);
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`) values(4, 'sophia',13 ,'AB',1991,3,11);
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`) values(5, 'benny' ,24 ,'B',1980,1,21);
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`) values(6, 'jacky' ,9  ,'O',1991,12,11);

insert into block(`id`,`name`) values (1, 'dennis');
insert into block(`id`,`name`) values (2,'sophia');

update person set block_id = 1 where id = 3;
update person set block_id = 2 where id = 4;
