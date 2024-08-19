-- Change Set 1673930216-1
-- Author: thetaDev
create table categories
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    description varchar(500) null,
    title       varchar(255) null,
    updated_at  datetime(6)  null
);

create table cities
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    description varchar(500) null,
    title       varchar(255) null,
    updated_at  datetime(6)  null
);

create table permission
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table positions
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    description varchar(500) null,
    title       varchar(255) null,
    updated_at  datetime(6)  null
);

create table products
(
    id           bigint auto_increment
        primary key,
    created_at   datetime(6)  null,
    description  varchar(500) null,
    is_dangerous bit          null,
    is_glass     bit          null,
    title        varchar(255) null,
    updated_at   datetime(6)  null,
    weight       int          null,
    category_id  bigint       not null,
    constraint FKog2rp4qthbtt2lfyhfo32lsw9
        foreign key (category_id) references categories (id)
);

create table roles
(
    id   bigint auto_increment
        primary key,
    name varchar(255) not null,
    constraint UK_ofx66keruapi6vyqpv6f2or37
        unique (name)
);


create table role_permissions
(
    role_id       bigint       not null,
    permission_id varchar(255) not null,
    primary key (role_id, permission_id),
    constraint FKn5fotdgk8d1xvo8nav9uv3muc
        foreign key (role_id) references roles (id)
);

create table storehouses
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    description varchar(500) null,
    title       varchar(255) null,
    updated_at  datetime(6)  null,
    city_id     bigint       not null,
    constraint FK72eku4j5frjjlcwamouym6l5q
        foreign key (city_id) references cities (id)
);

create table users
(
    id          bigint auto_increment
        primary key,
    bio         varchar(500) null,
    created_at  datetime(6)  null,
    email       varchar(200) not null,
    firstname   varchar(255) null,
    lastname    varchar(255) null,
    password    varchar(255) null,
    updated_at  datetime(6)  null,
    position_id bigint       null,
    constraint UK_63m4nrc43y8qe89qrx9n14wb2
        unique (position_id),
    constraint UK_6dotkott2kjsp8vw4d0m25fb7
        unique (email),
    constraint FK6ph6xiiydudp6umjf2xckbbmi
        foreign key (position_id) references positions (id)
);

create table barcos
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    description varchar(500) null,
    speedometer int          null,
    title       varchar(255) null,
    updated_at  datetime(6)  null,
    weight      int          null,
    year        int          null,
    user_id     bigint       null,
    constraint FKaxeme6dhprhh2dysjbo77wku4
        foreign key (user_id) references users (id)
);


create table positions_users
(
    position_id bigint not null,
    users_id    bigint not null,
    primary key (position_id, users_id),
    constraint UK_nln1fmqn4mge4upux0q11xw7p
        unique (users_id),
    constraint FK74907pkt1sowusmylv0gv033s
        foreign key (users_id) references users (id),
    constraint FKqbfmbpit0d27025ybompq6ucx
        foreign key (position_id) references positions (id)
);


create table transportations
(
    id            bigint auto_increment
        primary key,
    created_at    datetime(6) null,
    distance      int         null,
    updated_at    datetime(6) null,
    weight        int         null,
    barco_id      bigint      not null,
    storehouse_id bigint      not null,
    constraint FK7o4mcemf5linqftvwqueq302d
        foreign key (storehouse_id) references storehouses (id),
    constraint FKgvnpaa2rcnlji80ykjrru5s8i
        foreign key (barco_id) references barcos (id)
);

create table invoices
(
    id                bigint auto_increment
        primary key,
    amount            float        null,
    created_at        datetime(6)  null,
    description       varchar(500) null,
    status            varchar(255) null,
    title             varchar(255) null,
    updated_at        datetime(6)  null,
    transportation_id bigint       null,
    constraint FK9i21a4j59dqsbosyrpd6gibdi
        foreign key (transportation_id) references transportations (id)
);

create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FKh8ciramu9cc9q3qcqiv4ue8a6
        foreign key (role_id) references roles (id),
    constraint FKhfh9dx7w3ubf1co1vdev94g3f
        foreign key (user_id) references users (id)
);