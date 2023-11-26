use EmployeeSeatDB;

CREATE TABLE SeatingChart
(
FLOOR_SEAT_SEQ INT IDENTITY(1,1) PRIMARY KEY,
FLOOR_NO INT NOT NULL,
SEAT_NO INT NOT NULL,
SEAT_STATUS VARCHAR(50) NOT NULL
);

CREATE TABLE Employee
(
EMP_ID CHAR(5) PRIMARY KEY,
NAME VARCHAR(100) NOT NULL,
EMAIL VARCHAR(255),
FLOOR_SEAT_SEQ INT FOREIGN KEY REFERENCES SeatingChart(FLOOR_SEAT_SEQ)
);


INSERT INTO Employee (EMP_ID, NAME, EMAIL, FLOOR_SEAT_SEQ)
VALUES
    ('10001', 'mary', 'emp1@gmail.com', null),
    ('10002', 'jack', 'emp2@gmail.com', null),
    ('10003', 'may', 'emp3@gmail.com', null),
    ('10004', 'john', 'emp4@gmail.com', null),
    ('10005', 'anthony', 'emp5@gmail.com', null);
