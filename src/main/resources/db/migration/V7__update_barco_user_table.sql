-- Author: techmatrix

create table barco_user
(
    id              bigint        auto_increment   primary key,
    barco_id        bigint        not null,
    user_id         bigint        not null,
    description     varchar(500)  null,
    assign_user_id  bigint        not null,
    created_at      datetime(6)   null,
    updated_at      datetime(6)   null,
    constraint UK_nln1fmqn4mge4upux0q11xw7p
        unique (user_id),
    constraint FK72eku4j5frjjlcwamouym1122
        foreign key (barco_id) references barcos (id),
    constraint FK72eku4j5frjjldfgdfgdf2211
        foreign key (user_id) references users (id),
    constraint FK72rtyukyikdfgdfgdfgdf3211
        foreign key (assign_user_id) references users (id)
);

