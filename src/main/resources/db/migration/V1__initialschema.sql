-- Change Set 202408081200-1
-- Author: Vitalii, Alexander ))

-- 1
create table barco_products
(
    id         bigint not null auto_increment,
    barco_id   bigint,
    product_id bigint,
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id)
) engine = InnoDB;

-- 2
create table barco_users
(
    id         bigint not null auto_increment,
    barco_id   bigint,
    user_id    bigint,
    created_at datetime(6),
    updated_at datetime(6),
    primary key (id)
) engine = InnoDB;

-- 3
create table barcos
(
    id          bigint not null auto_increment,
    title       varchar(255),
    description varchar(500),
    speedometer integer,
    weight      integer,
    year        integer,
    created_at  datetime(6),
    updated_at  datetime(6),
    primary key (id)
) engine = InnoDB;

-- 4
create table categories
(
    id          bigint not null auto_increment,
    title       varchar(255),
    description varchar(500),
    created_at  datetime(6),
    updated_at  datetime(6),
    primary key (id)
) engine = InnoDB;

-- 5
create table cities
(
    id          bigint not null auto_increment,
    title       varchar(255),
    description varchar(500),
    created_at  datetime(6),
    updated_at  datetime(6),
    primary key (id)
) engine = InnoDB;

-- 6
create table invoices
(
    id                bigint not null auto_increment,
    title             varchar(255),
    description       varchar(500),
    amount            float(23),
    transportation_id bigint,
    status            varchar(255),
    created_at        datetime(6),
    updated_at        datetime(6),
    primary key (id)
) engine = InnoDB;

-- 7
create table permission
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
) engine = InnoDB;

-- 8
create table positions
(
    id          bigint not null auto_increment,
    title       varchar(255),
    description varchar(500),
    created_at  datetime(6),
    updated_at  datetime(6),
    primary key (id)
) engine = InnoDB;

-- 9
create table positions_users
(
    position_id bigint not null,
    user_id    bigint not null,
    primary key (position_id, user_id)
) engine = InnoDB;

-- 10
create table products
(
    id           bigint not null auto_increment,
    title        varchar(255),
    description  varchar(500),
    category_id  bigint not null,
    is_dangerous bit,
    is_glass     bit,
    weight       integer,
    created_at   datetime(6),
    updated_at   datetime(6),
    primary key (id)
) engine = InnoDB;

-- 11
create table role_permissions
(
    role_id       bigint not null,
    permission_id bigint not null,
    primary key (permission_id, role_id)
) engine = InnoDB;

-- 12
create table roles
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
) engine = InnoDB;

-- 13
create table storehouse_barcos
(
    id            bigint not null auto_increment,
    storehouse_id bigint,
    barco_id      bigint,
    created_at    datetime(6),
    updated_at    datetime(6),
    primary key (id)
) engine = InnoDB;

-- 14
create table storehouses
(
    id          bigint not null auto_increment,
    title       varchar(255),
    description varchar(500),
    city_id     bigint not null,
    created_at  datetime(6),
    updated_at  datetime(6),
    primary key (id)
) engine = InnoDB;

-- 15
create table transportations
(
    id            bigint not null auto_increment,
    barco_id      bigint not null,
    storehouse_id bigint not null,
    distance      integer,
    weight        integer,
    created_at    datetime(6),
    updated_at    datetime(6),
    primary key (id)
) engine = InnoDB;

-- 16
create table users
(
    id          bigint       not null auto_increment,
    firstname   varchar(255),
    lastname    varchar(255),
    password    varchar(255),
    position_id bigint,
    email       varchar(200) not null,
    bio         varchar(500),
    created_at  datetime(6),
    updated_at  datetime(6),
    primary key (id)
) engine = InnoDB;

-- 17
create table user_roles
(
    id          bigint       not null auto_increment,
    user_id     bigint not null,
    role_id     bigint not null,
    created_at  datetime(6),
    updated_at  datetime(6),
    primary key (id)
) engine = InnoDB;

--
alter table positions_users
    add constraint UK_nln1fmqn4mge4upux0q11xw7p unique (user_id);
alter table users
    add constraint UK_63m4nrc43y8qe89qrx9n14wb2 unique (position_id);
alter table users
    add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);
alter table barco_products
    add constraint FKmhr0ad1klrokvgriiqf1017l foreign key (barco_id) references barcos (id);
alter table barco_products
    add constraint FKk6a09i9iq8t69bacmf7ot0taj foreign key (product_id) references products (id);
alter table barco_users
    add constraint FKdhdmsc8tgpqsobpixrm9kkm0u foreign key (barco_id) references barcos (id);
alter table barco_users
    add constraint FK8t0dh3ahui3tgrjegp3gxmud0 foreign key (user_id) references users (id);
alter table invoices
    add constraint FK9i21a4j59dqsbosyrpd6gibdi foreign key (transportation_id) references transportations (id);
alter table positions_users
    add constraint FK74907pkt1sowusmylv0gv033s foreign key (user_id) references users (id);
alter table positions_users
    add constraint FKqbfmbpit0d27025ybompq6ucx foreign key (position_id) references positions (id);
alter table products
    add constraint FKog2rp4qthbtt2lfyhfo32lsw9 foreign key (category_id) references categories (id);
alter table role_permissions
    add constraint FKh0v7u4w7mttcu81o8wegayr8e foreign key (permission_id) references permission (id);
alter table role_permissions
    add constraint FKn5fotdgk8d1xvo8nav9uv3muc foreign key (role_id) references roles (id);
alter table storehouse_barcos
    add constraint FKipu2arh61bsjv1yvedh334xeb foreign key (barco_id) references barcos (id);
alter table storehouse_barcos
    add constraint FKhpnrjrbtoyvrkey34q15on9bd foreign key (storehouse_id) references storehouses (id);
alter table storehouses
    add constraint FK72eku4j5frjjlcwamouym6l5q foreign key (city_id) references cities (id);
alter table transportations
    add constraint FKgvnpaa2rcnlji80ykjrru5s8i foreign key (barco_id) references barcos (id);
alter table transportations
    add constraint FK7o4mcemf5linqftvwqueq302d foreign key (storehouse_id) references storehouses (id);
alter table users
    add constraint FK6ph6xiiydudp6umjf2xckbbmi foreign key (position_id) references positions (id);
alter table user_roles
    add constraint FKa62j07k5mhgifpp955h37ponj foreign key (role_id) references roles (id);
alter table user_roles
    add constraint FKml90kef4w2jy7oxyqv742tsfc foreign key (user_id) references users (id);

