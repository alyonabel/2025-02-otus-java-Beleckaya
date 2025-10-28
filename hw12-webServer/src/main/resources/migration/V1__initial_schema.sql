-- Для @GeneratedValue(strategy = GenerationType.IDENTITY)
/*
create table client
(
    id   bigserial not null primary key,
    name varchar(50)
);

 */

-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
create sequence hibernate_sequence start with 1 increment by 1;

create table client
(
    id   bigint not null primary key,
    name varchar(50)
    login    varchar(50),
    password varchar(50)

);

create table address_data_set
(
    id       bigint not null primary key,
    street   varchar(50),
    clent_id bigint
);

create table phone_data_set
(
    id       bigint not null primary key,
    name   varchar(50),
    clent_id bigint
);

alter table if exists address_data_set add constraint fk_clent_id_client foreign key (clent_id) references client;

alter table if exists phone_data_set add constraint fk_clent_id_client foreign key (client_id) references client;

insert into client (login, name, password, id) values ('client1', 'Пушкин Александр', '111', 1);
insert into phone_data_set (client_id, number, id) values (1, '+7999', 1);
insert into phone_data_set (client_id, number, id) values (1, '+7998', 2);
insert into address_data_set (clent_id, street, id) values (1, 'street1', 1);

SELECT nextval('hibernate_sequence');
