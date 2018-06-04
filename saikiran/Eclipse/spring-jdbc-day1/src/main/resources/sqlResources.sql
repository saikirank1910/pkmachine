CREATE TABLE empdetails (
    ID int NOT NULL,
    name varchar(30) NOT NULL,
    deptid int not null,
    Age int,
    PRIMARY KEY (ID)
);
create table deptdetails(
    id int not null,
    deptname varchar(20),
    primary key(id)
);

ALTER TABLE empdetails
ADD CONSTRAINT fk_key_dept
  FOREIGN KEY (deptid)
  REFERENCES deptdetails(id);
