create table Voter
(
    id int AUTO_INCREMENT,
    user_id varchar(100),
    vote_id int,
    result1 int,
    result2 int,
    result3 int,
    result4 int,

    primary key (id),

    FOREIGN KEY (id) REFERENCES Vote (id)
);
