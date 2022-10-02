--liquibase formatted sql

--changeset tpronicheva:insert_data_in_products

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (name, surname, email, phone, login, password, address)
values ('Bob','White', 'bob_johnson@gmail.com', '+79462348923', 'bwhite', '$2a$12$yAlWaR8OInRRKoGNecFntODaAlKYbGN11aDsGHZWYWYXOsC5I0mpu', 'Russia, Ivanovo, Lesnaya 11' ),
       ('John', 'Smith', 'john_johnson@gmail.com', '+72309432844', 'jsmith' , '$2a$12$UUsCSb7G.iU.Oh//Ns2H/ug9dEenhYQw/UcU4pmx08mLQlnudmx0a', 'Russia, Moscow, Lenina 15, kv 33');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);