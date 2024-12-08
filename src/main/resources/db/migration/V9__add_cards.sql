
create table cards
(
    `id`          bigint        auto_increment   primary key,
    `title`       varchar(255)  not  null,
    `number`      bigint        not  null,
    `type`	      varchar(255)  not  null,
    `month`	      int           not  null,
    `year`        int           not  null,
    `cvv`         int           not  null,
    `cc`          varchar(3)    not  null,
    `amount`      decimal(14,2) not  null,
    `user_id`     int           not  null,

    created_at    datetime(6)        null,
    updated_at    datetime(6)        null
);

