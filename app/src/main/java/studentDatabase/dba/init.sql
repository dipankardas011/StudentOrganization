
create database if not exists students;

use students;

create table IF NOT EXISTS student (
    roll INTEGER unsigned PRIMARY KEY AUTO_INCREMENT ,
    name VARCHAR(20) NOT NULL ,
    age INTEGER CHECK ( age < 30 AND age >= 18 ) NOT NULL
)engine=innodb
 default charset=utf8
 default collate=utf8_unicode_ci;

desc student;

insert into student values(default, 'demo name1', 25);

select * from student;
