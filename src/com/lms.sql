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
    authorname varchar(50) default null,
    subjecttitle varchar(50) default null,
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
    USER_ID INT DEFAULT 1,
    isUpdated int default 1,
    isCreated int default 1,
    idBook int default 1
);
INSERT INTO SESSION(user_id) values (1);
INSERT INTO BOOKS (TITLE, ISBN, PUBLISH_DATE,authorname,subjecttitle,FINE_PER_DAY)
VALUES ('Data Structure', '139780470383278', '2009','deitel and deitel','linear algebra',5000),
       ('discreta math', '546464646464646', '2006','Rosetta Stone','Java application',300),
       ('linear algebra', '646468468454', '2016','James Bond','Discrete math',2000),
       ('life', '544649498498', '2019','Kulibali Kiosota','English',10000);
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
drop table SESSION;
insert into SUBJECT_BOOK(BOOK_ID, SUBJECT_ID)
VALUES (2, 4),
       (4, 2),
       (3, 3),
       (1, 1),
       (3, 2),
       (2, 1),
       (1, 2);
insert into USERS(university_id,first_name,last_name,password,email,role)
values ('admin','admin','admin','admin','',3),
       ('u16516','Kamol','Alimov','lib','',2),
       ('u1810197','Mirshod','Mirjonov','2241323m','',1),
       ('u1810278','Kamoliddin','Chinaliev','12345','',1),
       ('u1810240','Jurabek','Uzakov','12345','',1),
       ('u1810000','Joker','Hulk','12345','',1);
