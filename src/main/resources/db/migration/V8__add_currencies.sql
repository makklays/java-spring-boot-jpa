
create table currencies
(
    id bigint auto_increment primary key,
    r030          int not null,
    title         varchar(255) not null,
    rate	      float not null,
    cc	          varchar(255) not null,
    exchangedate  varchar(255) not null,
    created_at    datetime(6)  null
);

