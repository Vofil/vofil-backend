create table Vote
(
    User_id varchar(100),
    id int AUTO_INCREMENT,
    gender int,
    age int,
    endingPoint int,
    kind int,
    pic_cnt int,

    result1 int,
    result2 int,
    result3 int,
    result4 int,
    category varchar(100),
    title varchar(100),
    Taging varchar(100),

    primary key (id)
);
