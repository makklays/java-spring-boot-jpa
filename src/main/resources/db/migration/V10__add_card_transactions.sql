
create table card_transactions
(
    `id`               bigint          auto_increment   primary key,
    `number`           bigint          not  null,
    `number_to`        varchar(255)    null,
    `purpose`	       varchar(255)    null,
    `amount`           decimal(14,2)   not  null,

    `is_add`           bit             null,
    `is_withdrawal`    bit             null,
    `is_transfer`      bit             null,
    `is_payment`       bit             null,

    `user_id`          int             not  null,

    created_at         datetime(6)     null,
    updated_at         datetime(6)     null
);

