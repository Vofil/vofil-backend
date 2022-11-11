create table Vote
(
    id int AUTO_INCREMENT,
    user_id varchar(100),
    gender int,
    age int,
    ending_point int,
    kind int,
    pic_cnt int,

    result1 int,
    result2 int,
    result3 int,
    result4 int,


    taging varchar(100),
    categorying varchar(100),
    feeling varchar(100),
    primary key (id)
);
