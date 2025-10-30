CREATE TABLE clients (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50),
    login VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE address_data_set (
    id BIGINT PRIMARY KEY,
    street VARCHAR(50),
    client_id BIGINT
    CONSTRAINT fk_client_address_data_set FOREIGN KEY (client_id) REFERENCES clients(id)

);

CREATE TABLE phone_data_set (
    id BIGINT PRIMARY KEY,
    number VARCHAR(50),
    client_id BIGINT
    CONSTRAINT fk_client_phone_data_set FOREIGN KEY (client_id) REFERENCES clients(id)

);

