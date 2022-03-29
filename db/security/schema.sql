CREATE TABLE authorities (
                             id serial primary key,
                             authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users (
                       id serial primary key,
                       username VARCHAR(50) NOT NULL unique,
                       password VARCHAR(100) NOT NULL,
                       enabled boolean default true,
                       authority_id int not null references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('master', true, '$2a$10$cKiygpsLdSOGI/sFHJwjLe2t68jIcOJfgmfAzTg1Xm4aPQwYzyXji',
        (select id from authorities where authority = 'ROLE_ADMIN'));