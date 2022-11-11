drop table if exists user_company cascade;
create table user_company (
    id                          bigint GENERATED BY DEFAULT AS IDENTITY,
    login                       varchar(255),
    password                    varchar(255),
    name                        varchar(255),
    surname                     varchar(255),
    patronymic                  varchar(255),
    phone                       varchar(255),
    email                       varchar(255),
    position                    varchar(255),
    created                     timestamp,
    account_is_locked           boolean,
    password_expiration_date    timestamp,
    primary key (id)
);

drop table if exists product cascade;
create table product (
    id                          bigint GENERATED BY DEFAULT AS IDENTITY,
    article                     varchar(255),
    name                        varchar(255),
    category                    varchar(255),
    count                       integer,
    primary key (id)
);

drop table if exists product_order cascade;
create table product_order (
    id                          bigint GENERATED BY DEFAULT AS IDENTITY,
    user_id                     bigint references user_company(id),
    delivery_address            varchar(255),
    client_fio                  varchar(255),
    client_id                   varchar(255),
    uid                         varchar(255),
    term                        timestamp,
    status                      varchar(20),
    comment                     varchar(255),
    priority                    varchar(255),
    primary key (id)
);

drop table if exists order_item cascade;
create table order_item (
    id                          bigint GENERATED BY DEFAULT AS IDENTITY,
    product_id                  bigint references product(id),
    order_id                    bigint references product_order(id),
    count                       integer,
    primary key (id)
);