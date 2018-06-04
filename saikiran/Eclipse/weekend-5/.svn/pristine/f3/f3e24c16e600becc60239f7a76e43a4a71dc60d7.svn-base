create table mainTask(
    mainTaskId number(5) primary key,
    mainTaskName varchar(50),
    mainTaskDesc varchar(100),
    noOfSubtasks number(10),
    persId number(5)  
);
create table subTask(
    mainTaskId number(5) references mainTask(mainTaskId),
    subTaskId number(5),
    subTaskName varchar(50),
    subTaskDesc varchar(100),
    persId number(5),
    time_estimated number(5)
);
create table Pers(
    persId number(10),
    name varchar(20),
    Age number(2),
    desi varchar(20)
);
alter table pers add CONSTRAINT unique_pk_pers unique (persId);
alter table pers add CONSTRAINT composite_pk_pers primary key(name,Age);
alter table subtask add CONSTRAINT primar_sub primary key(subTaskId);
alter table mainTask add CONSTRAINT fk_main_pers FOREIGN key(persId) references Pers(persId);
alter table subTask add CONSTRAINT fk_sub_pers FOREIGN key(persId) references Pers(persId);
create sequence main_task_seq start with 1 increment by 1 nocycle;
create sequence sub_task_seq start with 1 increment by 1 nocycle;
create sequence per_id start with 1 increment by 1 nocycle;

create or replace procedure insert_mainTask( mainTaskId number, mainTaskName varchar,mainTaskDesc varchar,noOfSubtasks number,persId number)
is
begin
 insert into mainTask values (mainTaskId, mainTaskName ,mainTaskDesc ,noOfSubtasks,persId);
 commit;
end;
/
create or replace procedure insert_subTask( mainTaskId number,subTaskId number,subTaskName varchar,subTaskDesc varchar,persId number,time_estimated number)
is
begin
 insert into subTask values (mainTaskId,subTaskId,subTaskName ,subTaskDesc,persId,time_estimated);
 commit;
end;
/

create or replace procedure insert_pers(per number,Name varchar,Age number ,desi varchar)
is
begin
 insert into pers values (per,Name,Age,desi );
 commit;
end;
/
