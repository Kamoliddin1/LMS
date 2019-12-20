CREATE TABLE USERS
(
    ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    university_id varchar(30) not null unique,
    first_name varchar(30),
    last_name varchar(30),
    password varchar(30) not null,
    role int not null,
    email varchar(50)
);
create table BOOKS
(
    ID              INT         NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    TITLE           VARCHAR(30) NOT NULL,
    ISBN            VARCHAR(30) not null,
    PUBLISH_DATE    varchar(5),
    reserved_status smallint default 0,
    borrowed_status smallint default 0,
    student_borrowed_id int default null,
    student_reserved_id int default null,
    borrowed_date date default current date,
    due_day int not null default 1,
    fine_per_day  double default null,
    FOREIGN KEY (student_borrowed_id) REFERENCES USERS (ID) ON DELETE restrict,
    FOREIGN KEY (student_reserved_id) REFERENCES USERS (ID) ON DELETE restrict
);
CREATE TABLE SUBJECTS
(
    ID    INT         NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    TITLE VARCHAR(50) NOT NULL unique
);
CREATE TABLE AUTHORS
(
    ID   INT         NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    NAME VARCHAR(70) NOT NULL unique
);
CREATE TABLE SUBJECT_BOOK
(
    ID         INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    BOOK_ID    INT NOT NULL,
    SUBJECT_ID INT NOT NULL,
    FOREIGN KEY (BOOK_ID) REFERENCES BOOKS (ID) ON DELETE CASCADE,
    FOREIGN KEY (SUBJECT_ID) REFERENCES SUBJECTS (ID) ON DELETE CASCADE
);
CREATE TABLE AUTHOR_BOOK
(
    ID        INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    BOOK_ID   INT NOT NULL,
    AUTHOR_ID INT NOT NULL,
    FOREIGN KEY (BOOK_ID) REFERENCES BOOKS (ID) ON DELETE CASCADE,
    FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS (ID) ON DELETE CASCADE
);
CREATE TABLE SESSION(
    ID INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    USER_ID INT DEFAULT 0,
    isUpdated int default 0,
    isCreated int default 0,
    FOREIGN KEY (USER_ID) REFERENCES USERS (ID) ON DELETE CASCADE
);
INSERT INTO SESSION(user_id) values (1);
INSERT INTO BOOKS (TITLE, ISBN, PUBLISH_DATE)
VALUES ('Data Structure', '139780470383278', '2009'),
       ('discreta math', '546464646464646', '2006'),
       ('linear algebra', '646468468454', '2016'),
       ('life', '544649498498', '2019');
insert into AUTHORS (NAME)
values ('kenndy alxim'),
       ('kamol niyoziy'),
       ('mirshod qoshgariy'),
       ('jorabek nasafiy');
insert into SUBJECTS(TITLE)
values ('mathematics'),--1
       ('english'),--2
       ('data science'),--3
       ('magazines');--4
insert into AUTHOR_BOOK(BOOK_ID, AUTHOR_ID)
VALUES (1, 4),
       (1, 2),
       (1, 3),
       (1, 1),
       (2, 2),
       (3, 1),
       (4, 2);
insert into SUBJECT_BOOK(BOOK_ID, SUBJECT_ID)
VALUES (2, 4),
       (4, 2),
       (3, 3),
       (1, 1),
       (3, 2),
       (2, 1),
       (1, 2);
select * from BOOKS;
insert into USERS(university_id,first_name,last_name,password,email,role)
values ('admin','admin','admin','admin','',3),
       ('u16516','Kamol','Alimov','lib','',2),
       ('u1810197','Mirshod','Mirjonov','2241323m','',1),
       ('u1810278','Kamoliddin','Chinaliev','12345','',1),
       ('u1810240','Jurabek','Uzakov','12345','',1),
       ('u1810000','Joker','Hulk','12345','',1);
select * from USERS;
update books
set borrowed_status = 1, student_borrowed_id = 3, due_day = 3, fine_per_day = 2000
where ID = 1;
update books
set borrowed_status = 1, student_borrowed_id = 4, due_day = 4, fine_per_day = 3000
where ID = 2;
update books
set borrowed_status = 1, student_borrowed_id = 5, due_day = 7, fine_per_day = 4000
where ID = 3;
--view personal details of specific student
select * from users where USERS.university_id = 'u1810197';
--borrowed qigan kitoblari
select * from books b
                  join users u on b.student_borrowed_id = u.ID
where u.university_id = 'u1810197';
--view fine Kamoliddin's work
--filter to be continued
--select * from BOOKS b join SUBJECT_BOOK sb on sb.BOOK_ID = b.id
--  where student_borrowed_id = 0 and title like 'data%structure' and sb.SUBJECT_ID = 1
--reserve borrowed books
select * from books where borrowed_status = 1 and reserved_status = 0;
--view update delete books
select * from BOOKS;
update BOOKS
set TITLE = 'odam anatomiyasi'
where id = 1;
delete from BOOKS where id = 1;
insert into books (TITLE, ISBN, PUBLISH_DATE)
values ('discrete math','668768768','2000');
--view update delete students
select * from USERS where role = 1;
update users
set last_name = 'odam anatomiyasi'
where id = 3;
delete from USERS where id = 1;

insert into users(university_id, first_name, last_name, password, role)
values('u1810197','adsa','adad0','dadsasa',1);
select * from books where borrowed_status = 1;
select u.university_id,u.last_name,u.first_name
from  BOOKS b
          join USERS u on b.student_borrowed_id = u.ID
where ((select timestamp(current_date)));

select timestampdiff('2019-12-14','2019-12-10');
SELECT b.TITLE,b.BORROWED_DATE,b.DUE_DAY,b.FINE_PER_DAY FROM ADMIN.BOOKS b join users u
    on b.STUDENT_BORROWED_ID = u.ID where u.UNIVERSITY_ID = 'u1810197';
alter table USERS
    drop column email;
ALTER TABLE USERS drop column ISUPDATED;
