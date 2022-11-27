create table Picture
(
    id int,
    re1 varchar(100),
    re2 varchar(100),
    re3 varchar(100),
    re4 varchar(100),


    primary key (id),

    FOREIGN KEY (id) REFERENCES Vote (id)
);
