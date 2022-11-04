create table user
(
    name varchar(20) NOT NULL,
    id varchar(100),
    password varchar(100) NOT NULL,
    birth_year int,
    birth_month int,
    birth_day int,
    gender int,

    point bigint,
    keyword varchar(100),
    title varchar(100),

    primary key (id)
);