-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
create sequence hibernate_sequence start with 1 increment by 1;

create table clients
(
    id  bigint PRIMARY KEY DEFAULT nextval('hibernate_sequence'),
    name varchar(50),
    login    varchar(50),
    password varchar(50)

);

create table  addresses (
    id bigint PRIMARY KEY DEFAULT nextval('hibernate_sequence'),
    street VARCHAR(255),
    client_id bigint,
    CONSTRAINT fk_client_addresses FOREIGN KEY (client_id) REFERENCES clients(id)
);

create table phones
(
    id  bigint PRIMARY KEY DEFAULT nextval('hibernate_sequence'),
    number  VARCHAR(50),
    client_id bigint,
    CONSTRAINT fk_client_phones FOREIGN KEY (client_id) REFERENCES clients(id)

);

INSERT INTO clients (login, name, password) VALUES ('client1', 'Пушкин Александр', '111');

INSERT INTO phones (client_id, number) VALUES (1, '+7999');
INSERT INTO phones (client_id, number) VALUES (1, '+7998');

INSERT INTO addresses (client_id, street) VALUES (1, 'street1');
